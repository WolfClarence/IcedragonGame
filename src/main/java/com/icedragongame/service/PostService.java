package com.icedragongame.service;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.icedragongame.dto.PagingDto;
import com.icedragongame.entity.Post;
import com.icedragongame.vo.PageVo;
import com.icedragongame.vo.PostForBigBlockVo;
import com.icedragongame.vo.PostForLittleBlockVO;

import java.util.List;

/**
 * 文章表(Post)表服务接口
 *
 * @author makejava
 * @since 2023-07-02 01:32:05
 */
public interface PostService extends IService<Post> {

    List<PostForBigBlockVo> listForVO(AbstractWrapper query);
    PostForBigBlockVo getByIdForVO(int query);
    PostForBigBlockVo getPostVoByPost(Post post);

    PageVo<PostForBigBlockVo> pageForPostVO(PagingDto postPage, AbstractWrapper query);

    PostForLittleBlockVO getLittlePostVoByPost(Post post);
    PageVo<PostForLittleBlockVO> pageForLittlePostVO(PagingDto postPage, AbstractWrapper query);
}

