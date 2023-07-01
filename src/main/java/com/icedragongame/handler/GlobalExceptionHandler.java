package com.icedragongame.handler;

import com.icedragongame.common.R;
import com.icedragongame.exception.SystemExceptionBySelf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
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
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

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
    @ExceptionHandler(SystemExceptionBySelf.class)
    public R<Object> systemExceptionHandler(SystemExceptionBySelf e){
        //打印异常信息
        log.error("出现SystemExceptionBySelf异常: "+e);
        //从异常对象中获取提示信息封装返回
        return R.error("出现SystemExceptionBySelf异常: "+e.getMessage());
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
    @ExceptionHandler(Exception.class)
    public R<Object> exceptionHandler(Exception e){
        //打印异常信息
        log.error("出现系统异常: "+e);
        e.printStackTrace();
        //从异常对象中获取提示信息封装返回
        return R.error("系统异常:"+e.getMessage());
    }
}
