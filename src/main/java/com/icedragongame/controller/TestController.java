package com.icedragongame.controller;

import com.icedragongame.common.R;
import com.icedragongame.entity.User;
import com.icedragongame.service.PostService;
import com.icedragongame.service.ReplyService;
import com.icedragongame.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
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
 *@author gengxuelong
 *
 */
@RestController
@RequestMapping("/test")
public class TestController {
//如来来没来
    @Resource
    private ReplyService replyService;
    @Resource
    private UserService userService;
    @Resource
    private PostService postService;

    @GetMapping("/getuser")
    public R<List<User>> getUserByUsername(){
        return R.success(userService.list());
    }

    @GetMapping("/test")
    public R<String> test(){
        return R.success("xxxx");
    }

}
