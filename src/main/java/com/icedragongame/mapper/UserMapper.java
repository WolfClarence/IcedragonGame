package com.icedragongame.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.icedragongame.entity.Reply;
import com.icedragongame.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
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
 *@author wenrui
 *
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
