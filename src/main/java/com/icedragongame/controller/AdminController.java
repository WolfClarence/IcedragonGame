package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.icedragongame.common.R;
import com.icedragongame.myenum.SystemError;
import com.icedragongame.constant.ConstantBySelf;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.User;
import com.icedragongame.service.PostService;
import com.icedragongame.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <p>
 *     project: snow_dragonGame blogSystem
 *
 *  该类名称为:
 *     <name>
 *
 *  该类作用为:
 *   <effect>
 *       管理员controller
 *
 *@author wzy
 *
 */
@RestController
@RequestMapping("/admin")
@Api(value = "admin的一些功能")
public class AdminController {

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
    @Resource
    private UserService userService;

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
    @Resource
    private PostService postService;

    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该方法名称为:
     *     <name>
     *
     *  该方法作用为:
     *   <effect>
     *       封禁用户
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    @ApiOperation(value = "(封禁用户)(已完成)", notes = "封禁用户")
    @PostMapping("/banUser/{username}")
    public R<String> banUser ( @PathVariable String  username){

        User user = userService.getById(username);
        if (user!=null){
            if (user.getUserStatus().equals(ConstantBySelf.USER_BANNED))
                return R.error(SystemError.USER_HAS_BEEN_BANNED);
            else {
                LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
                updateWrapper.eq(User::getUsername, username).set(User::getUserStatus, ConstantBySelf.USER_BANNED);
                userService.update(updateWrapper);
                return R.success("封禁成功");
            }
        }else{
            return R.error(SystemError.USER_NOT_FOUND);
        }
    }

    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该方法名称为:
     *     <name>
     *
     *  该方法作用为:
     *   <effect>
     *       解封用户
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    @PostMapping("/freeUser/{username}")
    @ApiOperation(value = "(解封用户)(已完成)",notes = "解封用户")
    public R<String> freeUser ( @PathVariable String username){
        User user = userService.getById(username);
        if (user!=null){
            if (!user.getUserStatus().equals(ConstantBySelf.USER_BANNED))
                return R.error(SystemError.USER_HAS_BEEN_FREEDOM);
            else {
                LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
                updateWrapper.eq(User::getUsername, username).set(User::getUserStatus, ConstantBySelf.USER_FREEDOM);
                userService.update(updateWrapper);
                return R.success("解封成功");
            }
        }else{
            return R.error(SystemError.USER_NOT_FOUND);
        }
    }


   /**
    * <p>
    *     project: snow_dragonGame blogSystem
    *
    *  该方法名称为:
    *     <name>
    *
    *  该方法作用为:
    *   <effect>
    *       修改审核状态
    *
    *   该方法设计参数描述:
    *   <description>
    *
    */

   @ApiOperation(value = "(修改文章的审核状态)(已完成)",notes = "status仅能取: 未审核,审核未通过,审核通过")
    @PostMapping ("/changeAuditStatus/{postId}/{status}")
    public R<String> changeAuditStatus( @PathVariable("postId") Integer id,@PathVariable String status){
       if(!ConstantBySelf.POST_STATUS_LIST.contains(status)){
           return R.error(SystemError.WRONG_FORMAT_FOR_POST_STATUS);
       }
        Post post = postService.getById(id);
        if(post!=null) {
            String result = post.getAuditStatus();
            if (result.equals(status))
                return R.error(SystemError.POST_HAS_BEEN_STATUS);
            else {
                LambdaUpdateWrapper<Post> updateWrapper = new LambdaUpdateWrapper<>();
                updateWrapper.eq(Post::getId, id).set(Post::getAuditStatus, status);
                postService.update(updateWrapper);
                return R.success("状态更改成功");
            }
        }else{
            return R.error(SystemError.POST_NOT_FOUND);
        }
    }

    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该方法名称为:
     *     <name>
     *
     *  该方法作用为:
     *   <effect>
     *       升级用户为管理员
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    @PostMapping("/upGrade/{username}")
    @ApiOperation(value = "(升级用户为管理员,只需要传入username)(已完成)",notes = "升级用户为管理员,只需要传入username")
    public R<String> upGrade( @PathVariable String username){

        User user = userService.getById(username);
        if(user!=null){
            if(Objects.equals(user.getUserIdentity(), ConstantBySelf.USER_ADMIN_TAG))
                return R.error(SystemError.USER_HAS_BEEN_ADMIN);
            else{
                LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
                updateWrapper.eq(User::getUsername, username).set(User::getUserIdentity,ConstantBySelf.USER_ADMIN_TAG);
                userService.update(updateWrapper);
                return R.success("成功升级为管理员");
            }
        }
        else{
            return R.error(SystemError.USER_NOT_FOUND);
        }

    }

}
