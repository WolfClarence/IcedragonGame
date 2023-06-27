package com.icedragongame.controller;

import com.icedragongame.common.R;
import com.icedragongame.entity.User;
import com.icedragongame.service.ReplyService;
import com.icedragongame.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName : GetUserInfoController  //类名
 * @Description : 根据user_name查询，把一个user类返回  //描述
 * @Author : wenrui //作者
 * @Date: 2023/6/27  16:07
 */
@RestController
@RequestMapping("/")
public class GetUserInfoController {
    @Resource
    private ReplyService replyService;
    @Resource
    private UserService userService;
    @GetMapping("/getUserInfo")
    public R<List<User>> getUserInfoByName(){
        return R.success(userService.list());
    }

}
