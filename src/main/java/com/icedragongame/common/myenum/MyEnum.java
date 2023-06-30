package com.icedragongame.common.myenum;

import io.swagger.models.auth.In;

/**
 * @author gengxuelong
 * @date 2023/6/30 0:13
 */
public enum MyEnum {

    NOT_FOUND_USER(501,"未找到此用户,请先注册"),
    PASSWORD_WRONG(502,"密码错误");
    private final Integer code;
    private final String msg;
    private MyEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
    public Integer getCode(){
        return this.code;
    }
    public String getMsg(){
        return this.msg;
    }

    //添加一个静态方法，根据code返回枚举值
    public static MyEnum valueOfCode(Integer code) {
        for (MyEnum e : values()) {
            if (e.code.equals(code)) {
                return e;
            }
        }
        return null;
    }
}
