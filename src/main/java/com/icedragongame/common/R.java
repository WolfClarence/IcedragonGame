package com.icedragongame.common;

import com.icedragongame.common.myenum.SystemError;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果，服务端响应的数据最终都会封装成此对象
 * @param <T>
 */
@Data
public class R<T> {

    private Integer code; //编码：200成功，500和其它数字为失败

    private String msg; //错误信息

    private T data; //数据


    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = SystemError.SUCCESS.getCode();
        r.msg = SystemError.SUCCESS.getMsg();
        return r;
    }

    public static <T> R<T> success() {
        R<T> r = new R<T>();
        r.data = null;
        r.code = SystemError.SUCCESS.getCode();
        r.msg = SystemError.SUCCESS.getMsg();
        return r;
    }

    public static <T> R<T> error(String msg) {
        R<T> r = new R<>();
        r.msg = msg;
        r.code = SystemError.UNKNOWN_ERROR.getCode();
        return r;
    }
    public static <T> R<T> error(SystemError systemError,T data) {
        R<T> r = new R<>();
        r.msg = systemError.getMsg();
        r.code = systemError.getCode();
        r.data = data;
        return r;
    }

    public static <T> R<T> error(SystemError systemError) {
        return error(systemError,null);
    }


    private   R() {
    }



}
