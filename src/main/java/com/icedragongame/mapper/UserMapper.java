package com.icedragongame.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icedragongame.entity.Reply;
import com.icedragongame.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<User> {
/*    //查询全部
    @Select("select * from user")
    public List<User> queryAll();
    //根据用户名查询数据
    @Select("select * from user where username = #{username}")
    public User queryByName(String username);
    //添加数据
    @Insert("Insert into user value(#{username},#{userPassword},#{userNickname},#{userIdentity},#{userPoints})")
    public int add(User user);
    //根据用户名删除数据
    @Delete("delete from user where username = #{username}")
    public int deleteByName(String username);

    //根据用户名修改数据
    @Update("update user set user_password = #{userPassowrd}," +
            "user_nickname = #{userNickname}," +
            "user_identity = #{userIdentity},"+
            "user_points = #{userPoints},"+
            " where username = #{username}")
    public int updadeByName(String User);*/
}
