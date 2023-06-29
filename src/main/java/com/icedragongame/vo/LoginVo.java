package com.icedragongame.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther: gengxuelong
 * @date: 2023/6/29 13:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginVo {
    String jwt;
    UserInfoVo userInfoVo;
}
