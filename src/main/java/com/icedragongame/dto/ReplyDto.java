package com.icedragongame.dto;

import lombok.Data;

@Data
public class ReplyDto {

    private Integer reply_id;
    private String reply_context;
    private String username;
    private Integer post_id;
}
