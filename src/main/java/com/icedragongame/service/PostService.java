package com.icedragongame.service;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.icedragongame.dto.PagingDto;
import com.icedragongame.entity.Post;
import com.icedragongame.vo.PageVo;
import com.icedragongame.vo.PostVo;

import java.util.List;

/**
 * 文章表(Post)表服务接口
 *
 * @author makejava
 * @since 2023-07-02 01:32:05
 */
public interface PostService extends IService<Post> {

    List<PostVo> listForVO(AbstractWrapper query);
    PostVo getByIdForVO(int query);
    PostVo getPostVoByPost(Post post);

    PageVo<PostVo> pageForPostVO(PagingDto postPage, AbstractWrapper query);
}

