package com.icedragongame.controller;

import com.icedragongame.common.R;
import com.icedragongame.entity.Post;
import com.icedragongame.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/post")
public class PostController {
    @Resource
    private PostService postService;


    /**
     * 查看指定帖子详情
     * @param id
     * @return post
     */
    @RequestMapping("/getPost")
    public R<Post> getPostById(int id){
        Post post = postService.getById(id);
        if (post==null)
            return R.error("该帖子不存在");
        else return R.success(post);
    }


}
