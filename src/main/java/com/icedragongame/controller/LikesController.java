package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icedragongame.common.R;
import com.icedragongame.dto.LikesDto;
import com.icedragongame.entity.Attentions;
import com.icedragongame.entity.Post;
import com.icedragongame.service.AttentionsService;
import com.icedragongame.service.PostService;
import com.icedragongame.utils.MyBeanUtils;
import com.icedragongame.vo.PostVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gengxuelong
 * @date 2023/7/3 12:21
 */
@RestController("likes")
public class LikesController {
    @Resource
    AttentionsService attentionsService;

    @Resource
    PostService postService;

    @PostMapping("/add")
    public R<Object> addLikes(@RequestBody LikesDto likesDto){
        attentionsService.save(MyBeanUtils.beanCopy(likesDto,Attentions.class));
        return R.success();
    }
    @PostMapping("/delete")
    public R<Object> delete(@RequestBody LikesDto likesDto){
        attentionsService.remove(new QueryWrapper<Attentions>().eq("post_id",likesDto.getPost_id()).eq("username",likesDto.getUsername()));
        return R.success();
    }
    @GetMapping("/getAll/{username}")
    public R<Object> getAll(@PathVariable("username")String username){
        List<Attentions> attentions = attentionsService.list(new QueryWrapper<Attentions>().eq("username", username));
        List<PostVo> posts = postService.listForVO(new QueryWrapper<Post>().in("id", attentions.stream().map(Attentions::getPostId).collect(Collectors.toList())));
        return R.success(posts);
    }

}
