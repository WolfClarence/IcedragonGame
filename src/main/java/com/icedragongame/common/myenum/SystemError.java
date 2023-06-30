package com.icedragongame.common.myenum;

public enum SystemError {

    SUCCESS(200, "成功"),
    UNKNOWN_ERROR(500, "未知错误"),
    //其他系统错误
    ;

    private final Integer code;
    private final String msg;

    private SystemError(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
