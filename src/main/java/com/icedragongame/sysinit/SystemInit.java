package com.icedragongame.sysinit;

import com.icedragongame.contanst.ConstantBySelf;
import com.icedragongame.entity.Post;
import com.icedragongame.service.PostService;
import com.icedragongame.utils.MyRedisUtils;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author  gengxuelong
 * @date 2023/6/29 21:17
 */
@Component
@Slf4j
public class SystemInit {
    @Autowired
    PostService postService;
    @Resource
    MyRedisUtils myRedisUtils;

    @PostConstruct
    public void init(){
        log.info("将文章观看次数放置在redis中");
        List<Post> list = postService.list();
        Map<Integer, Integer> collect = list.stream().collect(Collectors.toMap(Post::getPostId, post->post.getScanNum().intValue()));
        myRedisUtils.setMap(ConstantBySelf.KEY_SCANS_POST,collect);
        System.out.println("\n" +
                ".__                                                      .___                                    \n" +
                "|__| ____  ____             ______ ____   ______  _  ____| _/___________     ____   ____   ____  \n" +
                "|  |/ ___\\/ __ \\   ______  /  ___//    \\ /  _ \\ \\/ \\/ / __ |\\_  __ \\__  \\   / ___\\ /  _ \\ /    \\ \n" +
                "|  \\  \\__\\  ___/  /_____/  \\___ \\|   |  (  <_> )     / /_/ | |  | \\// __ \\_/ /_/  >  <_> )   |  \\\n" +
                "|__|\\___  >___  >         /____  >___|  /\\____/ \\/\\_/\\____ | |__|  (____  /\\___  / \\____/|___|  /\n" +
                "        \\/    \\/               \\/     \\/                  \\/            \\//_____/             \\/ ");
    }


}
