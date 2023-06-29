package com.icedragongame.handler;

import com.alibaba.fastjson.JSON;
import com.icedragongame.common.R;
import com.icedragongame.common.WebSmallUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        accessDeniedException.printStackTrace();
        R<Object> result = R.error("无此权限");
        //响应给前端
        WebSmallUtils.renderString(response, JSON.toJSONString(result));
    }
}
