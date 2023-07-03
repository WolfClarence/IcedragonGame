package com.icedragongame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gengxuelong
 * @date 2023/7/3 12:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikesDto {
    private Integer post_id;

    private String username;
}
