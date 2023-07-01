package com.icedragongame.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icedragongame.dao.PostDao;
import com.icedragongame.dto.PagingDto;
import com.icedragongame.entity.Category;
import com.icedragongame.entity.Game;
import com.icedragongame.entity.Post;
import com.icedragongame.service.CategoryService;
import com.icedragongame.service.GameService;
import com.icedragongame.service.PostService;
import com.icedragongame.utils.MyBeanUtils;
import com.icedragongame.vo.PageVo;
import com.icedragongame.vo.PostVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章表(Post)表服务实现类
 *
 * @author makejava
 * @since 2023-07-02 01:32:05
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostDao, Post> implements PostService {

    @Resource
    GameService gameService;
    @Resource
    CategoryService categoryService;

    @Override
    public List<PostVo> listForVO(AbstractWrapper query) {
        List<Post> list = list(query);
        List<PostVo> postVoList = new ArrayList<>();
        for (Post post : list) {
            PostVo postVo = getPostVoByPost(post);
            postVoList.add(postVo);
        }
        return postVoList;
    }

    @Override
    public PostVo getByIdForVO(int query) {
        Post post = getById(query);
        return getPostVoByPost(post);
    }
    public PostVo getPostVoByPost(Post post){
        PostVo postVo = MyBeanUtils.beanCopy(post, PostVo.class);
        Game game = gameService.getById(post.getGameId());
        postVo.setGame_name(game.getGameName());
        Category category = categoryService.getById(game.getCategoryId());
        postVo.setCategory(category.getCategoryName());
        return postVo;
    }

    @Override
    public PageVo<PostVo> pageForPostVO(PagingDto postPage, AbstractWrapper query) {
        Page<Post> page = new Page<>(postPage.getPage_indices(),postPage.getPage_num());
        Page<Post> pageForPost = page(page, query);
        List<Post> records = pageForPost.getRecords();
        List<PostVo> postVoList = records.stream().map(this::getPostVoByPost).collect(Collectors.toList());
        return new PageVo<>(pageForPost.getTotal(),postVoList);
    }
}

