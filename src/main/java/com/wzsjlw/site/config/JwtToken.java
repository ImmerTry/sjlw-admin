package com.wzsjlw.site.config;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 替换原生 Token
 * @author: ll
 * @version: 1.0 2019-08-12
 * @see:
 * @since:
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
