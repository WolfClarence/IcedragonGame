package com.icedragongame.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gengxuelong
 * @date 2023/7/4 1:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostForTodayHotVO {
    Integer id;//postid,用来进入详情页
    String game_name;
    String title;
    String image_url;
}
