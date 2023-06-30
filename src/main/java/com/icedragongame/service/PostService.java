package com.icedragongame.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.icedragongame.entity.Post;
import com.icedragongame.utils.MyBeanUtils;

public interface PostService extends IService<Post> {
    //Post selectPostById(Serializable postId);

    @Override
    default boolean updateById(Post post) {
        Post entity = getById(post.getPostId());
        post  = MyBeanUtils.updateBeanBySource(post,entity);
        return IService.super.updateById(post);
    }

    @Override
    default boolean update(Post entity, Wrapper<Post> updateWrapper) {
        Post entityFromBase = getById(entity.getPostId());
        entity  = MyBeanUtils.updateBeanBySource(entity,entityFromBase);
        return IService.super.update(entity, updateWrapper);
    }
}
