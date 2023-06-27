package com.icedragongame.entity;

import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @ClassName : Post
 * @Author : wzy
 * @Description : Post class
 * @Date : 2023/6/27 10:05
 */

@Data
public class Post implements Serializable {

    private Integer postId;//id

    private String title;//标题

    private BigInteger scanNum;//浏览量

    private BigInteger replyNum;//回复量

    private Timestamp buildTime;//创建时间

    private String gameName;//游戏名

    private String describe;//介绍

    private String category;//分类

    private String downloadUrl;//下载链接

    private Integer points;//积分点

    private String auditStatus;//审核状态

}


