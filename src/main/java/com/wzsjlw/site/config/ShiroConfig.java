package com.wzsjlw.site.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro 配置类
 *
 * @author: ll
 * @version: 1.0 2019/07/02
 * @see:
 * @since:
 */
@Configuration
public class ShiroConfig {


    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager manager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(manager);

        //添加 Shiro 内置过滤器
        /**
         *  anon: 无需认证（登录）可以访问
         *  authc: 必须认证才可以访问
         *  user: 如果使用 remember 的功能可以直接访问
         *  perms: 该资源必须得到资源权限才可以访问
         *  role: 该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();

        filterMap.put("/login", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);


        return shiroFilterFactoryBean;
    }

    /**
     * 创建 DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") Realm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联 Realm
        securityManager.setRealm(realm);
        return securityManager;
    }


    /**
     * 创建 Realm 类
     */
    @Bean(name = "userRealm")
    public Realm getRealm() {
        return new Realm();
    }
}
