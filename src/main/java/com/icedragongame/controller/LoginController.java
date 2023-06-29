package com.icedragongame.controller;

import com.icedragongame.common.MyBeanUtils;
import com.icedragongame.common.R;
import com.icedragongame.dto.UserDto;
import com.icedragongame.entity.User;
import com.icedragongame.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @auther: gengxuelong
 * @date: 2023/6/29 12:38
 */
@RestController
public class LoginController {
    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public R<Object> login(@RequestBody UserDto userDto){
        User user = MyBeanUtils.beanCopy(userDto, User.class,true);
        return loginService.login(user);
    }
    @PostMapping("/logout")
    public R<Object> logout(){
        return loginService.logout();
    }

    @PostMapping("/register")
    public R<Object> register(@RequestBody UserDto userDto){
        User user = MyBeanUtils.beanCopy(userDto, User.class,true);
        return loginService.register(user);
    }

}
