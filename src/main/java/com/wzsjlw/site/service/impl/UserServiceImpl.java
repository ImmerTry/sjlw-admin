package com.wzsjlw.site.service.impl;

import com.wzsjlw.site.dao.UserDAO;
import com.wzsjlw.site.entity.User;
import com.wzsjlw.site.service.UserService;
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
    UserDAO userDAO;

    @Override
    public User login(User user) {
        return userDAO.login(user);
    }
}
