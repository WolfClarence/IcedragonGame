package com.icedragongame.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gengxuelong
 * @date 2023/7/2 4:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDetailVo {
    Integer id;
    String content;
    String title;
    String game_name;
    String game_description;
    String category;
    String image_url;
    Integer points;
    String download_url;
}
