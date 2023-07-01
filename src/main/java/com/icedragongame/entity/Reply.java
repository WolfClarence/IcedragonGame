package com.icedragongame.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (Reply)表实体类
 *
 * @author makejava
 * @since 2023-07-02 01:32:58
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reply implements Serializable{

    private Integer id;
    
    private String replyContext;

    @TableField(fill = FieldFill.INSERT)
    private Date buildTime;
    
    private Integer postId;

    private String username;

}

