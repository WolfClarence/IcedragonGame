package com.icedragongame.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author gengxuelong
 * @date 2023/7/4 2:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostForLittleBlockVO {
    Integer id;

    String title;


    String username;

    Date build_time;

    String game_name;

    String image_url;

    String category;

    String create_username_image_url;


}
