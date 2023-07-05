package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icedragongame.common.R;
import com.icedragongame.constant.ConstantBySelf;
import com.icedragongame.dto.PagingDto;
import com.icedragongame.dto.UserNickDto;
import com.icedragongame.dto.UserPointAddDto;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.User;
import com.icedragongame.myenum.SystemError;
import com.icedragongame.service.PostService;
import com.icedragongame.service.ReplyService;
import com.icedragongame.service.UserService;
import com.icedragongame.vo.*;
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
    @ApiOperation("(进入个人页面,便更新个人信息,积分和tag)(已完成) 得到一个用户的详细信息")
    public R<UserInfoVo> getUserInfoByName(@PathVariable("username") String username){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username); // 根据用户名查询
        User user = userService.getOne(queryWrapper);
        System.out.println(user+"--------------------------------------------------------??");
        UserInfoVo userInfo = new UserInfoVo(user);
        return R.success(userInfo);
    }

    @GetMapping("/personal/normal/userinfo")
    @ApiOperation("得到所有的普通用户")
    public R<List<BriefUserVo>> getNormalUserInfo(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_identity", ConstantBySelf.USER_NORMAL_TAG);
        List<User> list = userService.list(queryWrapper);
        List<BriefUserVo> briefUserVos = BriefUserVo.getBUVbyUsers(list);
        return R.success(briefUserVos);
    }
    @GetMapping("/personal/admin/userinfo")
    @ApiOperation("得到所有的管理员用户")
    public R< List<BriefUserVo>> getAdminUserInfo(){
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
    @ApiOperation("(我的帖子,分页)(以实现) 得到一个用户的所有文章,分页方式")
    public R<PageVo<PostForBigBlockVo>> getUserPostRecordByName(@PathVariable("username") String username,@RequestBody PagingDto pagingDto){
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username); // 根据用户名查询
        PageVo<PostForBigBlockVo> postVoPageVo = postService.pageForPostVO(pagingDto, queryWrapper);
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
    @ApiOperation("(得到所有未审核的文章,分页方式)(已完成)")
    public R<Object> getUnauditedPost(@RequestBody PagingDto pagingDto){
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("audit_status","未审核");
        PageVo<PostForLittleBlockVO> postVoPageVo = postService.pageForLittlePostVO(pagingDto, queryWrapper);
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
    @ApiOperation("(按照id删除帖子)(已完成)")
    public R<String> deletePostById(@PathVariable("post_id") int id){
        postService.removeById(id);
        return R.success("删除帖子成功");
    }

    @PostMapping("/update/nick")
    @ApiOperation("(更新用户nickname)(已完成)")
    public R<Object> updateUser(@RequestBody UserNickDto userNickDto){
        /*fail
        UpdateWrapper<User> query = new UpdateWrapper<>();
        query.eq("username",userNickDto.getUsername());//获取用户
        query.set("user_nickname",userNickDto.getNick_name());//更改昵称
        userService.update(query);
        */
        /*fail
        UpdateWrapper<User> query = new UpdateWrapper<>();
        query.eq("username",userNickDto.getUsername());//获取用户
        User user = new User();
        user.setUserNickname(userNickDto.getNick_name());
        userService.update(user,query);
         */
        User user = userService.getById(userNickDto.getUsername());//获取用户
        if (user!=null){//找到用户
            LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(User::getUsername, userNickDto.getUsername())
                    .set(User::getUserNickname, userNickDto.getUser_nickname());
            userService.update(updateWrapper);
        }else{//用户不存在
            return R.error(SystemError.USER_NOT_FOUND);
        }
        return R.success("用户：" + userNickDto.getUsername() + " 的昵称更改成功：" + userNickDto.getUser_nickname());
    }

    @PostMapping("/update/point/")
    @ApiOperation("(更新用户积分,按照一定规则,观看一个文章加一分,评论一个加5分,前端传加分数据)(已完成)")
    public R<Object> updateUser(@RequestBody UserPointAddDto userPointAddDto){
        User user = userService.getById(userPointAddDto.getUsername());//获取用户
        if (user!=null){//用户存在
            LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
            Integer finalPoints = new Integer(user.getUserPoints() + userPointAddDto.getAdd_num());
            updateWrapper.eq(User::getUsername, userPointAddDto.getUsername())
                    .set(User::getUserPoints, finalPoints);
            userService.update(updateWrapper);
            return R.success("用户：" + userPointAddDto.getUsername() +
                    " 加分：" + userPointAddDto.getAdd_num());
        }else {//用户不存在
            return R.error(SystemError.USER_NOT_FOUND);
        }
    }


}
/*三种参数：query，body，path
 * get请求对应query，post请求对应body，path两个请求都能用
 */