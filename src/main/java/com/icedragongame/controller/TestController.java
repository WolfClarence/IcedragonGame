package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icedragongame.common.R;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.Reply;
import com.icedragongame.entity.User;
import com.icedragongame.mapper.PostMapper;
import com.icedragongame.mapper.ReplyMapper;
import com.icedragongame.service.PostService;
import com.icedragongame.service.ReplyService;
import com.icedragongame.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private ReplyService replyService;
    @Resource
    private UserService userService;
    @Resource
    private PostService postService;
    @Resource
    private ReplyMapper replyMapper;
    @Resource
    private PostMapper postMapper;

    @GetMapping("/getuser")
    public R<List<User>> getUserByUsername(){
        return R.success(userService.list());
    }

    @GetMapping("/addreply")
    public R<String> addReply(){
        Reply reply=new Reply();
        reply.setReplyId(666);
        reply.setReplyContext("zhentmsb");
        replyService.save(reply);
        return R.success("hehe");
    }

    @GetMapping("/getreply")
    public R<Reply> getReply(){
        LambdaQueryWrapper<Reply> lqw=new LambdaQueryWrapper<>();
        lqw.eq(Reply::getReplyId,333);
        return R.success(replyService.getOne(lqw));
    }

    @GetMapping("/getreply1")
    public R<Reply> getReply1(){
        return R.success(replyMapper.selectReplyById(333));
    }

    @GetMapping("/getpost")
    public R<Post> getPost(){
        return R.success(postMapper.selectPostById(122));
    }
}
