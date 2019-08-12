package com.wzsjlw.site.config;

import com.wzsjlw.site.entity.User;
import com.wzsjlw.site.service.UserService;
import com.wzsjlw.site.utils.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义 Realm
 *
 * @author: ll
 * @version: 1.0 2019/07/02
 * @see:
 * @since:
 */
public class Realm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(Realm.class);

    @Autowired
    UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 执行授权逻辑
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JWTUtil.getUsername(principals.toString());
        User user = userService.getUserByName(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(user.getAccess().toString());
//        Set<String> permisson = new HashSet<>()
        return simpleAuthorizationInfo;
    }

    /**
     * 执行认证逻辑
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String credentials = (String) token.getCredentials();
        // 解密获得 username
        String username = JWTUtil.getUsername(credentials);
        if (username == null) {
            throw new AuthenticationException("token 无效");
        }
        User user = userService.getUserByName(username);
        if (user == null) {
            throw new AuthenticationException("用户不存在");
        }
        if (!JWTUtil.verfiy(credentials,username,user.getPassword())) {
            throw new AuthenticationException("用户名或密码不正确");
        }
        return new SimpleAuthenticationInfo(credentials,credentials,"Realm");
    }
}
