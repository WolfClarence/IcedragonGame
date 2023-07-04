package com.icedragongame.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icedragongame.dao.PostDao;
import com.icedragongame.dto.PagingDto;
import com.icedragongame.entity.Category;
import com.icedragongame.entity.Post;
import com.icedragongame.service.CategoryService;
import com.icedragongame.service.PostService;
import com.icedragongame.utils.MyBeanUtils;
import com.icedragongame.vo.PageVo;
import com.icedragongame.vo.PostForBigBlockVo;
import com.icedragongame.vo.PostForLittleBlockVO;
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
    CategoryService categoryService;

    @Override
    public List<PostForBigBlockVo> listForVO(AbstractWrapper query) {
        List<Post> list = list(query);
        List<PostForBigBlockVo> postVoList = new ArrayList<>();
        for (Post post : list) {
            PostForBigBlockVo postVo = getPostVoByPost(post);
            postVoList.add(postVo);
        }
        return postVoList;
    }

    @Override
    public PostForBigBlockVo getByIdForVO(int query) {
        Post post = getById(query);
        return getPostVoByPost(post);
    }
    public PostForBigBlockVo getPostVoByPost(Post post){
        Integer categoryId = post.getCategoryId();
        Category byId = categoryService.getById(categoryId);
        post.setCategory(byId.getCategoryName());
        return MyBeanUtils.beanCopy(post, PostForBigBlockVo.class);
    }

    @Override
    public PageVo<PostForBigBlockVo> pageForPostVO(PagingDto postPage, AbstractWrapper query) {
        Page<Post> page = new Page<>(postPage.getPage_indices(),postPage.getPage_num());
        Page<Post> pageForPost = page(page, query);
        List<Post> records = pageForPost.getRecords();
        List<PostForBigBlockVo> postVoList = records.stream().map(this::getPostVoByPost).collect(Collectors.toList());
        return new PageVo<>(pageForPost.getTotal(),postVoList);
    }

    @Override
    public PostForLittleBlockVO getLittlePostVoByPost(Post post) {
        Integer categoryId = post.getCategoryId();
        Category byId = categoryService.getById(categoryId);
        post.setCategory(byId.getCategoryName());
        return MyBeanUtils.beanCopy(post, PostForLittleBlockVO.class);
    }

    @Override
    public PageVo<PostForLittleBlockVO> pageForLittlePostVO(PagingDto postPage, AbstractWrapper query) {
        Page<Post> page = new Page<>(postPage.getPage_indices(),postPage.getPage_num());
        Page<Post> pageForPost = page(page, query);
        List<Post> records = pageForPost.getRecords();
        List<PostForLittleBlockVO> postVoList = records.stream().map(this::getLittlePostVoByPost).collect(Collectors.toList());
        return new PageVo<>(pageForPost.getTotal(),postVoList);
    }

    @Override
    public List<Post> sortPostByHot(List<Post> postList) {
        int i,j,first,second;
        Post medim;
        for(i =0;i<postList.size();i++){
            for(j=i;j<postList.size();j++){
                first = postList.get(i).getScanNum()+2*postList.get(i).getReplyNum();
                second =postList.get(j).getScanNum()+2*postList.get(j).getReplyNum();
                if(first<second){
                    medim = postList.get(i);
                    postList.set(i,postList.get(j));
                    postList.set(j,medim);
                }
            }

        }


        return postList;
    }
}

