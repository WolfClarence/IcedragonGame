package com.icedragongame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther: gengxuelong
 * @date:2023/6/29 12:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private String user_password;
    private String use_nickname;
    private int user_identity;
    private int user_points;
    private String user_status;
}
