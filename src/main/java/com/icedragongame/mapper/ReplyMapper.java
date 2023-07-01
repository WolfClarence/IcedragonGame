package com.icedragongame.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icedragongame.entity.Reply;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 *     project: snow_dragonGame blogSystem
 *
 *  该类名称为:
 *     <name>
 *
 *  该类作用为:
 *   <effect>
 *
 *@author wzy
 *
 */
@Mapper
public interface ReplyMapper extends BaseMapper<Reply> {
//
//    @Select("SELECT * FROM reply WHERE reply_id = #{replyId}")
//    @Results({
//            @Result(property = "replyId", column = "reply_id"),
//            @Result(property = "replyContent", column = "reply_content"),
//            @Result(property = "buildTime", column = "build_time"),
//            @Result(property = "user", column = "username",javaType = User.class, one=@One(select = "selectUser")),
//            @Result(property = "post", column = "post_id",javaType = Post.class, one = @One(select = "selectPostById"))
//    })
//    Reply selectReplyById(Serializable playerId);
//
//    @Select("SELECT * FROM user WHERE username = #{username}")
//    User selectUser(String username);
//
//    @Select("SELECT * FROM post WHERE post_id = #{postId}")
//    @ResultMap("com.icedragongame.mapper.PostMapper.postMap")
//    Post selectPostById(Serializable postId);
}
