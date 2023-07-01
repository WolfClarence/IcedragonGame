package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.icedragongame.common.R;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.User;
import com.icedragongame.service.PostService;
import com.icedragongame.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    @PostMapping("/banUser/{username}")
    public R<String> banUser ( @PathVariable String  username){

        User user = userService.getById(username);
        if (user!=null){
            if (user.getUserStatus().equals("已封禁"))
                return R.error("该用户已处在封禁态");
            else {
                LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
                updateWrapper.eq(User::getUsername, username).set(User::getUserStatus, "已封禁");
                userService.update(updateWrapper);
                return R.success("封禁成功");
            }
        }else{
            return R.error("该用户不存在");
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
    public R<String> freeUser ( @PathVariable String username){
        User user = userService.getById(username);
        if (user!=null){
            if (user.getUserStatus().equals("已解封"))
                return R.error("该用户已处在解封态");
            else {
                LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
                updateWrapper.eq(User::getUsername, username).set(User::getUserStatus, "已解封");
                userService.update(updateWrapper);
                return R.success("解封成功");
            }
        }else{
            return R.error("该用户不存在");
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
    @PostMapping ("/changeAuditStatus/{postId}/{status}")
    public R<String> changeAuditStatus( @PathVariable("postId") Integer id,@PathVariable String status){

            Post post = postService.getById(id);
            if(post!=null) {
                String result = post.getAuditStatus();
                if (result.equals(status))
                    return R.error("已在该状态");
                else {
                    LambdaUpdateWrapper<Post> updateWrapper = new LambdaUpdateWrapper<>();
                    updateWrapper.eq(Post::getPostId, id).set(Post::getAuditStatus, status);
                    postService.update(updateWrapper);
                    return R.success("状态更改成功");
                }
            }else{
                return R.error("该帖子不存在");
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
    public R<String> upGrade( @PathVariable String username){

        User user = userService.getById(username);
        if(user!=null){
            if(user.getUserIdentity()==1)
                return R.error("该用户已是管理员");
            else{
                LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
                updateWrapper.eq(User::getUsername, username).set(User::getUserIdentity, "1");
                userService.update(updateWrapper);
                return R.success("成功升级为管理员");
            }
        }
        else{
            return R.error("该用户不存在");
        }

    }

}
