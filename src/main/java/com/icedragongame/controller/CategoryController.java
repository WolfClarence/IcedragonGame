package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icedragongame.common.R;
import com.icedragongame.entity.Category;
import com.icedragongame.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gengxuelong
 * @date 2023/7/2 19:28
 */
@RestController
@RequestMapping("/category")
@Api(value = "category一些的一些功能")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/all")
    @ApiOperation(value = "得到所有游戏分类(完成)",notes = "得到所有游戏分类")
    public R<Object> getAll (){
        List<Category> list = categoryService.list();

        return R.success(list);
    }
    @GetMapping("/part/{num}")
    @ApiOperation(value = "得到所有游戏分类(完成)",notes = "得到所有游戏分类")
    public R<Object> getPart (@PathVariable String num){
        List<Category> list = categoryService.list(new QueryWrapper<Category>().last("limit "+num));

        return R.success(list);
    }
}
