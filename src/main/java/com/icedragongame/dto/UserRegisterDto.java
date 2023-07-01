package com.icedragongame.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gengxuelong
 * @date 2023/7/1 18:07
 */
@ApiModel(description = "注册使用的数据,注册页面只能注册普通用户,无法注册管理员,所以这个user_identity不用传,此处仅仅是对管理员新增功能的预留,此功能可以不做")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegisterDto {
    @ApiModelProperty(value = "用户名",required = true)
    private String username;
    @ApiModelProperty(value = "密码",required = true)
    private String password;
    @ApiModelProperty(value = "昵称",required = true)
    private String user_nickname;
    @ApiModelProperty(value = "注册用户的类型",required = false)
    private Integer user_identity=0;


}

