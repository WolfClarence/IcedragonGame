package com.icedragongame.sysinit;

import com.icedragongame.constant.ConstantBySelf;
import com.icedragongame.entity.Post;
import com.icedragongame.service.PostService;
import com.icedragongame.utils.MyRedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
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
        log.info("___________systemInit______________");
        doInit();
    }
    private void doInit(){
        log.info("将文章观看次数放置在redis中");
        List<Post> list = postService.list();
        Map<Integer, Integer> collect = list.stream().collect(Collectors.toMap(Post::getId, Post::getScanNum));
        myRedisUtils.setMap(ConstantBySelf.REDIS_KEY_SCANS_POST,collect);
        System.out.println("\n" +
                ".__                                                      .___                                    \n" +
                "|__| ____  ____             ______ ____   ______  _  ____| _/___________     ____   ____   ____  \n" +
                "|  |/ ___\\/ __ \\   ______  /  ___//    \\ /  _ \\ \\/ \\/ / __ |\\_  __ \\__  \\   / ___\\ /  _ \\ /    \\ \n" +
                "|  \\  \\__\\  ___/  /_____/  \\___ \\|   |  (  <_> )     / /_/ | |  | \\// __ \\_/ /_/  >  <_> )   |  \\\n" +
                "|__|\\___  >___  >         /____  >___|  /\\____/ \\/\\_/\\____ | |__|  (____  /\\___  / \\____/|___|  /\n" +
                "        \\/    \\/               \\/     \\/                  \\/            \\//_____/             \\/ ");
    }


}
