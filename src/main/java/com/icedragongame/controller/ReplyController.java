package com.icedragongame.controller;

import com.icedragongame.common.R;
import com.icedragongame.entity.Reply;
import com.icedragongame.service.PostService;
import com.icedragongame.service.ReplyService;
import com.icedragongame.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Resource
    private ReplyService replyService;
    @Resource
    private UserService userService;
    @Resource
    private PostService postService;

    @RequestMapping("/ReplyPostById/{id}/{reply}")
     public R<Reply> ReplyPostById (@PathVariable Integer postId,@PathVariable("reply") String replyContext){
        Reply reply = new Reply();
        return R.success(reply);

    }


}
