package com.icedragongame.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.icedragongame.entity.User;
import com.icedragongame.entity.UserPost;
import com.icedragongame.utils.MyBeanUtils;

public interface UserService extends IService<User> {
    @Override
    default boolean updateById(User source) {
        User entity = getById(source.getUsername());
        MyBeanUtils.updateBeanBySource(source, entity);
        return IService.super.updateById(entity);
    }

    @Override
    default boolean update(User entity, Wrapper<User> updateWrapper) {
        User entityFromBase = getById(entity.getUsername());
        entity  = MyBeanUtils.updateBeanBySource(entity,entityFromBase);
        return IService.super.update(entity, updateWrapper);
    }
}
