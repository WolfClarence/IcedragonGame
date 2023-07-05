
package com.icedragongame.vo;

import com.icedragongame.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)//set方法返回本身
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {
    /**
     * 主键
     */
    private String username;

    /**
     * 昵称
     */
    private String user_nickname;

    private Integer user_identity;

    private Integer user_points;

    private String user_status;

    private String tag;

    private String image_url;

    public UserInfoVo(User user){
        username = user.getUsername();
        user_nickname = user.getUserNickname();
        user_identity = user.getUserIdentity();
        user_points = user.getUserPoints();
        user_status = user.getUserStatus();
        image_url = user.getImageUrl();
        setTagByPoints(user.getUserPoints());
        System.out.println(user.toString());
    }

    public void setTagByPoints(Integer points){
        if(points <= 100){
            tag = "小白";
        }else if(points <= 500) {
            tag = "中白";
        }else{
            tag = "大白";
        }
    }
    /** UserInfoVo userInfo = new UserInfoVo()
     .setUsername("admin")
     .setUserNickname("管理员")
     .setUserIdentity("管理员身份")
     .setUserPoints("100")
     .setUserStatus("正常");
     **/
}