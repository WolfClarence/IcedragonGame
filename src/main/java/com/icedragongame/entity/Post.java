package com.icedragongame.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * @ClassName : Post
 * @Author : wzy
 * @Description : Post class
 * @Date : 2023/6/27 10:05
 */

@Data
@TableName(value = "post")
public class Post {

    @TableId
    private Integer postId;//id
    private String title;//标题
    private BigInteger scanNum;//浏览量
    private BigInteger replyNum;//回复量
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime buildTime;//创建时间

    private String gameName;//游戏名
    private String gameDescribe;//介绍
    private String category;//分类
    private String downloadUrl;//下载链接
    private Integer points;//积分点
    private String auditStatus;//审核状态
    private String username;//作者
    private String imageUrl;
}


