package com.icedragongame.common;

import com.icedragongame.common.myenum.MyEnum;
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
        r.code = 200;
        return r;
    }

    public static <T> R<T> success() {
        R<T> r = new R<T>();
        r.data = null;
        r.code = 200;
        return r;
    }

    public static <T> R<T> error(Integer code) {
        R<T> r = new R<>();
        //根据code获取枚举值，并设置msg
        MyEnum e = MyEnum.valueOfCode(code);
        if (e != null) {
            r.msg = e.getMsg();
        } else {
            r.msg = "未知错误";
        }
        r.code = code;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R<T> r = new R<>();
        r.msg = msg;
        r.code = 500;
        return r;
    }
}
