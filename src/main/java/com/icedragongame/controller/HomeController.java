package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icedragongame.common.R;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.UserPost;
import com.icedragongame.service.PostService;
import com.icedragongame.service.UserPostService;
import com.icedragongame.vo.BriefPostVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
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
 *@author py
 *
 */
@RestController
@RequestMapping(value = "/api/home")
public class HomeController {

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
    private UserPostService userPostService;

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
    @GetMapping("/newGame")
    public R<List<BriefPostVo>> newGame(){
        QueryWrapper<Post> query = new QueryWrapper<>();
        query.orderByDesc("build_time");
        query.last("limit 10");
        List<Post> list = postService.list(query);
        return R.success(BriefPostVo.getBPVbyPosts(list));
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
    @GetMapping("/hotgame")
    public R<List<BriefPostVo>> hotgame() {
        QueryWrapper<Post> query = new QueryWrapper<>();
        query.orderByDesc("2 * reply_num + scan_num");
        query.last("limit 10");
        List<Post> list = postService.list(query);
        return R.success(BriefPostVo.getBPVbyPosts(list));
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
    @GetMapping("/followedGame/{username}")
    public R<List<BriefPostVo>> followedGame(@PathVariable(value = "username") String username) {
        QueryWrapper<UserPost> upQuery = new QueryWrapper<>();
        upQuery.eq("username",username);
        List<UserPost> upList =  userPostService.list(upQuery);
        List<Integer> pidList = upList.stream().map(UserPost::getPostId).collect(Collectors.toList());//PostID表
        List<Post> list;
        if(pidList.size() == 0){//没有关注
            list = new ArrayList<>();
        }else {
            list = postService.listByIds(pidList);
        }
        return R.success(BriefPostVo.getBPVbyPosts(list));
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
    @GetMapping("/category/{category}")
    public R<List<BriefPostVo>> category(@PathVariable(value = "category") String category) {
        QueryWrapper<Post> query = new QueryWrapper<>();
        query.eq("category",category);
        List<Post> list = postService.list(query);
        return R.success(BriefPostVo.getBPVbyPosts(list));
    }
}
