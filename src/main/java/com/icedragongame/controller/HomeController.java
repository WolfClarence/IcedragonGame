package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.icedragongame.common.R;
import com.icedragongame.myenum.SystemError;
import com.icedragongame.dto.PagingDto;
import com.icedragongame.entity.Category;
import com.icedragongame.entity.Game;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.User;
import com.icedragongame.service.CategoryService;
import com.icedragongame.service.GameService;
import com.icedragongame.service.PostService;
import com.icedragongame.service.UserService;
import com.icedragongame.vo.PageVo;
import com.icedragongame.vo.PostVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
@RequestMapping(value = "/api/home")
public class HomeController {

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
    private GameService gameService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private UserService userService;



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
    @GetMapping("/newGame/{num}")
    @ApiOperation(value = "得到最新的若干个文章,默认值为10")
    public R<List<PostVo>> newGame(@PathVariable("num") Integer num){
        num = num==null||num<0?10:num;
        QueryWrapper<Post> query = new QueryWrapper<>();
        query.orderByDesc("build_time");
        query.last("limit "+num);
        List<PostVo> postVoList = postService.listForVO(query);
        return R.success(postVoList);
    }

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
    @GetMapping("/hotgame/{num}")
    @ApiOperation("得到最热的游戏文章,默认值为10,可设置文章数量")
    public R<List<PostVo>> hotgame(@PathVariable("num") Integer num) {
        num = (num==null||num<0)? 0 :num;
        QueryWrapper<Post> query = new QueryWrapper<>();
        query.orderByDesc("2 * reply_num + scan_num");
        query.last("limit "+num);
        List<PostVo> list = postService.listForVO(query);
        return R.success(list);
    }

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
    @PostMapping("/followedGame/{username}")
    @ApiOperation("通过用户名得到该用户的文章,方法为分页")
    public R<Object> followedGame(@PathVariable(value = "username") String username, @RequestBody PagingDto pagingDto) {
        User user = userService.getById(username);
        if(user==null){
            return R.error(SystemError.USER_NOT_FOUND);
        }
        LambdaQueryWrapper<Post> upQuery = new LambdaQueryWrapper<>();
        upQuery.eq(Post::getUsername,username);
        PageVo<PostVo> postVoPageVo = postService.pageForPostVO(pagingDto, upQuery);
        return R.success(postVoPageVo);
    }

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
    @PostMapping("/category/{category}")
    @ApiOperation(value = "通过类别得到文章列表,方法为分页")
    public R<Object> category(@PathVariable(value = "category") String category, @RequestBody PagingDto pagingDto) {
        LambdaQueryWrapper<Game> wrapperForGame = new LambdaQueryWrapper<>();
        Category category1 = categoryService.getOne( new LambdaUpdateWrapper<Category>().eq(Category::getCategoryName,category));
        if(category1==null){
            return R.error(SystemError.NO_THIS_CATEGORY);
        }
        wrapperForGame.eq(Game::getCategoryId,category1.getId());
        List<Game> gameList = gameService.list(wrapperForGame);
        QueryWrapper<Post> query = new QueryWrapper<>();
        System.out.println(gameList);
        List<Integer> gameIds = gameList.stream().map(Game::getId).collect(Collectors.toList());
        if(gameIds.size()==0){
            gameIds.add(-1);
        }
        query.in("game_id",gameIds);
        PageVo<PostVo> postVoPageVo =postService.pageForPostVO(pagingDto,query);
        return R.success(postVoPageVo);
    }
}
