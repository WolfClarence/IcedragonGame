package com.icedragongame.mapper;
import com.icedragongame.entity.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;
@Mapper
public interface ReplyMapper {
    //查询全部
    public List<Reply> queryAll();
    //添加数据
    public int add(Reply reply);
    //根据id删除数据
    public void delete(int replyId);
    //根据id查询数据
    public Reply queryByName(int replyId);
}
