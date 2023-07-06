package com.icedragongame.dto;

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
 *@author wenrui
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {

    private String title;//标题

    private String game_name;//游戏名

    private String game_description;//游戏介绍

    private String category;//分类

    private String download_url;//下载链接

    private Integer points;//积分点

    private String username;

    private String content;
    private String image_url    ;
}
