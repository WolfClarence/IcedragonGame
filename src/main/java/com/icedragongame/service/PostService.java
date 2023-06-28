package com.icedragongame.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.icedragongame.entity.Post;

import java.io.Serializable;
import java.util.List;

public interface PostService extends IService<Post> {
    Post selectPostById(Serializable postId);

}
