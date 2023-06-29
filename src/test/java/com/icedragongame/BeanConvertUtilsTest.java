package com.icedragongame;

import com.icedragongame.utils.OssUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @auther: gengxuelong
 * @date:2023/6/28 20:57
 */
@SpringBootTest
public class BeanConvertUtilsTest {
    @Autowired
    OssUtils ossUtils;
    @Test
    public  void test() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\33494\\Pictures\\QQ截图20230615105218.png");
        ossUtils.imageUpload(fileInputStream,".png");
    }
}
