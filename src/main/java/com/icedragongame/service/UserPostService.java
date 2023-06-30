package com.icedragongame.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.icedragongame.entity.Reply;
import com.icedragongame.entity.UserPost;
import com.icedragongame.utils.MyBeanUtils;

public interface UserPostService extends IService<UserPost> {
    @Override
    default boolean updateById(UserPost source) {
        UserPost entity = getById(source.getPostId());
        MyBeanUtils.updateBeanBySource(source, entity);
        return IService.super.updateById(entity);
    }
    @Override
    default boolean update(UserPost entity, Wrapper<UserPost> updateWrapper) {
        UserPost entityFromBase = getById(entity.getPostId());
        entity  = MyBeanUtils.updateBeanBySource(entity,entityFromBase);
        return IService.super.update(entity, updateWrapper);
    }
}
