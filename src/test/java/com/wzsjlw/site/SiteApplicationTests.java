package com.wzsjlw.site;

import com.wzsjlw.site.dao.UserDAO;
import com.wzsjlw.site.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiteApplicationTests {

    @Autowired
    UserDAO userDAO;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setUserName("sjlw");
        user.setPassword("123456");
        User login = userDAO.login(user);
        System.out.println(login);
    }
    @Test
    public void test() {
        List<User> allUser = userDAO.getAllUser();
        Assert.assertNotNull(allUser);
    }

}
