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

/**
 * <p>
 *     project: snow_dragonGame blogSystem
 *
 *  该类名称为:
 *     <name>
 *
 *  该类作用为:
 *   <effect>
 *
 *@author wzy
 *
 */
@RestController
@RequestMapping("/reply")
public class ReplyController {

    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该参数名称为:
     *     <name>
     *
     *  该参数描述为:
     *   <effect>
     *
     */
    @Resource
    private ReplyService replyService;

    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该参数名称为:
     *     <name>
     *
     *  该参数描述为:
     *   <effect>
     *
     */
    @Resource
    private UserService userService;

    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该参数名称为:
     *     <name>
     *
     *  该参数描述为:
     *   <effect>
     *
     */
    @Resource
    private PostService postService;

    //这里回复帖子的用户username应该用session传过来，实际上在replydto中有username属性
    //未测试
    @PostMapping("/ReplyAPost")
    public R<String> ReplyAPost (@PathVariable ReplyDto replyDto){

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
