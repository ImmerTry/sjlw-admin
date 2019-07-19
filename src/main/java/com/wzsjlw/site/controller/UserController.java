package com.wzsjlw.site.controller;

import com.wzsjlw.site.entity.User;
import com.wzsjlw.site.service.UserService;
import com.wzsjlw.site.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ll
 * @version: 1.0 2019/07/02
 * @see:
 * @since:
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/user/login")
    public Result login(@RequestBody User user)
    {
        User login = userService.login(user);
        if (login != null) {
            return Result.success(login);
        }
        return Result.fail("用户名或密码输入有误");
    }
}
