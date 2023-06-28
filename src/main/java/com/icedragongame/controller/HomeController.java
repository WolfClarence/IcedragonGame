package com.icedragongame.controller;

import com.icedragongame.common.R;
import com.icedragongame.entity.Post;
import com.icedragongame.service.PostService;
import com.icedragongame.service.ReplyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api/home")
public class HomeController {

    @Resource
    private PostService postService;

    @Resource
    private ReplyService replyService;

    @GetMapping("/newGame")
    public R<List<Post>> getNewGame(){

    }

    @GetMapping("/hotgame")
    public R<List<Post>> getHotgame(){

    }
}
