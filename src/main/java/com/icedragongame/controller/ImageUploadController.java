package com.icedragongame.controller;

import com.icedragongame.common.R;
import com.icedragongame.myenum.SystemError;
import com.icedragongame.utils.OssUtils;
import com.icedragongame.vo.ImageUploadVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 *     project: snow_dragonGame blogSystem
 *
 *  该方法名称为:
 *     <name>
 *
 *  该方法作用为:
 *   <effect>
 *
 *   该方法设计参数描述:
 *   <description>
 *
 */
@RestController
@RequestMapping("image")
public class ImageUploadController {
    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该参数名称为:
     *     <name>
     *
     *  该参数描述为:
     *   <effect>
     *
     */
    @Autowired
    OssUtils ossUtils;

    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该方法名称为:
     *     <name>
     *
     *  该方法作用为:
     *   <effect>
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    @PostMapping("/upload")
    @ApiOperation("上传图片到云端,得到一个可以全网直接访问到的图片链接")
    public R<Object> imageUpLoad(MultipartFile multipartFile){
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String originalFilename = multipartFile.getOriginalFilename();
        System.out.println(originalFilename);
        assert originalFilename != null;
        String[] split = originalFilename.split("\\.");
        String type = "."+(split[split.length-1]);
        if(!(".png".equals(type)||".jpg".equals(type))){
            return R.error(SystemError.IMAGE_TYPE_ERROR);
        }
        String link = ossUtils.imageUpload(inputStream, type);
        return R.success(new ImageUploadVo(link));
    }
}
