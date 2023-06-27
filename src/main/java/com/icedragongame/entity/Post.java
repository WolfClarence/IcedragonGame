package com.icedragongame.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @ClassName : Post
 * @Author : wzy
 * @Description : Post class
 * @Date : 2023/6/27 10:05
 */

@Entity
@Builder
@Getter
@Setter
@Table(name = "Post")
public class Post implements Serializable {

    @Id
    @Column(name="post_id",nullable = false)
    private Integer postId;//id

    @Column(name = "title",nullable = false)
    private String title;//标题

    @Column(name = "scan_num",nullable = false)
    private BigInteger scanNum;//浏览量

    @Column(name = "reply_num",nullable = false)
    private BigInteger replyNum;//回复量

    @Column(name = "build_time",nullable = false)
    private Timestamp buildTime;//创建时间

    @Column(name = "game_name",nullable = false)
    private String gameName;//游戏名

    @Column(name = "describe")
    private String describe;//介绍

    @Column(name = "catagory",nullable = false)
    private String catagory;//分类

    @Column(name = "download_url",nullable = false)
    private String downloadUrl;//下载链接

    @Column(name = "points",nullable = false)
    private Integer points;//积分点

    @Column(name = "audit_status",nullable = false)
    private String auditStatus;//审核状态

}


