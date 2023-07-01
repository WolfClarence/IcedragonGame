package com.icedragongame.constant;

import java.util.Arrays;
import java.util.List;

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
public class ConstantBySelf {

    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该参数名称为:
     *     <name>
     *
     *  该参数描述为:
     *   <effect>
     *
     */
    public static final String REDIS_KEY_SCANS_POST = "scanForPostList";
    public static final String DEFAULT_USER_STATE = "新注册";
    public static final Integer DEFAULT_USER_POINTS = 0;

    public static final String REDIS_KEY_USERINFO = "loinUser:";
    public static final String USER_BANNED = "被封禁";
    public static final String USER_FREEDOM = "已解封";
    public static final Integer USER_ADMIN_TAG = 1;
    public static final Integer USER_NORMAL_TAG = 0;

    public static final Integer POST_HAS_AUDIT = 1;
    public static final Integer POST_NO_AUDIT = 0;
    public static final List<String> POST_STATUS_LIST = Arrays.asList("未审核","审核通过","审核未通过");
    public static final Integer ORDER_BY_TIME = 0;
}
