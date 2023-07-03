package com.icedragongame.utils;

import com.google.gson.Gson;
import com.icedragongame.myenum.SystemError;
import com.icedragongame.exception.SystemExceptionBySelf;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @auther: gengxuelong
 * @date: 2023/6/29 19:50
 */
@Data
@Component
@ConfigurationProperties(prefix = "gxl.oss")
public class OssUtils {

    private String accessKey;
    private String secretKey;
    private String bucket;
    private String domain;
    private int maxFileSize;

    public  String imageUpload(InputStream inputStream,String suffix){
        int available = 0;
        try {
            available = inputStream.available();
            if(available>maxFileSize){
                throw new SystemExceptionBySelf(SystemError.FILE_SIZE_OVER_LIMIT);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        UploadManager uploadManager = new UploadManager(cfg);
        String key =  getKeyForUpImage()+suffix;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(inputStream,key,upToken,null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            return domain+putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                ex2.printStackTrace();
            }
        }
        return null;
    }

    private  String getKeyForUpImage() {
        Random random = new Random();
        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String time = simpleDateFormat.format(date);
//        System.out.println(time);
        long l = random.nextLong();
        return time+"/"+l;
    }
}
