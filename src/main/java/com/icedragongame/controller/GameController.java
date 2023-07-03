package com.icedragongame.controller;

import com.icedragongame.common.R;
import com.icedragongame.vo.GameVo;
import com.icedragongame.entity.Game;
import com.icedragongame.service.CategoryService;
import com.icedragongame.service.GameService;
import com.icedragongame.utils.MyBeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gengxuelong
 * @date 2023/7/2 19:31
 */
@RestController
@RequestMapping("/game")
@Api(value = "game一些的一些功能")
public class GameController {
    @Resource
    private GameService gameService;
    @Resource
    private CategoryService categoryService;

    @PostMapping("/all")
    @ApiOperation(value = "得到所有游戏数据",notes = "得到所有游戏数据")
    public R<Object> getAll (){
        List<Game> list = gameService.list();
        List<GameVo> collect = list.stream().map(
                game -> (MyBeanUtils.beanCopy(game, GameVo.class))
                        .setCategory_name((categoryService.getById(game.getCategoryId()).getCategoryName()))
        ).collect(Collectors.toList());
        return R.success(collect);
    }
}
