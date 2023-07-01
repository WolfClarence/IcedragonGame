package com.icedragongame.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Game)表实体类
 *
 * @author makejava
 * @since 2023-07-02 01:30:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game  implements Serializable{
    
    private Integer id;
    
    private String gameName;
    
    private String gameDescribe;
    
    private Integer categoryId;
}

