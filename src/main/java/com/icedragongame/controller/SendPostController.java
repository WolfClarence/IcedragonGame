package com.icedragongame.controller;

import com.icedragongame.common.R;
import com.icedragongame.entity.Post;
import com.icedragongame.service.PostService;
import com.icedragongame.service.ReplyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public R<Boolean> sendPost(@RequestBody Post post){

        return R.success(postService.save(post));
    }


}
