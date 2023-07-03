package com.icedragongame.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostForBigBlockVo {
    Integer id;
    String title;
    Integer scan_num;
    Integer reply_num;
    String username;
    Date build_time;
    String game_name;
    String image_url;
    String category;
    String create_username_image_url;
    int likes_num;//喜欢这个文章的人数

}
