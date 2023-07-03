package com.icedragongame.service.impl;

import com.icedragongame.myenum.SystemError;
import com.icedragongame.constant.ConstantBySelf;
import com.icedragongame.dto.LoginUserDetails;
import com.icedragongame.entity.User;
import com.icedragongame.exception.SystemExceptionBySelf;
import com.icedragongame.service.LoginService;
import com.icedragongame.service.UserService;
import com.icedragongame.utils.JwtUtil;
import com.icedragongame.utils.MyBeanUtils;
import com.icedragongame.utils.MyRedisUtils;
import com.icedragongame.vo.LoginVo;
import com.icedragongame.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @auther: gengxuelong
 * @date:2023/6/29 12:43
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Resource
    UserService userService;

    @Resource
    private MyRedisUtils myRedisUtils;
    @Override
    public LoginVo login(User user) {
        System.out.println("-----------------进入login service impl");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if(Objects.isNull(authenticate)){
            throw new SystemExceptionBySelf(SystemError.USER_OR_PASSWORD_ERROR);
        }
        //获取userid 生成token
        LoginUserDetails loginUser = (LoginUserDetails) authenticate.getPrincipal();
        String userId = loginUser.getUser().getUsername().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
       myRedisUtils.setObject(ConstantBySelf.REDIS_KEY_USERINFO+userId,loginUser);
        //把token和userinfo封装 返回
        //把User转换成UserInfoVo
        UserInfoVo userInfoVo = MyBeanUtils.beanCopy(loginUser.getUser(), UserInfoVo.class,true);
        return new LoginVo(jwt,userInfoVo);
    }

    @Override
    public void logout() {
        //获取token 解析获取userid
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal());
        LoginUserDetails loginUser = (LoginUserDetails) authentication.getPrincipal();
        //获取userid
        String userId = loginUser.getUser().getUsername();
        //删除redis中的用户信息
        myRedisUtils.deleteObject(ConstantBySelf.REDIS_KEY_USERINFO +userId);
    }


    @Override
    public boolean register(User user) {
        //对数据进行非空判断
        if(!StringUtils.hasText(user.getUsername())||!StringUtils.hasText(user.getPassword())){
            throw new SystemExceptionBySelf(SystemError.FIELD_NOT_EXITS);
        }
        //对密码进行加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        return userService.save(user);
    }
}
