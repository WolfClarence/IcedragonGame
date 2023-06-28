package com.icedragongame.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icedragongame.entity.Reply;
import com.icedragongame.mapper.ReplyMapper;
import com.icedragongame.service.ReplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements ReplyService {


    @Resource
    private ReplyMapper replyMapper;

//    public Reply selectReplyById(Serializable replyId) {
//        return replyMapper.selectReplyById(replyId);
//    }
}
