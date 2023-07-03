package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.icedragongame.common.R;
import com.icedragongame.dto.PostDto;
import com.icedragongame.entity.Category;
import com.icedragongame.entity.Post;
import com.icedragongame.service.CategoryService;
import com.icedragongame.service.PostService;
import com.icedragongame.service.ReplyService;
import com.icedragongame.utils.MyBeanUtils;
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
    public R<String> sendPost(@RequestBody PostDto postDto){//传入分类的名字
        Post post = MyBeanUtils.beanCopy(postDto,Post.class,true);
        Category category = categoryService.getOne(new LambdaUpdateWrapper<Category>().eq(Category::getCategoryName,post.getCategory()));
        if(category==null){
            category= new Category();
            category.setCategoryName(post.getCategory());
            categoryService.save(category);
            category = categoryService.getOne(new LambdaUpdateWrapper<Category>().eq(Category::getCategoryName,post.getCategory()));
        }
        post.setCategoryId(category.getId());
        postService.save(post);
        return R.success("帖子写入成功！");//postService.save(post)
    }


}
