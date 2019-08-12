package com.wzsjlw.site.controller;

import com.wzsjlw.site.entity.User;
import com.wzsjlw.site.service.UserService;
import com.wzsjlw.site.utils.JWTUtil;
import com.wzsjlw.site.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: ll
 * @version: 1.0 2019/07/02
 * @see:
 * @since:
 */
@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @PostMapping("/user/login")
    public ResultUtil login(@RequestBody Map userMap) {
        LOGGER.info("用户名：" + userMap.get("userName") + " 密码：" + userMap.get("password"));
        String userName = userMap.get("userName").toString();
        String password = userMap.get("password").toString();
        try {
            User user = userService.getUserByName(userName);
            if (user.getPassword().equals(password)) {
                return ResultUtil.success(JWTUtil.sign(userName,password));
            } else {
                return ResultUtil.fail("密码输入不正确");
            }
        } catch (Exception e) {
            return ResultUtil.fail("用户名不存在");
        }
    }
}
