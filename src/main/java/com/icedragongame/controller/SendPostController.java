package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icedragongame.common.BeanConvertUtils;
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
    public R<Boolean> sendPost(@RequestBody PostDto postDto){
//        System.out.println(postDto.toString());
        Post post = BeanConvertUtils.convert(postDto,Post.class);
//        System.out.println(post.toString());
        return R.success(postService.save(post));//postService.save(post)
    }


}
