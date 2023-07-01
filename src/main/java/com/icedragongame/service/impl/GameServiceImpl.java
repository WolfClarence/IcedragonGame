package com.icedragongame.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icedragongame.dao.GameDao;
import com.icedragongame.entity.Game;
import com.icedragongame.service.GameService;
import org.springframework.stereotype.Service;

/**
 * (Game)表服务实现类
 *
 * @author makejava
 * @since 2023-07-02 01:30:25
 */
@Service
public class GameServiceImpl extends ServiceImpl<GameDao, Game> implements GameService {

}

