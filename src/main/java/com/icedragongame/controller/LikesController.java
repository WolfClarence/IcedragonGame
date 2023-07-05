package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icedragongame.common.R;
import com.icedragongame.dto.LikesDto;
import com.icedragongame.dto.PagingDto;
import com.icedragongame.entity.Attentions;
import com.icedragongame.service.AttentionsService;
import com.icedragongame.service.PostService;
import com.icedragongame.utils.MyBeanUtils;
import com.icedragongame.vo.PageVo;
import com.icedragongame.vo.PostForBigBlockVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gengxuelong
 * @date 2023/7/3 12:21
 */
@RestController()
@RequestMapping("/likes")
public class LikesController {
    @Resource
    AttentionsService attentionsService;

    @Resource
    PostService postService;

    @PostMapping("/add")
    @ApiOperation(value = "(喜欢)(已完成测试)")
    public R<Object> addLikes(@RequestBody LikesDto likesDto){
        attentionsService.save(MyBeanUtils.beanCopy(likesDto,Attentions.class));
        return R.success();
    }
    @PostMapping("/delete")
    @ApiOperation(value = "(取消喜欢)(已完成测试)")
    public R<Object> delete(@RequestBody LikesDto likesDto){
        attentionsService.remove(new QueryWrapper<Attentions>().eq("post_id",likesDto.getPost_id()).eq("username",likesDto.getUsername()));
        return R.success();
    }
    @PostMapping("/getAll/{username}")
    @ApiOperation(value = "(得到一个人所有是喜欢的作品,分页)(已完成测试)")
    public R<PageVo<PostForBigBlockVo>> getAll(@PathVariable("username")String username, @RequestBody PagingDto pagingDto){
        QueryWrapper<Attentions> queryWrapper = new QueryWrapper<>();
        List<Attentions> username1 = attentionsService.list(new QueryWrapper<Attentions>().eq("username", username));
        List<Integer> postIds = username1.stream().map(Attentions::getPostId).collect(Collectors.toList());
        if(postIds.size() != 0){
            queryWrapper.in("id",postIds);
            PageVo<PostForBigBlockVo> postForBigBlockVoPageVo = postService.pageForPostVO(pagingDto,queryWrapper);
            return R.success(postForBigBlockVoPageVo);
        }
        return R.success(new PageVo<>());

    }

    @PostMapping("/getAll/{username}/{num}")
    @ApiOperation(value = "(得到一个人所有是喜欢的作品,指定数量)(已完成)")
    public R<List<PostForBigBlockVo>> getAllByNum(@PathVariable("username")String username,@PathVariable("num")int num){
        QueryWrapper<Attentions> queryWrapper = new QueryWrapper<>();
        List<Attentions> username1 = attentionsService.list(new QueryWrapper<Attentions>().eq("username", username));
        List<Integer> postIds = username1.stream().map(Attentions::getPostId).collect(Collectors.toList());
        if(postIds.size() != 0){
            queryWrapper.in("id",postIds);
            List<PostForBigBlockVo> postForBigBlockVoPageVo = postService.listForVO(queryWrapper);
            return R.success(postForBigBlockVoPageVo);
        }

        return R.success(new ArrayList<>());
    }

}
