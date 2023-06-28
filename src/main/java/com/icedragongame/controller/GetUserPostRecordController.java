package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icedragongame.common.R;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.User;
import com.icedragongame.entity.UserPost;
import com.icedragongame.service.ReplyService;
import com.icedragongame.service.UserPostService;
import com.icedragongame.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : GetUserPostRecordController  //类名
 * @Description :  获取某人的发帖记录，查询帖子表  //描述
 * @Author : wenrui //作者
 * @Date: 2023/6/27  16:11
 */
@RestController
@RequestMapping("/")
public class GetUserPostRecordController {
    @Resource
    private ReplyService replyService;
    @Resource
    private UserService userService;
    @Resource
    private UserPostService userPostService;

    @GetMapping("/getUserPostRecord")
    public R<List<Post>> getUserPostRecordByName(String username){
        QueryWrapper<UserPost> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        queryWrapper.select("id");
        List<UserPost> ids = userPostService.list(queryWrapper);
        System.out.println(ids);
        return null;
    }

}
