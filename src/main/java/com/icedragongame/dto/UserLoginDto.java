package com.icedragongame.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *     project: snow_dragonGame blogSystem
 *
 *  该类名称为:
 *     <name>
 *
 *  该类作用为:
 *   <effect>
 *
 *@author gengxuelong
 *
 */
@ApiModel(description = "登陆使用的数据,只需要传入用户名和密码,此处用户名要求唯一,作为数据的键值")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginDto {
   @ApiModelProperty(value = "用户名",required = true)
   private String username;
   @ApiModelProperty(value = "密码",required = true)
   private String password;
}
