package com.icedragongame.myenum;

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
    DATABASE_ERROR(501,"数据库错误"),
    USER_NOT_FOUND(502, "用户未找到"),
    USER_HAS_BEEN_FREEDOM(503, "该用户已处在解封态"),
    POST_NOT_FOUND(504, "文章未找到"),
    USER_HAS_BEEN_ADMIN(505,"该用户已是管理员" ),
    USER_HAS_BEEN_BANNED(506, "用户已经被封禁"),
    WRONG_FORMAT_FOR_POST_STATUS(507, "错误的文章状态格式"),
    POST_HAS_BEEN_STATUS(508, "该文章已在该状态"),
    USER_HAS_BEEN_EXIST(509, "用户已存在"),
    NICKNAME_HAS_BEEN_EXIST(510, "昵称已被占用"),
    NO_THIS_CATEGORY(511, "无此游戏类别"),
    IMAGE_TYPE_ERROR(512, "图片文件只能是png和jpg,不支持上传其他类型文件"),
    FILE_SIZE_OVER_LIMIT(513, "所上传的文件超过最大限制,请重新选择上传"),
    USER_OR_PASSWORD_ERROR(514, "密码或用户名错误"),
    FIELD_NOT_EXITS(515, "需要必填的字段为空,请填写必要字段"),
    TOKEN_ERROR(516, "token过期或者非法"), TOKEN_EXPIRED(517, "登陆过期");

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
