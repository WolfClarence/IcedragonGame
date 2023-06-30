package com.icedragongame.controller;

import com.icedragongame.common.R;
import com.icedragongame.utils.OssUtils;
import com.icedragongame.vo.ImageUploadVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @auther: gengxuelong
 * @date: 2023/6/29 21:05
 */
@RestController
@RequestMapping("image")
public class ImageUploadController {
    @Autowired
    OssUtils ossUtils;

    @PostMapping("/upload")
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
        String link = ossUtils.imageUpload(inputStream, type);
        return R.success(new ImageUploadVo(link));
    }
}
