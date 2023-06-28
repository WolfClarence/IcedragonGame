package com.icedragongame.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @ClassName : Post
 * @Author : wzy
 * @Description : Post class
 * @Date : 2023/6/27 10:05
 */

@Data
@TableName("post")
public class Post {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer postId;//id
    private String title;//标题
    @TableField(fill = FieldFill.INSERT)
    private BigInteger scanNum;//浏览量
    @TableField(fill = FieldFill.INSERT)
    private BigInteger replyNum;//回复量
    private LocalDateTime buildTime;//创建时间

    private String gameName;//游戏名
    private String gameDescribe;//介绍
    private String category;//分类
    private String downloadUrl;//下载链接
    private Integer points;//积分点
    @TableField(fill = FieldFill.INSERT)
    private String auditStatus;//审核状态
    private String username;

}


