package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.icedragongame.common.R;
import com.icedragongame.dto.UserImageDto;
import com.icedragongame.entity.User;
import com.icedragongame.myenum.SystemError;
import com.icedragongame.service.UserService;
import com.icedragongame.utils.OssUtils;
import com.icedragongame.vo.ImageUploadVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
    @Resource
    private UserService userService;

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
    @ApiOperation("(已完成)上传图片到云端,得到一个可以全网直接访问到的图片链接")
    public R<ImageUploadVo> imageUpLoad(MultipartFile multipartFile){
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

    @PostMapping("/setUserImage")
    @ApiOperation("(为用户设置图片url,通过图片上传接口获得用户图片的imageurl后立马调用这个接口) (已完成)")
    public R<Object> imageUpLoad1(UserImageDto userImageDto){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,userImageDto.getUsername());
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getUsername,userImageDto.getUsername()).set(User::getImageUrl,userImageDto.getImage_url());
        userService.update(updateWrapper);




        return R.success("上传成功");
    }
}
