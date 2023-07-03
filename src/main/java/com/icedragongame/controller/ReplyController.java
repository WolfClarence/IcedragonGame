package com.icedragongame.controller;

import com.icedragongame.common.R;
import com.icedragongame.myenum.SystemError;
import com.icedragongame.dto.ReplyDto;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.Reply;
import com.icedragongame.service.PostService;
import com.icedragongame.service.ReplyService;
import com.icedragongame.service.UserService;
import com.icedragongame.utils.MyBeanUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation("这里回复帖子的用户username应该用session传过来，实际上在replydto中有username属性")
    public R<Object> ReplyAPost (@RequestBody ReplyDto replyDto){

        Integer postId = replyDto.getPost_id();
        Post post = postService.getById(postId);
        if(post!=null) {
            Reply reply = MyBeanUtils.beanCopy(replyDto,Reply.class);
            System.out.println(reply);
            replyService.save(reply);
            post.setReplyNum(post.getReplyNum()+1);
            return R.success();
        }else {
            return R.error(SystemError.POST_NOT_FOUND);
        }
    }




}
