package com.icedragongame.controller;

import com.icedragongame.utils.MyBeanUtils;
import com.icedragongame.common.R;
import com.icedragongame.dto.PostDto;
import com.icedragongame.entity.Post;
import com.icedragongame.service.PostService;
import com.icedragongame.service.ReplyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName : SendPostController  //类名
 * @Description : 发帖人完成帖子信息输入后，将新帖子的信息写入数据库  //描述
 * @Author : wenrui //作者
 * @Date: 2023/6/27  16:06
 */
@RestController
@RequestMapping("/api")
public class SendPostController {
    @Resource
    private ReplyService replyService;
    @Resource
    private PostService postService;

    @PostMapping ("/postingPage")
    public R<String> sendPost(@RequestBody PostDto postDto){
//        System.out.println(postDto.toString());
        Post post = MyBeanUtils.beanCopy(postDto,Post.class,true);
//        System.out.println(post.toString());
        postService.save(post);
        return R.success("帖子写入成功！");//postService.save(post)
    }


}
