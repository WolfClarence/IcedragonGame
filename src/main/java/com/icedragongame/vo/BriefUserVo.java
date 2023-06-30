package com.icedragongame.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : BriefUser  //类名
 * @Description : 将User驼峰转换为下划线传给前端  //描述
 * @Author : wenrui //作者
 * @Date: 2023/6/30  14:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BriefUserVo {

    private String username;
    private String user_password;
    private String user_nickname;

    private int user_identity;
    private int user_points;
    private String user_status;
    public BriefUserVo(User user){
        username = user.getUsername();
        user_password = user.getUserPassword();
        user_nickname = user.getUserNickname();
        user_identity = user.getUserIdentity();
        user_points = user.getUserPoints();
        user_status = user.getUserStatus();

    }
    public static List<BriefUserVo> getBUVbyUsers(List<User> users){
        int length = users.size();
        List<BriefUserVo> retList = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            retList.add(new BriefUserVo(users.get(i)));
        }
        return retList;
    }

    public static BriefUserVo getBUVbyAPost(User user){
        return  new BriefUserVo(user);
    }
}

