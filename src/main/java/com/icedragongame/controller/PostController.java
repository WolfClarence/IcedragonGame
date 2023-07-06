package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
    @Resource
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

    @Autowired
    UserService userService;

    @GetMapping("/postingPage/{num}")
    @ApiOperation("(今日热门推荐)得到今日最热帖子若干(已完成)  ,要求今日帖子,热度计算公式:2*replynum+scannum")
    public R<List<PostForTodayHotVO>> getTodayHot(@PathVariable int num){//传入分类名称
//        int i = 0;
//        int j = 0;
//        int categoryId;
//        List<Post> posts = new ArrayList<>();
//        List<Post> postList = new ArrayList<>();
//        LambdaQueryWrapper<Post> queryWapper = new LambdaQueryWrapper<>();
//        //获取游戏id
//        Category category = categoryService.getOne( new LambdaUpdateWrapper<Category>().eq(Category::getCategoryName,num));
//        if(category==null){
//            return R.error(SystemError.NO_THIS_CATEGORY);
//        }
//        categoryId = categoryService.getOne(new LambdaUpdateWrapper<Category>().eq(Category::getCategoryName,category)).getId();
//        posts = postService.list(queryWapper.eq(Post::getCategoryId,categoryId));

//        if(!(num==null||num.isEmpty())){
//            Category category = categoryService.getOne( new LambdaUpdateWrapper<Category>().eq(Category::getCategoryName,num));
//            if(category==null){
//                return R.error(SystemError.NO_THIS_CATEGORY);
//            }
//            categoryId = category.getId();
//            posts = postService.list(queryWapper.eq(Post::getCategoryId,categoryId));
//        }

        //获取该类post


//        //获取符合时间post
//        while(i<posts.size()){
//            //System.out.println(posts.get(i).getBuildTime());
//            //标准化
//            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//            Date postDate = posts.get(i).getBuildTime();
//            Date nowdate = new Date();
//
//            //System.out.println(postDate);
//            // System.out.println(nowdate);
//            System.out.println(formatter.format(postDate));
//            if(formatter.format(postDate).equals(formatter.format(nowdate))){
//
//                postList.add(posts.get(j));
//                System.out.println(postList);
//                j++;
//            }
//            i++;
//        }
//        //热度排序
//        postService.sortPostByHot(postList);
//
//        System.out.println(postList);

        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("audit_status","审核通过");
        queryWrapper.gt("build_time", LocalDateTime.now().minusHours(24))
                .orderByDesc("2*reply_num + scan_num")
                .last("limit "+num);
        List<Post> postList = postService.list(queryWrapper);
        List<PostForTodayHotVO> postForTodayHotVOS = postList.stream().map(post -> MyBeanUtils.beanCopy(post,PostForTodayHotVO.class)).collect(Collectors.toList());
        System.out.println(postForTodayHotVOS);
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
        PostForBigBlockVo postVo = postService.getBigBlockPostVoByPost(post);
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
    @ApiOperation("(已完成)查看指定帖子回复详情,并按时间倒叙排列")
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
