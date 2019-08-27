package com.wzsjlw.site.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.wzsjlw.site.entity.User;
import com.wzsjlw.site.service.UserService;
import com.wzsjlw.site.utils.JWTUtil;
import com.wzsjlw.site.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author: ll
 * @version: 1.0 2019/07/02
 * @see:
 * @since:
 */
@RestController
@Api("用户登录")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @PostMapping("/user/login")
    @ApiOperation("登录")
    public ResultUtil login(@RequestParam("userName") String userName,
                            @RequestParam("password") String password) {
        LOGGER.info("用户名：" + userName + " 密码：" + password);
        try {
            User user = userService.getUserByName(userName);
            if (user.getPassword().equals(password)) {
                return ResultUtil.success("登录成功", JWTUtil.sign(userName, password));
            } else {
                return ResultUtil.fail("密码输入不正确");
            }
        } catch (Exception e) {
            return ResultUtil.fail("用户名不存在");
        }
    }

    @GetMapping("/article")
    public ResultUtil article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return ResultUtil.success("通过过认证");
        } else {
            return ResultUtil.success("未通过验证");
        }
    }

    @GetMapping("/require_path")
    @RequiresAuthentication
    public ResultUtil requirePath() {
        return ResultUtil.success("获得认证资格");
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultUtil unauthorized() {
        return ResultUtil.error("未授权");
    }

    @PostMapping("/user/parseToken")
    @ApiOperation("解析Token")
    public ResultUtil parseToken(@RequestParam("token") String token) {
        String username = JWTUtil.getUsername(token);
        User user = userService.getUserByName(username);
        return ResultUtil.success(user);
    }

    @PostMapping("/user/save")
    @ApiOperation("注册")
    public ResultUtil save(@RequestParam("userName") String userName,
                           @RequestParam("nickName") String nickName,
                           @RequestParam("password") String password) {

        LOGGER.info("用户名：" + userName + " 昵称：" + nickName + " 密码：" + password);
        return userService.save(userName, nickName, password);

    }

    @PostMapping("/user/updatePassword")
    @ApiOperation("修改密码")
    public ResultUtil updatePassword(@RequestParam("userName") String userName,
                                     @RequestParam("password") String password) {

        LOGGER.info("新密码: " + password + " 用户名：" + userName);
        return userService.updatePassword(userName, password);
    }

    @PostMapping("/user/isPassword")
    @ApiOperation("密码是否正确")
    public ResultUtil isPassword(@RequestParam("userName") String userName,
                                 @RequestParam("oldPassword") String oldPassword) {
        LOGGER.info("当前密码: " + oldPassword + " 用户名：" + userName);
        User user = userService.getUserByName(userName);
        if (user.getPassword().equals(oldPassword)) {
            return ResultUtil.success();
        } else {
            return ResultUtil.fail("密码不正确");
        }
    }
}
