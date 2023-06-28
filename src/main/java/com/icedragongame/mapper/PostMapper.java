package com.icedragongame.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.Reply;
import com.icedragongame.entity.User;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;

@Mapper
public interface PostMapper extends BaseMapper<Post> {
/*
    @Results(id = "postMap", value = {
            @Result(property = "postId", column = "post_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "scanNum", column = "scan_num"),
            @Result(property = "replyNum", column = "reply_num"),
            @Result(property = "buildTime", column = "build_time"),
            @Result(property = "gameName", column = "game_name"),
            @Result(property = "gameDescribe", column = "game_describe"),
            @Result(property = "category", column = "category"),
            @Result(property = "downloadUrl", column = "download_url"),
            @Result(property = "points", column = "points"),
            @Result(property = "auditStatus", column = "audit_status"),
            @Result(property = "user", column = "username",javaType = User.class, one = @One(select = "selectUser"))
    })
    @Select("SELECT * FROM post WHERE post_id = #{postId}")
    Post selectPostById(Serializable postId);

    @Select("SELECT * FROM user WHERE username = #{username}")
    User selectUser(String username);
    
 */
}
