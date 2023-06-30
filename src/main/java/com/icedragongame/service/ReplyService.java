package com.icedragongame.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.Reply;
import com.icedragongame.utils.MyBeanUtils;

public interface ReplyService extends IService<Reply> {
    //Reply selectReplyById(Serializable replyId);
    @Override
    default boolean updateById(Reply source) {
        Reply entity = getById(source.getReplyId());
        MyBeanUtils.updateBeanBySource(source, entity);
        return IService.super.updateById(entity);
    }
    @Override
    default boolean update(Reply entity, Wrapper<Reply> updateWrapper) {
        Reply entityFromBase = getById(entity.getReplyId());
        entity  = MyBeanUtils.updateBeanBySource(entity,entityFromBase);
        return IService.super.update(entity, updateWrapper);
    }
}
