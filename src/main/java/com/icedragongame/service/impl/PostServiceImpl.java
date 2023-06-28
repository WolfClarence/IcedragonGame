package com.icedragongame.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.UserPost;
import com.icedragongame.mapper.PostMapper;
import com.icedragongame.service.PostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    @Resource
    private PostMapper postMapper;


    public Post selectPostById(Serializable postId) {
        return getBaseMapper().selectPostById(postId);
    }
}
