package com.icedragongame.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icedragongame.common.R;
import com.icedragongame.common.myenum.SystemError;
import com.icedragongame.constant.ConstantBySelf;
import com.icedragongame.dto.UserLoginDto;
import com.icedragongame.dto.UserRegisterDto;
import com.icedragongame.entity.User;
import com.icedragongame.service.LoginService;
import com.icedragongame.service.UserService;
import com.icedragongame.utils.MyBeanUtils;
import com.icedragongame.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
 *@author gengxuelong
 *
 */
@RestController
@Api(value = "login的controller")
public class LoginController {
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
    private LoginService loginService;
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
    @ApiOperation(value = "登陆接口",notes = "登陆")
    @PostMapping("/login")
    public R<Object> login(@RequestBody UserLoginDto UserLoginDto){
        User user = MyBeanUtils.beanCopy(UserLoginDto, User.class);
        User entity =userService.getById(user.getUsername());
        if(entity == null){
            return R.error(SystemError.USER_NOT_FOUND);
        }
        String userStatus = entity.getUserStatus();
        if(ConstantBySelf.USER_BANNED.equals(userStatus)){
            return R.error(SystemError.USER_HAS_BEEN_BANNED);
        }
        LoginVo loginVo = loginService.login(user);
        return R.success(loginVo);
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
    @PostMapping("/logout")
    @ApiImplicitParam(name = "token", required = false,paramType = "header",dataType = "String")
    public R<Object> logout(){
        loginService.logout();
        return R.success();
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
    @PostMapping("/register")
    public R<Object> register(@RequestBody UserRegisterDto userRegisterDto){
        User user = MyBeanUtils.beanCopy(userRegisterDto, User.class,true);
        System.out.println("register user -------------------------->"+user);
        User entity = userService.getById(user.getUsername());
        if(entity!=null){
            return R.error(SystemError.USER_HAS_BEEN_EXIST);
        }
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserNickname,user.getUserNickname());
        User entity1 = userService.getOne(lambdaQueryWrapper);
        if(entity1!=null){
            return R.error(SystemError.NICKNAME_HAS_BEEN_EXIST);
        }

        if (loginService.register(user)){
            return R.success("注册成功");
        }else{
            return R.error(SystemError.DATABASE_ERROR);
        }
    }

}
