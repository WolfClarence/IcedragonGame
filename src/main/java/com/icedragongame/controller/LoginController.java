package com.icedragongame.controller;

import com.icedragongame.utils.MyBeanUtils;
import com.icedragongame.common.R;
import com.icedragongame.dto.UserDto;
import com.icedragongame.entity.User;
import com.icedragongame.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
public class LoginController {
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
    private LoginService loginService;

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
    @PostMapping("/login")
    public R<Object> login(@RequestBody UserDto userDto){
        User user = MyBeanUtils.beanCopy(userDto, User.class,true);
        return loginService.login(user);
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
    @PostMapping("/logout")
    public R<Object> logout(){
        return loginService.logout();
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
    @PostMapping("/register")
    public R<Object> register(@RequestBody UserDto userDto){
        User user = MyBeanUtils.beanCopy(userDto, User.class,true);
        return loginService.register(user);
    }

}
