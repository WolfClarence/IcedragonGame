package com.icedragongame.entity;

import lombok.Builder;
import lombok.Data;


/**
 * @ClassName : User  //类名
 * @Description : user class  //描述
 * @Author : wenrui //作者
 * @Date: 2023/6/27  10:11
 */
@Data
@Builder
public class User {
    private String username;
    private String userPassword;
    private String userNickname;

    private int userIdentity;
    private int userPoints;

    public User(String username, String userPassword, String userNickname, int userIdentity, int userPoints) {
        this.username = username;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
        this.userIdentity = userIdentity;
        this.userPoints = userPoints;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public int getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(int userIdentity) {
        this.userIdentity = userIdentity;
    }

    public int getUserPoints() {
        return userPoints;
    }

    public void setUserPoints(int userPoints) {
        this.userPoints = userPoints;
    }







}
