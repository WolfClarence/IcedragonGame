package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icedragongame.common.R;
import com.icedragongame.constant.ConstantBySelf;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.Reply;
import com.icedragongame.service.PostService;
import com.icedragongame.service.ReplyService;
import com.icedragongame.utils.MyRedisUtils;
import com.icedragongame.vo.BriefPostVo;
import com.icedragongame.vo.ReplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
@RequestMapping("/post")
public class PostController {

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
    @Autowired
    MyRedisUtils myRedisUtils;

    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该方法名称为:
     *     <name>
     *
     *  该方法作用为:
     *   <effect>
     *       查看指定帖子本身详情
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    @RequestMapping("/getPostDetailById/{postId}")
    public R<BriefPostVo> getPostDetailById(@PathVariable Integer postId){
        Post post = postService.getById(postId);
        return R.success(BriefPostVo.getBPVbyAPost(post));
    }

    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该方法名称为:
     *     <name>
     *
     *  该方法作用为:
     *   <effect>
     *       查看指定帖子回复详情
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    @RequestMapping("/getPostReplyById/{postId}")
    public R<List<ReplyVo>> getPostReplyById(@PathVariable Integer postId){
        LambdaQueryWrapper<Reply> ss = new LambdaQueryWrapper<>();
        ss.eq(Reply::getPostId,postId);
        List<Reply> replyList = replyService.list(ss);

        return R.success(ReplyVo.getRVbyReply(replyList));
    }

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
    @RequestMapping("/scanNumUpOne/{postId}")
    public R<Object> scanNumUpOne(@PathVariable("postId") Integer postId){
        myRedisUtils.increaseOneToMapValue(ConstantBySelf.KEY_SCANS_POST,postId);
        return R.success();
    }




}
