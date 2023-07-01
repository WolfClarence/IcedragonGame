package com.icedragongame.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName : UserPost  //类名
 * @Description : 为了user_post表数据项而特意创建的实体类  //描述
 * @Author : wenrui //作者
 * @Date: 2023/6/27  17:59
 */
@Data
@TableName("user_post")
public class UserPost {
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
    @TableId
    private String username;
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
    private int postId;
}
