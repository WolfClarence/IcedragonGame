package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icedragongame.common.R;
import com.icedragongame.entity.Category;
import com.icedragongame.entity.Game;
import com.icedragongame.entity.Post;
import com.icedragongame.service.CategoryService;
import com.icedragongame.service.GameService;
import com.icedragongame.service.PostService;
import com.icedragongame.service.ReplyService;
import com.icedragongame.utils.MyBeanUtils;
import com.icedragongame.vo.PostDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * <p>
 *     project: snow_dragonGame blogSystem
 *
 *  该类名称为:
 *     <name>
 *         SendPostController
 *
 *  该类作用为:
 *   <effect>
 *       发帖人完成帖子信息输入后，将新帖子的信息写入数据库  //描述
 *
 *@author wenrui
 *
 */
@RestController
@RequestMapping("/api")
@Api(value = "发帖人完成帖子信息输入后，将新帖子的信息写入数据库 ")
public class SendPostController {

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
    private ReplyService replyService;

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
    @PostMapping ("/postingPage")
    @ApiOperation("将文章信息写入数据库")
    public R<String> sendPost(@RequestBody PostDto postDto){
        String  game_name= postDto.getGame_name();
        Game game = gameService.getOne(new QueryWrapper<Game>().eq("game_name",game_name));
        int game_id  =  -1;
        if(game==null){
            //新增游戏
            Game game1 = new Game();
            game1.setGameName(game_name);
            game1.setGameDescribe(postDto.getGame_describe());
            int categoryId = -1;
            com.icedragongame.entity.Category category = categoryService.getOne(new QueryWrapper<Category>().eq("category_name",postDto.getCategory()));
            if(category==null){
                //新增category
                Category category1 = new Category();
                category1.setCategoryName(postDto.getCategory());
                categoryService.save(category1);
            }
            Category category2 = categoryService.getOne(new QueryWrapper<Category>().eq("category_name", postDto.getCategory()));
            categoryId = category2.getId();
            game1.setCategoryId(categoryId);
            gameService.save(game1);
        }
        Game game_name1 = gameService.getOne(new QueryWrapper<Game>().eq("game_name", game_name));
        game_id = game_name1.getId();
//        System.out.println(postDto.toString());
        Post post = MyBeanUtils.beanCopy(postDto,Post.class,true);
//        System.out.println(post.toString());
        post.setGameId(game_id);
        postService.save(post);
        return R.success("帖子写入成功！");//postService.save(post)
    }


}
