package com.icedragongame.entity;

import lombok.Builder;
import lombok.Data;
import org.apache.camel.language.Bean;

/**
 * @ClassName : User  //类名
 * @Description : user class  //描述
 * @Author : wenrui //作者
 * @Date: 2023/6/27  10:11
 */
@Data
@Builder
public class User {
    private String user_name;
    private String user_password;
    private String user_nickname;

    private int user_identity;
    private int user_points;


    public User(String user_name, String user_password, String user_nickname, int user_identity, int user_points) {
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_nickname = user_nickname;
        this.user_identity = user_identity;
        this.user_points = user_points;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public int getUser_identity() {
        return user_identity;
    }

    public void setUser_identity(int user_identity) {
        this.user_identity = user_identity;
    }

    public int getUser_points() {
        return user_points;
    }

    public void setUser_points(int user_points) {
        this.user_points = user_points;
    }


}
