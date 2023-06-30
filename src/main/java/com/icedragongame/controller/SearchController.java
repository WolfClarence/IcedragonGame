package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icedragongame.common.R;
import com.icedragongame.entity.Post;
import com.icedragongame.service.PostService;
import com.icedragongame.vo.BriefPostVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class SearchController {

    @Resource
    private PostService postService;

    @GetMapping("/search")
    public R<List<BriefPostVo>> search(@RequestParam(value = "sort",required = false) Integer sort,
                                @RequestParam(value = "category",required = false) String category,
                                @RequestParam(value = "key_word",required = false) String keyWord){
        QueryWrapper<Post> query = new QueryWrapper<>();
        //排序类型
        if(sort == null) sort = new Integer(0);//未选类别，默认为按时间
        if(sort.equals(0)){//按时间
            query.orderByDesc("build_time");
        }else{//按热度
            query.orderByDesc("2 * reply_num + scan_num");
        }

        //游戏类别
        if(!(category == null || category.isEmpty())){//类别不为空
            query.eq("category",category);
        }

        //关键字
        if(!(keyWord == null || keyWord.isEmpty())){//类别不为空
            query.like("game_name",keyWord);
        }
        List<Post> list = postService.list(query);
        return R.success(BriefPostVo.getBPVbyPosts(list));
    }
}
