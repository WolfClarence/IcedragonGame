package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icedragongame.common.R;
import com.icedragongame.dto.LikesDto;
import com.icedragongame.dto.PagingDto;
import com.icedragongame.entity.Attentions;
import com.icedragongame.service.AttentionsService;
import com.icedragongame.service.PostService;
import com.icedragongame.utils.MyBeanUtils;
import com.icedragongame.vo.PostForLittleBlockVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    @ApiOperation(value = "(喜欢)(已完成)")
    public R<Object> addLikes(@RequestBody LikesDto likesDto){
        attentionsService.save(MyBeanUtils.beanCopy(likesDto,Attentions.class));
        return R.success();
    }
    @PostMapping("/delete")
    @ApiOperation(value = "(取消喜欢)(已完成)")
    public R<Object> delete(@RequestBody LikesDto likesDto){
        attentionsService.remove(new QueryWrapper<Attentions>().eq("post_id",likesDto.getPost_id()).eq("username",likesDto.getUsername()));
        return R.success();
    }
    @GetMapping("/getAll/{username}")
    @ApiOperation(value = "(得到一个人所有是喜欢的作品,分页)(未完成)")
    public R<List<PostForLittleBlockVO>> getAll(@PathVariable("username")String username, @RequestBody PagingDto pagingDto){
        return null;
    }

}
