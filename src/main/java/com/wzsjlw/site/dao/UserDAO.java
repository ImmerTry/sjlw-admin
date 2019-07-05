package com.wzsjlw.site.dao;

import com.wzsjlw.site.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户登入接口
 *
 * @author: ll
 * @version: 1.0 2019/07/01
 * @see:
 * @since:
 */
@Mapper
public interface UserDAO {
    /**
     * 用户登录请求接口
     *
     * @param user
     * @return
     */
    User login(User user);

    /**
     * @return
     */
    List<User> getAllUser();
}
