
package com.icedragongame.vo;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)//set方法返回本身
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
    /** UserInfoVo userInfo = new UserInfoVo()
     .setUsername("admin")
     .setUserNickname("管理员")
     .setUserIdentity("管理员身份")
     .setUserPoints("100")
     .setUserStatus("正常");
     **/
}