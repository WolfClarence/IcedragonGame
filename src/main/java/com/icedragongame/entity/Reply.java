package com.icedragongame.entity;

import lombok.Data;

/**
 * @ClassName : Reply  //类名
 * @Description : reply class  //描述
 * @Author : wenrui //作者mydb
 * @Date: 2023/6/27  10:12
 */

@Data
public class Reply {
    private int replyId;
    private String replyContext;
    private String replyTime;
}
