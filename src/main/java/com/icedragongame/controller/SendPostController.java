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
 * <p>
 *     project: snow_dragonGame blogSystem
 *
 *  该类名称为:
 *     <name>
 *         SendPostController
 *
 *  该类作用为:
 *   <effect>
 *       发帖人完成帖子信息输入后，将新帖子的信息写入数据库  //描述
 *
 *@author wenrui
 *
 */
@RestController
@RequestMapping("/api")
public class SendPostController {

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
    private PostService postService;

    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该方法名称为:
     *     <name>
     *
     *  该方法作用为:
     *   <effect>
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    @PostMapping ("/postingPage")
    public R<String> sendPost(@RequestBody PostDto postDto){
//        System.out.println(postDto.toString());
        Post post = MyBeanUtils.beanCopy(postDto,Post.class,true);
//        System.out.println(post.toString());
        postService.save(post);
        return R.success("帖子写入成功！");//postService.save(post)
    }


}
