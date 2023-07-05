package com.icedragongame;

import com.icedragongame.entity.Post;
import com.icedragongame.service.PostService;
import com.icedragongame.service.UserService;
import com.icedragongame.sysschedulejob.SysScheduleJob;
import com.icedragongame.utils.OssUtils;
import com.icedragongame.utils.MySqlUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.util.Date;

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
        Post post = new Post();
        for (int i = 100; i < 200; i++) {
           post = new Post(i,"哈哈真实强"+i,10,0,new Date(),"http://",10,"已通过","gengxuelong","","hahaa","王者农药","xx",2,"");
           postService.save(post);
        }
    }
}
