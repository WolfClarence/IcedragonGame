package com.icedragongame.service;

import com.icedragongame.common.R;
import com.icedragongame.entity.User;

/**
 * @auther: gengxuelong
 * @date: 2023/6/29 12:43
 */
public interface LoginService {
    public R<Object> login(User user);

    R<Object> logout();
}
