package com.icedragongame.sysschedulejob;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.icedragongame.constant.ConstantBySelf;
import com.icedragongame.entity.Post;
import com.icedragongame.service.PostService;
import com.icedragongame.utils.MyRedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author gengxuelong
 * @date 2023/6/29 21:36
 */
@Component
public class SysScheduleJob {
    @Resource
    MyRedisUtils myRedisUtils;
    @Autowired
    PostService postService;

    /**
     * 每个十分钟向数据库服务器更新以一次数据
     */
    @Scheduled(cron = "${gxl.schedule.time}")
    public void putScanDataToMysqlFromRedis() {
//        doSchedule();
    }

    public void doSchedule(){
        Map<Integer, Integer> scanForPostList = myRedisUtils.getMap(ConstantBySelf.REDIS_KEY_SCANS_POST);
        for (Map.Entry<Integer, Integer> integerIntegerEntry : scanForPostList.entrySet()) {
            Integer postId = integerIntegerEntry.getKey();
            Integer scanNum = integerIntegerEntry.getValue();
            Post post = new Post();
            post.setId(postId);
            post.setScanNum(scanNum);
            System.out.println("post------------------------->"+post.toString());
            postService.updateById(post);
        }
        LambdaQueryWrapper<Post> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Post::getScanNum,0);
        List<Post> posts = postService.list(lambdaQueryWrapper);
        Map<Integer, Integer> collect = posts.stream().collect(Collectors.toMap(Post::getId, Post::getScanNum));
        myRedisUtils.setMultiMapValue(ConstantBySelf.REDIS_KEY_SCANS_POST,collect);
    }
}
