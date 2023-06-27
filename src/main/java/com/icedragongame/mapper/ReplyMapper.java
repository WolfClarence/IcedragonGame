package com.icedragongame.mapper;
import com.icedragongame.entity.Reply;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;
@Mapper
public interface ReplyMapper {
    //查询全部
    @Select("select * from reply")
    public List<Reply> queryAll();
    //根据id查询数据
    @Select("select from reply where reply_id = #{replyId}")
    public Reply queryByName(int replyId);
    //添加数据
    @Insert("insert into reply value(#{replyId},#{replyContext},#{replyTime})")
    public int add(Reply reply);
    //根据id删除数据
    @Delete("delete from reply where reply_id = #{replyId}")
    public void delete(int replyId);

}
