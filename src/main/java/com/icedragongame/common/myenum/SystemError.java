package com.icedragongame.common.myenum;

/**
 * <p>
 *  project: snow_dragonGame blogSystem
 *
 *  该类名称为:
 *     <name> SystemError
 *
 *  该类作用为:
 *   <effect> 定义返回信息的枚举类
 *
 *@author songyuxuan
 *
 */
public enum SystemError {

    /**
     * <p>
     *  project: snow_dragonGame blogSystem
     *
     *  该参数名称为:
     *     <name> SUCCESS
     *
     *  该参数描述为:
     *   <effect> 成功信息
     *
     */
    SUCCESS(200, "成功"),

    /**
     * <p>
     *  project: snow_dragonGame blogSystem
     *
     *  该参数名称为:
     *     <name> UNKNOWN_ERROR
     *
     *  该参数描述为:
     *   <effect> 未知错误信息
     *
     */
    UNKNOWN_ERROR(500, "未知错误"),
    //其他系统错误
    ;

    /**
     * <p>
     *  project: snow_dragonGame blogSystem
     *
     *  该参数名称为:
     *     <name> code
     *
     *  该参数描述为:
     *   <effect> 返回状态码
     *
     */
    private final Integer code;

    /**
     * <p>
     *  project: snow_dragonGame blogSystem
     *
     *  该参数名称为:
     *     <name> msg
     *
     *  该参数描述为:
     *   <effect> 返回信息描述
     *
     */
    private final String msg;

    /**
     * <p>
     *  project: snow_dragonGame blogSystem
     *
     *  该方法名称为:
     *     <name> SystemError
     *
     *  该方法作用为:
     *   <effect> 构造函数,私有化
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    private SystemError(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * <p>
     *  project: snow_dragonGame blogSystem
     *
     *  该方法名称为:
     *     <name> getCode
     *
     *  该方法作用为:
     *   <effect>
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    public Integer getCode() {
        return this.code;
    }

    /**
     * <p>
     *  project: snow_dragonGame blogSystem
     *
     *  该方法名称为:
     *     <name> getMsg
     *
     *  该方法作用为:
     *   <effect>
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    public String getMsg() {
        return this.msg;
    }
}
