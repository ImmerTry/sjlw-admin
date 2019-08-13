package com.wzsjlw.site.config;

import com.fasterxml.jackson.core.sym.NameN;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.Date;
import java.util.HashMap;
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
     * 开启 shiro AOP 注解支持
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager manager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //添加自己的过滤器
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(manager);
        shiroFilterFactoryBean.setUnauthorizedUrl("/401");
        //添加 Shiro 内置过滤器
        /**
         *  anon: 无需认证（登录）可以访问
         *  authc: 必须认证才可以访问
         *  user: 如果使用 remember 的功能可以直接访问
         *  perms: 该资源必须得到资源权限才可以访问
         *  role: 该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterRuleMap = new HashMap<>();

        filterRuleMap.put("/**", "jwt");
        filterRuleMap.put("/401", "anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建 DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") Realm realm) {
        // 关联 Realm
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);

        // 关闭自带的 session
        DefaultSessionStorageEvaluator evaluator = new DefaultSessionStorageEvaluator();
        evaluator.setSessionStorageEnabled(false);

        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        subjectDAO.setSessionStorageEvaluator(evaluator);
        // 关联 session
        securityManager.setSubjectDAO(subjectDAO);
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
