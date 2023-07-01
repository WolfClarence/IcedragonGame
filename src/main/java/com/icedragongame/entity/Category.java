package com.icedragongame.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Category)表实体类
 *
 * @author makejava
 * @since 2023-07-02 01:27:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable{
    
    private Integer id;
    
    private String categoryName;
}

