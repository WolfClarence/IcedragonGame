package com.icedragongame.service;

import com.icedragongame.entity.Post;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface HomeService {

    public List<Post> getNewGame();

    public List<Post> getHotgame();
}
