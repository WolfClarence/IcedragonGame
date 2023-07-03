package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icedragongame.common.R;
import com.icedragongame.constant.ConstantBySelf;
import com.icedragongame.vo.PostDetailVo;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.Reply;
import com.icedragongame.service.PostService;
import com.icedragongame.service.ReplyService;
import com.icedragongame.utils.MyBeanUtils;
import com.icedragongame.utils.MyRedisUtils;
import com.icedragongame.vo.PostVo;
import com.icedragongame.vo.ReplyVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
@Api(value = "文章类的接口")
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
    Get  该方法设计参数描述:
     *   <description>
     *
     */
    @GetMapping("/getPostDetailById/{postId}")
    @ApiOperation("查看指定帖子本身详情")
    public R<Object> getPostDetailById(@PathVariable Integer postId){
        Post post = postService.getById(postId);
        System.out.println(post);
        PostVo postVo = postService.getPostVoByPost(post);
        System.out.println(postVo);
        PostDetailVo postDetailVo = MyBeanUtils.beanCopy(post,PostDetailVo.class);
        System.out.println("------------------"+postDetailVo);
        MyBeanUtils.beanCopy(postVo, postDetailVo);
        System.out.println("------------------"+postDetailVo);
        return R.success(postDetailVo);
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
    @ApiOperation("查看指定帖子回复详情,并按时间倒叙排列")
    @GetMapping("/getPostReplyById/{postId}")
    public R<List<ReplyVo>> getPostReplyById(@PathVariable Integer postId){
        LambdaQueryWrapper<Reply> ss = new LambdaQueryWrapper<>();
        ss.eq(Reply::getPostId,postId).orderByDesc(Reply::getBuildTime);
        List<Reply> replyList = replyService.list(ss);
        List<ReplyVo> replyVoList = replyList.stream().map(reply -> MyBeanUtils.beanCopy(reply,ReplyVo.class)).collect(Collectors.toList());
        return R.success(replyVoList);
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
    @GetMapping("/scanNumUpOne/{postId}")
    @ApiOperation("对某个文章的浏览量加一")
    public R<Object> scanNumUpOne(@PathVariable("postId") Integer postId){
        Integer integer = myRedisUtils.getMapValue(ConstantBySelf.REDIS_KEY_SCANS_POST,postId);
        myRedisUtils.setMapValue(ConstantBySelf.REDIS_KEY_SCANS_POST,postId,integer+1);
        return R.success();
    }
}
