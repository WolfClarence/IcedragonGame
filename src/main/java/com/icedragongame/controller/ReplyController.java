package com.icedragongame.controller;

import com.icedragongame.common.R;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.Reply;
import com.icedragongame.service.PostService;
import com.icedragongame.service.ReplyService;
import com.icedragongame.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
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

    //这里回复帖子的用户username应该用session传过来，尚未解决
    @GetMapping("/ReplyAPost/{postId}/{reply}/{replyUserName}")
    public R<Reply> ReplyAPost (@PathVariable("postId") Integer postId,@PathVariable("reply") String replyContext,@PathVariable("replyUserName") String username){

        Post post = postService.getById(postId);
        if(post!=null) {

            Reply reply = new Reply();
            int testId = (postId * 1000);
            while(replyService.getById(testId)!=null){
                testId++;
            }
            reply.setReplyId(testId);
            reply.setReplyContext(replyContext);
            reply.setUsername(username);
            reply.setPostId(postId);
            replyService.save(reply);
            return R.success(reply);
        }else {
            return R.error("该帖子不存在");
        }
    }




}
