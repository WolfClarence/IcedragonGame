package com.icedragongame.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName : Reply  //类名
 * @Description : reply class  //描述
 * @Author : wenrui //作者
 * @Date: 2023/6/27  10:12
 */

@Data
@Builder
public class Reply {
    private int replyId;
    private String replyContext;
    private String replyTime;

    public Reply(int replyId, String replyContext, String replyTime) {
        this.replyId = replyId;
        this.replyContext = replyContext;
        this.replyTime = replyTime;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public String getReplyContext() {
        return replyContext;
    }

    public void setReplyContext(String replyContext) {
        this.replyContext = replyContext;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }
}
