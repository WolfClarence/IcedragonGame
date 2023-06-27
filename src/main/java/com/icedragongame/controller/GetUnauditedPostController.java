package com.icedragongame.controller;

import com.icedragongame.common.R;
import com.icedragongame.entity.User;
import com.icedragongame.service.ReplyService;
import com.icedragongame.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName : GetUnauditedPostController  //类名
 * @Description : 获取未审核的帖子列表  //描述
 * @Author : wenrui //作者
 * @Date: 2023/6/27  16:12
 */
@RestController
@RequestMapping("/")
public class GetUnauditedPostController {
    @Resource
    private ReplyService replyService;
    @Resource
    private UserService userService;
    @GetMapping("/getUnauditedPost")
    public R<List<User>> getUnauditedPost(){
        return R.success(userService.list());
    }

}
