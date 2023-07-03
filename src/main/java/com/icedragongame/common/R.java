package com.icedragongame.common;

import com.icedragongame.myenum.SystemError;

/**
 * <p>
 *     project: snow_dragonGame blogSystem
 *
 *  该类名称为:
 *     <name>
 *         R
 *
 *  该类作用为:
 *   <effect>
 *       通用返回结果，服务端响应的数据最终都会封装成此对象
 *
 *@author songyuxuan
 *
 */
public class R<T> {

    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该参数名称为:
     *     <name>
     *         code
     *
     *  该参数描述为:
     *   <effect>
     *       返回状态码
     *
     */
    private Integer code; //编码：200成功，500和其它数字为失败

    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该参数名称为:
     *     <name> msg
     *
     *  该参数描述为:
     *   <effect> return information to forward
     *
     */
    private String msg; //错误信息

    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该参数名称为:
     *     <name>
     *         data
     *
     *  该参数描述为:
     *   <effect>
     *       the concrete information to forward
     *
     */
    private T data; //数据


    /**
     * <p>
     *  project: snow_dragonGame blogSystem
     *
     *  该方法名称为:
     *     <name>
     *         success
     *
     *  该方法作用为:
     *   <effect>
     *       get the R entity when process is right
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = SystemError.SUCCESS.getCode();
        r.msg = SystemError.SUCCESS.getMsg();
        return r;
    }

    /**
     * <p>
     *  project: snow_dragonGame blogSystem
     *
     *  该方法名称为:
     *     <name>
     *         success
     *
     *  该方法作用为:
     *   <effect>
     *       get the R entity when process is right without  concrete information
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    public static <T> R<T> success() {
        R<T> r = new R<T>();
        r.data = null;
        r.code = SystemError.SUCCESS.getCode();
        r.msg = SystemError.SUCCESS.getMsg();
        return r;
    }

    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该方法名称为:
     *     <name>
     *         error
     *
     *  该方法作用为:
     *   <effect>
     *       get the R entity when process is wrong without  concrete information
     *       and when custom tip is used
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    public static <T> R<T> error(String msg) {
        R<T> r = new R<>();
        r.msg = msg;
        r.code = SystemError.UNKNOWN_ERROR.getCode();
        return r;
    }

    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该方法名称为:
     *     <name>
     *         error
     *
     *  该方法作用为:
     *   <effect>
     *       get the R entity when process is wrong with concrete information
     *       and when SystemError enum entity is used
     *
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    public static <T> R<T> error(SystemError systemError,T data) {
        R<T> r = new R<>();
        r.msg = systemError.getMsg();
        r.code = systemError.getCode();
        r.data = data;
        return r;
    }

    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该方法名称为:
     *     <name>
     *         error
     *
     *  该方法作用为:
     *   <effect>
     *       get the R entity when process is wrong without concrete information
     *       and when SystemError enum entity is used
     *
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    public static <T> R<T> error(SystemError systemError) {
        return error(systemError,null);
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
     *       空构造函数,私有化进行代码规范
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    private   R() {
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
     *       getters and setters
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
