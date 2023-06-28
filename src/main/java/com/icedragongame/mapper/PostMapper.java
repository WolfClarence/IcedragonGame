package com.icedragongame.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icedragongame.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostMapper extends BaseMapper<Post> {

/*
    @Select("select * from post")
    public List<Post> getNewGame();

    @Select("select * from post order by (scan_num + 2 * reply_num) as clout desc limit 3")
    public List<Post> getHotgame();
 */
}
