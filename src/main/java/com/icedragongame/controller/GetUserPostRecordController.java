package com.icedragongame.controller;

import com.icedragongame.common.R;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.User;
import com.icedragongame.service.ReplyService;
import com.icedragongame.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName : GetUserPostRecordController  //类名
 * @Description :  获取某人的发帖记录，查询帖子表  //描述
 * @Author : wenrui //作者
 * @Date: 2023/6/27  16:11
 */
@RestController
@RequestMapping("/")
public class GetUserPostRecordController {
    @Resource
    private ReplyService replyService;
    @Resource
    private UserService userService;
    @GetMapping("/getUserPostRecord")
    public R<List<Post>> getUserPostRecordByName(){
        List<int> ids =
        return R.success(userService.list());
    }

}
