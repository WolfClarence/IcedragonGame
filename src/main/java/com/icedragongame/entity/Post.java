package com.icedragongame.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章表(Post)表实体类
 *
 * @author makejava
 * @since 2023-07-02 01:32:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post  implements Serializable {
    
    private Integer id;
    private String title;
    private Integer scanNum;
    private Integer replyNum;
    @TableField(fill = FieldFill.INSERT)
    private Date buildTime;
    private String downloadUrl;
    private Integer points;
    //未审核 审核通过 审核未通过
    private String auditStatus;
    private String username;
    private String imageUrl;
    private String content;
    private String gameName;
    private String gameDescription;
    private Integer categoryId;


    @TableField(exist = false)
    String category;

}

