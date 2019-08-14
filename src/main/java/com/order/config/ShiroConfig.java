package com.order.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @ Author   ：clt.
 * @ Date     ：Created in 12:20 2019/8/6
 * @author Mrchen
 */
@Configuration
public class ShiroConfig {

    /**
     *ShiroFilterFactoryBean
     *
     * Shiro内置过滤器，可以实现权限相关的拦截器
     *    常用的过滤器：
     *       anon: 无需认证（登录）可以访问
     *       authc: 必须认证才可以访问
     *       user: 如果使用rememberMe的功能可以直接访问
     *       perms：该资源必须得到资源权限才可以访问
     *       role: 该资源必须得到角色权限才可以访问
     */

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> filterMap = new LinkedHashMap<>();

        filterMap.put("/add", "perms[user:add]");
        filterMap.put("/update", "perms[user:update]");
        filterMap.put("/testThymeleaf", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/hello", "anon");
        filterMap.put("/*", "anon");

        //修改调整的登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     *DefaultWebSecurityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     *Realm
     */
    @Bean(name="userRealm")
    public UserRealm getRealm(){
        UserRealm userRealm = new UserRealm();
        CredentialsMatcher matcher = new HashedCredentialsMatcher();
        ((HashedCredentialsMatcher) matcher).setHashAlgorithmName("MD5");
        ((HashedCredentialsMatcher) matcher).setHashIterations(1024);
        userRealm.setCredentialsMatcher(matcher);
        return userRealm;
    }

    @Bean(name = "shiroDialect")
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
