package com.icedragongame.controller;

import com.icedragongame.common.BeanConvertUtils;
import com.icedragongame.common.R;
import com.icedragongame.dto.UserDto;
import com.icedragongame.entity.User;
import com.icedragongame.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: gengxuelong
 * @date: 2023/6/29 12:38
 */
@RestController
public class LoginController {
    private LoginService loginService;

    @PostMapping("/login")
    public R<Object> login(@RequestBody UserDto userDto){
        User user = BeanConvertUtils.beanCopy(userDto, User.class,true);
        return loginService.login(user);
    }
    @PostMapping("/logout")
    public R<Object> logout(){
        return loginService.logout();
    }

}
