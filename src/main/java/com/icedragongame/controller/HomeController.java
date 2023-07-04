package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.icedragongame.common.R;
import com.icedragongame.dto.PagingDto;
import com.icedragongame.entity.Category;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.User;
import com.icedragongame.myenum.SystemError;
import com.icedragongame.service.CategoryService;
import com.icedragongame.service.PostService;
import com.icedragongame.service.UserService;
import com.icedragongame.vo.PageVo;
import com.icedragongame.vo.PostForBigBlockVo;
import com.icedragongame.vo.PostForLittleBlockVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    public R<List<PostForBigBlockVo>> newGame(@PathVariable("num") Integer num){
        num = num==null||num<0?10:num;
        QueryWrapper<Post> query = new QueryWrapper<>();
        query.orderByDesc("build_time");
        query.last("limit "+num);
        List<PostForBigBlockVo> postVoList = postService.listForVO(query);
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
    @ApiOperation("(优质神贴)(已完成)得到最热的游戏文章,默认值为10,可设置文章数量")
    public R<List<PostForBigBlockVo>> hotgame(@PathVariable("num") Integer num) {
        num = (num==null||num<0)? 0 :num;
        QueryWrapper<Post> query = new QueryWrapper<>();
        query.orderByDesc("2 * reply_num + scan_num");
        query.last("limit "+num);
        List<PostForBigBlockVo> list = postService.listForVO(query);
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
        PageVo<PostForBigBlockVo> postVoPageVo = postService.pageForPostVO(pagingDto, upQuery);
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
    @PostMapping("/category/{sort}/{category}")
    @ApiOperation(value = "(类别页数据展示)(已完成)通过类别得到文章列表,方法为分页,sort,1:不排序,2:按时间排序,3:按热度排序")
    public R<PageVo<PostForLittleBlockVO>> category(@PathVariable(value = "category") String category, @RequestBody PagingDto pagingDto, @PathVariable String sort) {


        Category category1 = categoryService.getOne( new LambdaUpdateWrapper<Category>().eq(Category::getCategoryName,category));
        if(category1==null){
            return R.error(SystemError.NO_THIS_CATEGORY);
        }
        int categoryId = categoryService.getOne(new LambdaUpdateWrapper<Category>().eq(Category::getCategoryName,category)).getId();
        LambdaQueryWrapper<Post> queryWrapper = new LambdaQueryWrapper<>();
        QueryWrapper<Post> query = new QueryWrapper<>();
        PageVo<PostForLittleBlockVO> pageVo = new PageVo<>();
        if (sort.equals("1")){
            queryWrapper.eq(Post::getCategoryId,categoryId);
            pageVo = postService.pageForLittlePostVO(pagingDto, queryWrapper);

        }else if(sort.equals("2")){
            query.eq(category,category);
            query.orderByDesc("create_time");
            pageVo = postService.pageForLittlePostVO(pagingDto, query);
        }else if(sort.equals("3")){
            query.eq(category,category);
            query.orderByDesc("2 * reply_num + scan_num");
            pageVo = postService.pageForLittlePostVO(pagingDto, query);

        }

        return R.success(pageVo);
    }

    @PostMapping("/all/{sort}")
    @ApiOperation(value = "(最新上新,热度游戏)(已完成) 方法为分页 ,sort为1为最新上新,按时间排列,2为热度游戏,按热度排序,分页返回全部 ")
    public R<PageVo<PostForBigBlockVo>> getAll(@PathVariable(value = "sort") int sort, @RequestBody PagingDto pagingDto) {

        LambdaQueryWrapper<Post> queryWrapper = new LambdaQueryWrapper<>();
        QueryWrapper<Post> query = new QueryWrapper<>();
        PageVo<PostForBigBlockVo> pageVo = new PageVo<>();
        if (sort==1){
            query.orderByDesc("create_time");
            pageVo = postService.pageForPostVO(pagingDto, query);

        }else if(sort==2){

            query.orderByDesc("2 * reply_num + scan_num");
            pageVo = postService.pageForPostVO(pagingDto, query);
        }

        return R.success(pageVo);


    }
}
