package com.icedragongame.entity;

import lombok.Data;

/**
 * @ClassName : User  //类名
 * @Description : user class  //描述
 * @Author : wenrui //作者
 * @Date: 2023/6/27  10:11
 */
@Data
public class User {
    private String username;
    private String userPassword;
    private String userNickname;

    private int userIdentity;
    private int userPoints;


}
