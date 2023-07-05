package com.icedragongame.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyVo {
    private Integer id;
    private String replyContext;
    private Date build_time;
    private String username;
    private String user_url;
}
