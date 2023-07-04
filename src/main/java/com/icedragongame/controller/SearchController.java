package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icedragongame.common.R;
import com.icedragongame.constant.ConstantBySelf;
import com.icedragongame.dto.PagingDto;
import com.icedragongame.entity.Category;
import com.icedragongame.entity.Post;
import com.icedragongame.myenum.SystemError;
import com.icedragongame.service.CategoryService;
import com.icedragongame.service.PostService;
import com.icedragongame.vo.PageVo;
import com.icedragongame.vo.PostForBigBlockVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     project: snow_dragonGame blogSystem
 *
 *  该类名称为:
 *     <name>
 *
 *  该类作用为:
 *   <effect>
 *
 *@author py
 *
 */
@RestController
@RequestMapping(value = "/api")
public class SearchController {

    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该参数名称为:
     *     <name>
     *
     *  该参数描述为:
     *   <effect>
     *
     */
    @Resource
    private PostService postService;

    @Resource
    private CategoryService categoryService;
    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该方法名称为:
     *     <name>
     *
     *  该方法作用为:
     *   <effect>
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    @GetMapping("/search")
    @ApiOperation("(搜索功能,分页)(未完成)当有category字段时,要求数据必须是该类" +
            "当有key时,要求数据可以是title中含有,也可以game name含有" +
            "sort为0时按时间降序排序,否则按热度降序排序")
    public R<Object> search(@RequestParam(value = "sort",required = false) Integer sort,
                                             @RequestParam(value = "category",required = false) String category,
                                             @RequestParam(value = "key_word",required = false) String keyWord,
                                             @RequestBody PagingDto pagingDto
                                             ){
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        //排序类型
        if(sort == null) sort = ConstantBySelf.ORDER_BY_TIME;//未选类别，默认为按时间
        if(sort.equals(ConstantBySelf.ORDER_BY_TIME)){//按时间
            queryWrapper.orderByDesc("build_time");
        }else{//按热度
            queryWrapper.orderByDesc("2 * reply_num + scan_num");
        }
        //游戏类别
        if(!(category == null || category.isEmpty())){//类别不为空
            Category category1= categoryService.getOne(new QueryWrapper<Category>().eq("category_name",category));
            if(category1==null){
                return R.error(SystemError.NO_THIS_CATEGORY);
            }
            int category_id = category1.getId();
            queryWrapper.eq("category_id",category_id);
        }
        //关键字
        if(!(keyWord == null || keyWord.isEmpty())){//类别不为空
            queryWrapper.like("title",keyWord).or().like("game_name",keyWord);
        }
        PageVo<PostForBigBlockVo> postVoPageVo = postService.pageForPostVO(pagingDto, queryWrapper);
        return R.success((postVoPageVo));
    }


}
