package com.icedragongame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author gengxuelong
 * @date 2023/7/2 19:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class GameDto {
    private Integer id;

    private String game_name;

    private String game_describe;

    private Integer category_id;

    private String category_name;

}
