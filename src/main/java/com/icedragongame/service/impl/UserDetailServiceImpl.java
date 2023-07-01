package com.icedragongame.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icedragongame.common.myenum.SystemError;
import com.icedragongame.dto.LoginUserDetails;
import com.icedragongame.entity.User;
import com.icedragongame.exception.SystemExceptionBySelf;
import com.icedragongame.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @auther: gengxuelong
 * @date:2023/6/29 12:55
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);
        User user = userService.getOne(queryWrapper);
        //判断是否查到用户  如果没查到抛出异常
        if(Objects.isNull(user)){
            throw new SystemExceptionBySelf(SystemError.USER_NOT_FOUND);
        }
        //返回用户信息
        return new LoginUserDetails(user);
    }
}
