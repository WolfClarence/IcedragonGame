package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icedragongame.common.R;
import com.icedragongame.constant.ConstantBySelf;
import com.icedragongame.dto.PagingDto;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.User;
import com.icedragongame.service.PostService;
import com.icedragongame.service.ReplyService;
import com.icedragongame.service.UserService;
import com.icedragongame.vo.BriefUserVo;
import com.icedragongame.vo.PageVo;
import com.icedragongame.vo.PostVo;
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
 *         UserController
 *
 *  该类作用为:
 *   <effect>
 *       个人按钮
 *
 *@author wenrui
 *
 */
@RestController
@RequestMapping("/api")
public class UserController {
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
    private UserService userService;
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

    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该方法名称为:
     *     <name>
     *
     *  该方法作用为:
     *   <effect>
     *       获取用户基本信息
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    @GetMapping("/personal/userinfo/{username}")
    @ApiOperation("得到一个用户的详细信息")
    public R<BriefUserVo> getUserInfoByName(@PathVariable("username") String username){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username); // 根据用户名查询
        User user = userService.getOne(queryWrapper);
        BriefUserVo BUV = new BriefUserVo(user);
        return R.success(BUV);
    }

    @GetMapping("/personal/normal/userinfo")
    @ApiOperation("得到所有的普通用户")
    public R<Object> getNormalUserInfo(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_identity", ConstantBySelf.USER_NORMAL_TAG);
        List<User> list = userService.list(queryWrapper);
        List<BriefUserVo> briefUserVos = BriefUserVo.getBUVbyUsers(list);
        return R.success(briefUserVos);
    }
    @GetMapping("/personal/admin/userinfo")
    @ApiOperation("得到所有的管理员用户")
    public R<Object> getAdminUserInfo(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_identity", ConstantBySelf.USER_ADMIN_TAG);
        List<User> list = userService.list(queryWrapper);
        List<BriefUserVo> briefUserVos = BriefUserVo.getBUVbyUsers(list);
        return R.success(briefUserVos);
    }

    @GetMapping("/personal/userinfo")
    @ApiOperation("得到所有用户")
    public R<Object> getUserInfo(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_identity", ConstantBySelf.USER_ADMIN_TAG);
        List<User> list = userService.list(queryWrapper);
        List<BriefUserVo> briefUserVos = BriefUserVo.getBUVbyUsers(list);
        return R.success(briefUserVos);
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
     *       获取用户发帖记录
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    @PostMapping("/personal/post/{username}")
    @ApiOperation("得到一个用户的所有文章,分页方式")
    public R<Object> getUserPostRecordByName(@PathVariable("username") String username,@RequestBody PagingDto pagingDto){
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username); // 根据用户名查询
        PageVo<PostVo> postVoPageVo = postService.pageForPostVO(pagingDto, queryWrapper);
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
     *       获取未审核的帖子
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    @PostMapping("/manager/uncheckedlist")
    @ApiOperation("得到所有未审核的文章,分页方式")
    public R<Object> getUnauditedPost(@RequestBody PagingDto pagingDto){
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("audit_status","未审核");
        PageVo<PostVo> postVoPageVo = postService.pageForPostVO(pagingDto, queryWrapper);
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
     *       删除帖子
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    @PostMapping("/manager/delete/{post_id}")
    @ApiOperation("按照id删除帖子")
    public R<String> deletePostById(@PathVariable("post_id") int id){
        postService.removeById(id);
        return R.success("删除帖子成功");
    }
}
/*三种参数：query，body，path
 * get请求对应query，post请求对应body，path两个请求都能用
 */