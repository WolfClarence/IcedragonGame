package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.icedragongame.common.R;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.User;
import com.icedragongame.mapper.ReplyMapper;
import com.icedragongame.service.HomeService;
import com.icedragongame.service.PostService;
import com.icedragongame.service.ReplyService;
import com.icedragongame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @Resource
    private HomeService homeService;

    @GetMapping("/newGame")
    public R<List<Post>> getNewGame(){
        List<Post> list = homeService.getNewGame();
        return R.success(list);
    }

    @GetMapping("/hotgame")
    public R<List<Post>> getHotgame(){
        List<Post> list = homeService.getHotgame();
        return R.success(list);
    }
}
