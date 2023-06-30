package com.icedragongame;

import com.icedragongame.entity.Post;
import com.icedragongame.service.PostService;
import com.icedragongame.utils.OssUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Random;

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
    @Test
    public  void test() throws FileNotFoundException {
//        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\33494\\Pictures\\QQ截图20230615105218.png");
//        System.out.println( ossUtils.imageUpload(fileInputStream,".png"));
//        for (int i = 0; i < 100; i++) {
//            Random random = new Random();
//            long l = random.nextLong();
//            System.out.println(l);
//        }
        Post post = new Post();
        post.setPostId(122);
        post.setScanNum(BigInteger.valueOf(110));
        postService.updateById(post);

    }
}
