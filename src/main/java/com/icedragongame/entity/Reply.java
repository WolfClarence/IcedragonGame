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
    private int reply_id;
    private String reply_context;
    private String reply_time;

    public Reply(int reply_id, String reply_context, String reply_time) {
        this.reply_id = reply_id;
        this.reply_context = reply_context;
        this.reply_time = reply_time;
    }
    public int getReply_id() {
        return reply_id;
    }

    public void setReply_id(int reply_id) {
        this.reply_id = reply_id;
    }

    public String getReply_context() {
        return reply_context;
    }

    public void setReply_context(String reply_context) {
        this.reply_context = reply_context;
    }

    public String getReply_time() {
        return reply_time;
    }

    public void setReply_time(String reply_time) {
        this.reply_time = reply_time;
    }


}
