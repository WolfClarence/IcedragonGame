package com.icedragongame.controller;

import com.icedragongame.common.R;
import com.icedragongame.entity.User;
import com.icedragongame.service.ReplyService;
import com.icedragongame.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName : DeletePostController  //类名
 * @Description : 删除指定id的帖子  //描述
 * @Author : wenrui //作者
 * @Date: 2023/6/27  16:12
 */
@RestController
@RequestMapping("/")
public class DeletePostController {
    @Resource
    private ReplyService replyService;


    @PostMapping ("/deletePost")
    public R<Boolean> deletePostById(int id){
        return R.success(replyService.removeById(id));
    }
}
