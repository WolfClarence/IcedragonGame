package com.icedragongame.service.impl;

import com.icedragongame.entity.Post;
import com.icedragongame.mapper.PostMapper;
import com.icedragongame.service.HomeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Resource
    private PostMapper postMapper;
    @Override
    public List<Post> getNewGame() {
        return postMapper.getNewGame();
    }

    @Override
    public List<Post> getHotgame() {
        return postMapper.getHotgame();
    }
}
