package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icedragongame.common.R;
import com.icedragongame.constant.ConstantBySelf;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.Reply;
import com.icedragongame.service.PostService;
import com.icedragongame.service.ReplyService;
import com.icedragongame.utils.MyRedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Resource
    private PostService postService;
    @Resource
    private ReplyService replyService;

    @Autowired
    MyRedisUtils myRedisUtils;

    /**
     * 查看指定帖子本身详情
     * @param id id
     * @return R
     */
    @RequestMapping("/getPostDetailById/{id}")
    public R<Post> getPostDetailById(@PathVariable Integer id){
        Post post = postService.getById(id);
        return R.success(post);
    }

    /**
     * 查看指定帖子回复详情
     * @param id id
     * @return post
     */
    @RequestMapping("/getPostReplyById/{id}")
    public List<Reply> getPostReplyById( @PathVariable Integer id){
        LambdaQueryWrapper<Reply> ss = new LambdaQueryWrapper<>();
        ss.eq(Reply::getPostId,id);
        return replyService.list(ss);
    }

    @RequestMapping("/scanNumUpOne/{postId}")
    public R<Object> scanNumUpOne(@PathVariable("postId") Integer postId){
        myRedisUtils.increaseOneToMapValue(ConstantBySelf.KEY_SCANS_POST,postId);
        return R.success();
    }




}
