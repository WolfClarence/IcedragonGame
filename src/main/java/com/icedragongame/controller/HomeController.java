package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icedragongame.common.R;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.UserPost;
import com.icedragongame.service.PostService;
import com.icedragongame.service.UserPostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/home")
public class HomeController {

    @Resource
    private PostService postService;

    @Resource
    private UserPostService userPostService;

    @GetMapping("/newGame")
    public R<List<Post>> newGame(){
        QueryWrapper<Post> query = new QueryWrapper<>();
        query.orderByDesc("build_time");
        query.last("limit 10");
        List<Post> list = postService.list(query);
        return R.success(list);
    }

    @GetMapping("/hotgame")
    public R<List<Post>> hotgame() {
        QueryWrapper<Post> query = new QueryWrapper<>();
        query.orderByDesc("reply_num", "scan_num");
        query.last("limit 10");
        List<Post> list = postService.list(query);
        return R.success(list);
    }

    @GetMapping("/followedGame/{username}")
    public R<List<Post>> followedGame(@PathVariable(value = "username") String username) {
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
        return R.success(list);
    }

    @GetMapping("/category/{category}")
    public R<List<Post>> category(@PathVariable(value = "category") String category) {
        QueryWrapper<Post> query = new QueryWrapper<>();
        query.eq("category",category);
        List<Post> list = postService.list(query);
        return R.success(list);
    }
}
