package com.icedragongame.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2023-07-02 01:33:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User  implements Serializable{

    @TableId
    private String username;
    
    private String password;
    
    private String userNickname;
    //1:管理员,0:普通用户
    private Integer userIdentity;
    //用户的积分
    @TableField(fill = FieldFill.INSERT)
    private Integer userPoints;
    //用户的状态,当为 已封禁 时,用户不再可用,当再解封后为 已解封
    @TableField(fill = FieldFill.INSERT)
    private String userStatus;
}

