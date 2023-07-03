package com.icedragongame.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icedragongame.dao.AttentionsDao;
import com.icedragongame.entity.Attentions;
import com.icedragongame.service.AttentionsService;
import org.springframework.stereotype.Service;

/**
 * (Attentions)表服务实现类
 *
 * @author makejava
 * @since 2023-07-03 12:20:17
 */
@Service("attentionsService")
public class AttentionsServiceImpl extends ServiceImpl<AttentionsDao, Attentions> implements AttentionsService {

}

