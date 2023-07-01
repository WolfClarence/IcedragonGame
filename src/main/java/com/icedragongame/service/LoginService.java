package com.icedragongame.service;

import com.icedragongame.entity.User;
import com.icedragongame.vo.LoginVo;

/**
 * @auther: gengxuelong
 * @date: 2023/6/29 12:43
 */
public interface LoginService {
    LoginVo login(User user);

    void logout();

    boolean register(User user);
}
