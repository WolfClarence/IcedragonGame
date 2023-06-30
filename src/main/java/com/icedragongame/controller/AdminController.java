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
 *
 * 管理员controller
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private UserService userService;
    @Resource
    private PostService postService;

    /**
     * 封禁用户
     * @param username param1
     * @return R
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
     * 解封用户
     * @param username param
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
     * 修改审核状态
     * @param id 文章id
     * @param status 状态
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
     * 升级用户为管理员
     * @param username username
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
