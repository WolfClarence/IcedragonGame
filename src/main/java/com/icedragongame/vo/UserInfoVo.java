
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

    private String user_identity;

    private String user_points;

    private String user_status;


}