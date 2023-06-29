package com.icedragongame.handler;

import com.icedragongame.common.R;
import com.icedragongame.exception.SystemExceptionBySelf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemExceptionBySelf.class)
    public R<Object> systemExceptionHandler(SystemExceptionBySelf e){
        //打印异常信息
        log.error("出现SystemExceptionBySelf异常: "+e);
        //从异常对象中获取提示信息封装返回
        return R.error(e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public R<Object> exceptionHandler(Exception e){
        //打印异常信息
        log.error("出现系统异常: "+e);
        //从异常对象中获取提示信息封装返回
        return R.error(e.getMessage());
    }
}
