package com.wzsjlw.site;

import com.wzsjlw.site.config.Realm;
import com.wzsjlw.site.dao.UserDAO;
import com.wzsjlw.site.entity.User;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiteApplicationTests {

    @Resource
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

    SimpleAccountRealm realm = new SimpleAccountRealm();

    @Before
    public void addUser() {
        realm.addAccount("ll", "123456");
    }

    @Test
    public void testShiro() {
        Realm realm = new Realm();

//        1、构建默认 SecurityManager 环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(realm);
//      使用加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
//      加密方式
        matcher.setHashAlgorithmName("md5");
//      设置加密次数
        matcher.setHashIterations(1);
//
        realm.setCredentialsMatcher(matcher);

//        2、主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("ll", "123456");
        subject.login(token);

        System.out.println("isAuthenticated: " + subject.isAuthenticated());

        subject.logout();

        System.out.println("isAuthenticated: " + subject.isAuthenticated());
    }

    @Test
    public void testUpload() {

    }
}
