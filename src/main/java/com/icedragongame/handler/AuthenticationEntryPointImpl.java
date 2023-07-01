
package com.icedragongame.handler;

import com.alibaba.fastjson.JSON;
import com.icedragongame.common.R;
import com.icedragongame.utils.WebSmallUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

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
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        authException.printStackTrace();
        R<Object> result = null;
        if(authException instanceof BadCredentialsException){
            result =R.error(authException.getMessage());
        }else if(authException instanceof InsufficientAuthenticationException){
            result = R.error("无需登陆");
        }else if(authException instanceof InternalAuthenticationServiceException){
            result = R.error("用户不存在");
        }else{
            result=R.error("认证或授权失败");
        }
        //响应给前端
        WebSmallUtils.renderString(response, JSON.toJSONString(result));
    }
}
