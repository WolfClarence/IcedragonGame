package com.icedragongame.mapper;
import com.icedragongame.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;
@Mapper
public interface UserMapper {
    //查询全部
    public List<User> queryAll();
    //添加数据
    public int add(User user);
    //根据用户名删除数据
    public void delete(String username);
    //根据用户名查询数据
    public User queryByName(String username);
    //根据用户名修改数据
    public User upgrade(String username);
}
