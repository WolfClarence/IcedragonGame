package com.icedragongame.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icedragongame.entity.User;
import com.icedragongame.entity.UserPost;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserPostMapper extends BaseMapper<UserPost> {
}
