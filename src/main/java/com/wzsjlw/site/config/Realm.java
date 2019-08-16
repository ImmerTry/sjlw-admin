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
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

    /**
     * 使用 JWT 替代原生 Token
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        LOGGER.info("doGetAuthorizationInfo: " + principals.toString());

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
        if (!JWTUtil.verify(credentials,username,user.getPassword())) {
            throw new AuthenticationException("token 过时");
        }
        return new SimpleAuthenticationInfo(credentials,credentials,"Realm");
    }
}
