package com.icedragongame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gengxuelong
 * @date 2023/7/4 1:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserImageDto {
    private String username;
    private String image_url;
}
