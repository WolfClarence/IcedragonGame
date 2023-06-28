package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icedragongame.common.R;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.User;
import com.icedragongame.entity.UserPost;
import com.icedragongame.service.PostService;
import com.icedragongame.service.ReplyService;
import com.icedragongame.service.UserPostService;
import com.icedragongame.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName : UserController  //类名
 * @Description : 个人按钮  //描述
 * @Author : wenrui //作者
 * @Date: 2023/6/28  14:08
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Resource
    private ReplyService replyService;
    @Resource
    private UserService userService;
    @Resource
    private PostService postService;
    @Resource
    private UserPostService userPostService;

    //获取用户基本信息
    @GetMapping("/personal/userinfo/{username}")
    public R<User> getUserInfoByName(@RequestParam("username") String username){

        return R.success(userService.getById(username));
    }

    //获取用户发帖记录
    @GetMapping("/personal/post/{username}")
    public R<List<Post>> getUserPostRecordByName(@RequestParam("username") String username){
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username); // 根据用户名查询
        List<Post> postList = postService.list(queryWrapper);
        return R.success(postList);
    }

    //获取未审核的帖子
    @GetMapping("/manager/uncheckedlist")
    public R<List<Post>> getUnauditedPost(){
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("audit_status",0);
        List<Post> postList = postService.list(queryWrapper);
        return R.success(postList);
    }


    //删除帖子
    @PostMapping("/manager/delete")
    public R<Boolean> deletePostById(@RequestParam("post_id") int id){

        return R.success(replyService.removeById(id));
    }
}
