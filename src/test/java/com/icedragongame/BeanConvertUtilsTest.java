package com.icedragongame;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icedragongame.entity.Post;
import com.icedragongame.service.PostService;
import com.icedragongame.service.UserService;
import com.icedragongame.sysschedulejob.SysScheduleJob;
import com.icedragongame.utils.MySqlUtils;
import com.icedragongame.utils.OssUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @auther: gengxuelong
 * @date:2023/6/28 20:57
 */
@SpringBootTest
public class BeanConvertUtilsTest {
    @Autowired
    OssUtils ossUtils;
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Resource
    MySqlUtils sqlUtils;
    @Resource
    SysScheduleJob sysScheduleJob;
    @Test
    public  void test() throws FileNotFoundException {
        QueryWrapper<Post> postQueryWrapper = new QueryWrapper<>();
        postQueryWrapper.last("limit 1");
        List<Post> list = postService.list(postQueryWrapper);
        System.out.println(list);
    }
}
