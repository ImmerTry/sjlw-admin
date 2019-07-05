package com.wzsjlw.site.service;

import com.wzsjlw.site.entity.User;

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
}
