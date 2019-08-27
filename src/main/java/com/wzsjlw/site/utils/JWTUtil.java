package com.wzsjlw.site.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * JWT 工具类
 *
 * @author: ll
 * @version: 1.0 2019-08-12
 * @see:
 * @since: https://github.com/auth0/java-jwt
 */
public class JWTUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtil.class);
    /**
     * 过期时间 5 分钟
     */
    private static final long EXPIRE_TIME = 5 * 60 * 1000;

    /**
     * 校验 token 是否正确
     *
     * @param token    密匙
     * @param userName 解密方式
     * @param secret   用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String userName, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", userName)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            LOGGER.error("verify()：" + e.getMessage());
            return false;
        }
    }

    /**
     * 获取 token 中的信息不通过 secret 解密
     *
     * @param token 包含的用户名
     * @return 包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            LOGGER.error("getUsername()：" + e.getMessage());
            return null;
        }
    }

    /**
     * 生成签名 5min过期时间
     *
     * @param userName 用户名
     * @param secret   用户的密码
     * @return 加密后的 token
     */
    public static String sign(String userName, String secret) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withClaim("username", userName)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            return null;
        }
    }
}
