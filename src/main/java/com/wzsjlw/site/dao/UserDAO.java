package com.wzsjlw.site.dao;

import com.wzsjlw.site.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用户
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

    /**
     * 根据用户名获取用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    User getUserByName(@Param("userName")String userName);

    /**
     * 添加用户
     * @param userName 用户名
     * @param nickName 昵称
     * @param password 密码
     * @return 表中受影响行数
     */
    int save(@Param("userName") String userName,
                @Param("nickName") String nickName,
                @Param("password") String password);

    int updatePassword(@Param("userName")String userName,
                       @Param("password")String password);
}
