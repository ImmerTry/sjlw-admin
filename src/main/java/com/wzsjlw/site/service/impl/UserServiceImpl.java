package com.wzsjlw.site.service.impl;

import com.wzsjlw.site.dao.UserDAO;
import com.wzsjlw.site.entity.User;
import com.wzsjlw.site.service.UserService;
import com.wzsjlw.site.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ll
 * @version: 1.0 2019/07/02
 * @see:
 * @since:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User login(User user) {
        return userDAO.login(user);
    }

    @Override
    public User getUserByName(String userName) {
        return userDAO.getUserByName(userName);
    }

    @Override
    public ResultUtil save(String userName, String nickName, String password) {
        User user = userDAO.getUserByName(userName);
        if (user != null) {
            return ResultUtil.fail("登录名已存在");
        } else {
            int result = userDAO.save(userName, nickName, password);
            if (result > 0) {
                return ResultUtil.success("注册成功");
            } else {
                return ResultUtil.fail("注册失败");
            }
        }
    }

    @Override
    public ResultUtil updatePassword(String userName, String password) {
        int result = userDAO.updatePassword(userName, password);
        if (result > 0) {
            return ResultUtil.success("修改成功");
        }
        return ResultUtil.fail("修改失败");
    }
}
