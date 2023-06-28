package com.icedragongame.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icedragongame.entity.UserPost;
import com.icedragongame.mapper.UserPostMapper;
import com.icedragongame.service.UserPostService;
import org.springframework.stereotype.Service;

/**
 * @ClassName : UserPostServiceImpl  //类名
 * @Description : ...  //描述
 * @Author : wenrui //作者
 * @Date: 2023/6/27  18:02
 */
@Service
public class UserPostServiceImpl extends ServiceImpl<UserPostMapper, UserPost> implements UserPostService {
}
