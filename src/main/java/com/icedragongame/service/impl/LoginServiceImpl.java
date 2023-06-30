package com.icedragongame.service.impl;

import com.icedragongame.utils.MyBeanUtils;
import com.icedragongame.utils.JwtUtil;
import com.icedragongame.common.R;
import com.icedragongame.dto.LoginUserDetails;
import com.icedragongame.entity.User;
import com.icedragongame.exception.SystemExceptionBySelf;
import com.icedragongame.mapper.UserMapper;
import com.icedragongame.service.LoginService;
import com.icedragongame.vo.LoginVo;
import com.icedragongame.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    UserMapper userMapper;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public R<Object> login(User user) {
        System.out.println("-----------------进入login service impl");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getUserPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if(Objects.isNull(authenticate)){
            throw new SystemExceptionBySelf("用户名或是密码错误");
        }
        //获取userid 生成token
        LoginUserDetails loginUser = (LoginUserDetails) authenticate.getPrincipal();
        String userId = loginUser.getUser().getUsername().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisTemplate.opsForValue().set("loginUser:"+userId,loginUser);
        //把token和userinfo封装 返回
        //把User转换成UserInfoVo
        UserInfoVo userInfoVo = MyBeanUtils.beanCopy(loginUser.getUser(), UserInfoVo.class,true);
        LoginVo vo = new LoginVo(jwt,userInfoVo);
        return R.success(vo);
    }

    @Override
    public R<Object> logout() {
        //获取token 解析获取userid
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserDetails loginUser = (LoginUserDetails) authentication.getPrincipal();
        //获取userid
        String userId = loginUser.getUser().getUsername();
        //删除redis中的用户信息
        redisTemplate.delete("loinUser:"+userId);
        return R.success();
    }


    @Override
    public R<Object> register(User user) {
        //对数据进行非空判断
        if(!StringUtils.hasText(user.getUsername())){
            throw new SystemExceptionBySelf("用户名不存在");
        }
        if(!StringUtils.hasText(user.getUserPassword())){
            throw new SystemExceptionBySelf("密码不存在");
        }
        //对密码进行加密
        String encodePassword = passwordEncoder.encode(user.getUserPassword());
        user.setUserPassword(encodePassword);
        //存入数据库
        MyBeanUtils.initBean(user);
        userMapper.insert(user);
        return R.success("注册成功");
    }
}
