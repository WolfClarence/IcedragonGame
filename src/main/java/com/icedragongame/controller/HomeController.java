package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icedragongame.common.R;
import com.icedragongame.entity.Post;
import com.icedragongame.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api/home")
public class HomeController {

    @Resource
    private PostService postService;

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
        QueryWrapper<Post> query = new QueryWrapper<>();
        query.eq("username",username);
        List<Post> list = postService.list(query);
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
