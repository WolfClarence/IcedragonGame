package com.icedragongame;

import com.icedragongame.service.PostService;
import com.icedragongame.service.UserService;
import com.icedragongame.utils.OssUtils;
import com.icedragongame.utils.MySqlUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.io.FileNotFoundException;

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
    @Test
    public  void test() throws FileNotFoundException {
        sqlUtils.initDatabase();
    }
}
