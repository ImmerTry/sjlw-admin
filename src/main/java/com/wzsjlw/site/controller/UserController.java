package com.wzsjlw.site.controller;

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
                return ResultUtil.success(JWTUtil.sign(userName,password));
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
}
