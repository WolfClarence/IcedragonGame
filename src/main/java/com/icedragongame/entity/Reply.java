package com.icedragongame.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName : Reply  //类名
 * @Description : reply class  //描述
 * @Author : wenrui //作者mydb
 * @Date: 2023/6/27  10:12
 */

@Data
public class Reply {
    @TableId
    private int replyId;
    private String replyContext;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime buildTime;

    @TableField("username")
    private User user;//作者
    @TableField("post_id")
    private Post post;//在哪个帖子之下
}
