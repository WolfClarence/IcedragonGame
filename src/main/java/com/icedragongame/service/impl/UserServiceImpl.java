package com.icedragongame.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icedragongame.dao.UserDao;
import com.icedragongame.entity.User;
import com.icedragongame.service.UserService;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2023-07-02 01:33:17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}

