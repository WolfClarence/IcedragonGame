package com.icedragongame.dto;

import lombok.Data;

@Data
public class ReplyDto {

    private String reply_context;
    private String username;
    private Integer post_id;
}
