package com.wzsjlw.site.service;

import com.wzsjlw.site.entity.User;
import com.wzsjlw.site.utils.ResultUtil;

/**
 * @author: ll
 * @version: 1.0 2019/07/02
 * @see:
 * @since:
 */
public interface UserService {
    /**
     * 用户登录服务接口
     *
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 根据用户名获取用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    User getUserByName(String userName);

    /**
     * 添加用户
     *
     * @param userName 用户名
     * @param nickName 昵称
     * @param password 密码
     * @return 结果集
     */
    ResultUtil save(String userName, String nickName, String password);

    /**
     * 更新密码
     * @param userName 用户名
     * @param password 密码
     * @return 结果集
     */
    ResultUtil updatePassword(String userName, String password);
}
