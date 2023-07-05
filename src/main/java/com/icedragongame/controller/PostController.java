package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icedragongame.common.R;
import com.icedragongame.constant.ConstantBySelf;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.Reply;
import com.icedragongame.entity.User;
import com.icedragongame.service.CategoryService;
import com.icedragongame.service.PostService;
import com.icedragongame.service.ReplyService;
import com.icedragongame.service.UserService;
import com.icedragongame.utils.MyBeanUtils;
import com.icedragongame.utils.MyRedisUtils;
import com.icedragongame.vo.PostDetailVo;
import com.icedragongame.vo.PostForBigBlockVo;
import com.icedragongame.vo.PostForTodayHotVO;
import com.icedragongame.vo.ReplyVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private ReplyService replyService;

    private CategoryService categoryService;

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

    @GetMapping("/postingPage/{num}")
    @ApiOperation("(今日热门推荐)得到今日最热帖子若干(已完成)  ,要求今日帖子,热度计算公式:2*replynum+scannum")
    public R<List<PostForTodayHotVO>> getTodayHot(@PathVariable String num){//传入分类名称
        int i = 0;
        int j = 0;
        LambdaQueryWrapper<Post> queryWapper = new LambdaQueryWrapper<>();
        //获取该类post
        List<Post> posts = postService.list(queryWapper.eq(Post::getCategoryId,num));
        List<Post> postList = new ArrayList<>();
        //获取符合时间post
        while(i<posts.size()){
            //System.out.println(posts.get(i).getBuildTime());
            //标准化
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date postDate = posts.get(i).getBuildTime();
            Date nowdate = new Date();

            //System.out.println(postDate);
           // System.out.println(nowdate);
            System.out.println(formatter.format(postDate));
            if(formatter.format(postDate).equals(formatter.format(nowdate))){

                postList.add(posts.get(j));
                System.out.println(postList);
                j++;
            }
            i++;
        }
        //热度排序
        postService.sortPostByHot(postList);

        //System.out.println(postList);
        List<PostForTodayHotVO> postForTodayHotVOS = postList.stream().map(post -> MyBeanUtils.beanCopy(post,PostForTodayHotVO.class)).collect(Collectors.toList());
        //System.out.println(postForTodayHotVOS);
       return R.success(postForTodayHotVOS);
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
     *       查看指定帖子本身详情
     *
    Get  该方法设计参数描述:
     *   <description>
     *
     */
    @GetMapping("/getPostDetailById/{postId}")
    @ApiOperation("(得到文章详情)(已完成)")
    public R<PostDetailVo> getPostDetailById(@PathVariable Integer postId){
        Post post = postService.getById(postId);
        System.out.println(post);
        PostForBigBlockVo postVo = postService.getPostVoByPost(post);
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
    @ApiOperation("(查看指定帖子回复详情,并按时间倒叙排列)(已完成 )")
    @GetMapping("/getPostReplyById/{postId}")
    public R<List<ReplyVo>> getPostReplyById(@PathVariable Integer postId){
        LambdaQueryWrapper<Reply> ss = new LambdaQueryWrapper<>();
        ss.eq(Reply::getPostId,postId).orderByDesc(Reply::getBuildTime);
        List<Reply> replyList = replyService.list(ss);
        List<ReplyVo> replyVoList = replyList.stream().map(reply -> MyBeanUtils.beanCopy(reply,ReplyVo.class)).collect(Collectors.toList());
        for (ReplyVo replyVo : replyVoList) {
            User byId = userService.getById(replyVo.getUsername());
            replyVo.setUser_url(byId.getImageUrl());
        }
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
    @ApiOperation("(对某个文章的浏览量加一)(已完成)")
    public R<Object> scanNumUpOne(@PathVariable("postId") Integer postId){
        Integer integer = myRedisUtils.getMapValue(ConstantBySelf.REDIS_KEY_SCANS_POST,postId);
        myRedisUtils.setMapValue(ConstantBySelf.REDIS_KEY_SCANS_POST,postId,integer+1);
        return R.success();
    }
}
