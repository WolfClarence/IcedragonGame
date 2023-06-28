package com.icedragongame.controller;

import com.icedragongame.common.R;
import com.icedragongame.entity.Post;
import com.icedragongame.entity.User;
import com.icedragongame.service.PostService;
import com.icedragongame.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 管理员controller
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private UserService userService;
    @Resource
    private PostService postService;

    /**
     * 封禁用户
     * @param username
     * @return
     */
    @GetMapping("/banUser")
    public R<String> banUser (String username){
        User user = userService.getById(username);
        if (user!=null){
            if (user.getUserStatus().equals("0"))
                return R.error("该用户已处在封禁态");
            else {
                user.setUserStatus("0");
                return R.success("已封禁");
            }
        }else{
            return R.error("该用户不存在");
        }
    }

    /**
     * 解封用户
     * @param username
     */
    @GetMapping("/freeUser")
    public R<String> freeUser (String username){
        User user = userService.getById(username);
        if (user!=null){
            if (user.getUserStatus().equals("1"))
                return R.error("该用户已处在解封态");
            else {
                user.setUserStatus("1");
                return R.success("已解封");
            }
        }else{
            return R.error("该用户不存在");
        }
    }


    /**
     * 修改审核状态
     * @param id
     * @param status
     */
    @GetMapping ("/changeAuditStatus")
    public R<String> changeAuditStatus(int id, String status){

            Post post = postService.getById(id);
            String  result = post.getAuditStatus();
            if (result.equals(status))
                return R.error("已在该状态");
            else{
                post.setAuditStatus(status);
                return R.success("修改成功");
            }
    }

    /**
     * 升级用户为管理员
     * @param username
     */
    @GetMapping("/upgrade")
    public R<String> upgrade (String username){

        User user = userService.getById("username");
        if(user!=null){
        if(user.getUserIdentity()==1)
            return R.error("该用户已是管理员");
        else{
            user.setUserIdentity(1);
            return R.success("修改成功");
        }
        }
        else{
            return R.error("该用户不存在");
        }

    }

}
