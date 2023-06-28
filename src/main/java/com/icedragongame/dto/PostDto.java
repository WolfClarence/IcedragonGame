package com.icedragongame.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * @ClassName : PostDto  //类名
 * @Description : 我是哈哈哈哈  //描述
 * @Author : wenrui //作者
 * @Date: 2023/6/28  17:53
 */

@Data
public class PostDto {

    private String title;//标题

    private String game_name;//游戏名
    private String game_describe;//介绍
    private String category;//分类
    private String download_url;//下载链接
    private Integer points;//积分点

    private String username;
}
