package com.icedragongame.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icedragongame.constant.ConstantBySelf;
import com.icedragongame.dao.PostDao;
import com.icedragongame.dto.PagingDto;
import com.icedragongame.entity.*;
import com.icedragongame.service.*;
import com.icedragongame.utils.MyBeanUtils;
import com.icedragongame.utils.MyRedisUtils;
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
    @Resource
    UserService userService;
    @Resource
    ReplyService replyService;
    @Resource
    MyRedisUtils myRedisUtils;
    @Resource
    AttentionsService attentionsService ;

    @Override
    public List<PostForBigBlockVo> listForVO(AbstractWrapper query) {
        List<Post> list = this.list(query);
        System.out.println("list========>"+list);
        List<PostForBigBlockVo> postVoList = new ArrayList<>();
        for (Post post : list) {
            PostForBigBlockVo postVo = getBigBlockPostVoByPost(post);
            postVoList.add(postVo);
        }
        return postVoList;
    }

    @Override
    public PostForBigBlockVo getByIdForVO(int query) {
        Post post = getById(query);
        return getBigBlockPostVoByPost(post);
    }
    public PostForBigBlockVo getBigBlockPostVoByPost(Post post){
        System.out.println("getBigBlockPostVoByPost"+post);
        Integer categoryId = post.getCategoryId();
        Category byId = categoryService.getById(categoryId);
        //设置类别
        post.setCategory(byId.getCategoryName());
        PostForBigBlockVo postForBigBlockVo = MyBeanUtils.beanCopy(post, PostForBigBlockVo.class);
        System.out.println("getBigBlockPostVoByPost"+postForBigBlockVo);
        //设置用户头像
        User byId1 = userService.getById(postForBigBlockVo.getUsername());
        postForBigBlockVo.setCreate_username_image_url(byId1.getImageUrl());
        //设置回复数字
        List<Reply> list = replyService.list(new LambdaUpdateWrapper<Reply>().eq(Reply::getPostId, post.getId()));
        postForBigBlockVo.setReply_num(list.size());
        //设置观看人数,将redis里的放尽进来
        Integer mapValue = myRedisUtils.getMapValue(ConstantBySelf.REDIS_KEY_SCANS_POST, post.getId());
        postForBigBlockVo.setScan_num(mapValue);
        //设置喜欢数量
        List<Attentions> attentions = attentionsService.list(new LambdaUpdateWrapper<Attentions>().eq(Attentions::getPostId, post.getId()));
        postForBigBlockVo.setLikes_num(attentions.size());
        return postForBigBlockVo;
    }

    @Override
    public PageVo<PostForBigBlockVo> pageForPostVO(PagingDto postPage, AbstractWrapper query) {
        Page<Post> page = new Page<>(postPage.getPage_indices(),postPage.getPage_num());
        Page<Post> pageForPost = page(page, query);
        List<Post> records = pageForPost.getRecords();
        List<PostForBigBlockVo> postVoList = records.stream().map(this::getBigBlockPostVoByPost).collect(Collectors.toList());
        return new PageVo<>(pageForPost.getTotal(),postVoList);
    }

    @Override
    public PostForLittleBlockVO getLittlePostVoByPost(Post post) {
        Integer categoryId = post.getCategoryId();
        Category byId = categoryService.getById(categoryId);
        post.setCategory(byId.getCategoryName());
        PostForLittleBlockVO postVo = MyBeanUtils.beanCopy(post, PostForLittleBlockVO.class);

        User byId1 = userService.getById(postVo.getUsername());
        if(byId1 != null) {
            postVo.setCreate_username_image_url(byId1.getImageUrl());
        }

        return postVo;
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

