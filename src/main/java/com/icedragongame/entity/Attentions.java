package com.icedragongame.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Attentions)表实体类
 *
 * @author makejava
 * @since 2023-07-03 12:20:17
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attentions implements Serializable{
    
    private Integer id;
    
    private Integer postId;
    
    private String username;


}

