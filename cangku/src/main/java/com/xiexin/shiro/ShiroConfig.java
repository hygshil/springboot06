package com.xiexin.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/*
shiro的配置
目的：因为shiro可以和很多项目适配，那么我们是web项目，就需要配置成web的securityManger
又因为是web项目，所以需要使用过滤器来配置需要拦截的请求何佩拦截的请求
*/
@Configuration //配置类的注解，表明该类是配置类，该注解是配置的意思，顶替的是xml中的配置
//优先于其他注解优先执行
public class ShiroConfig {
    //    1.shiroconfig需要指明Realm是谁，并且把这个realm创建出来，这个创建指的是优先于其他的controller，service等
//对象优先创建
    @Bean
    public Realm getMybatisRealm() {
        MybatisRealm realm = new MybatisRealm();
//        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
//        matcher.setHashAlgorithmName("md5");
//        matcher.setHashIterations(1024);
//        realm.setCredentialsMatcher(matcher);  //注入匹配，注入加盐加密的匹配
        return realm;
    }

    //2.指派securityManager，因为我们是web项目所以是websecurityManager
    @Bean
    public DefaultWebSecurityManager getSecurityManger(Realm realm) {
        DefaultWebSecurityManager sm = new DefaultWebSecurityManager();
        sm.setRealm(realm);
        sm.setRememberMeManager(cookieRememberMeManager());
        return sm;
    }

    //以上就是把他们联系在一起了
    //3.subject，他们需要过滤器来获取
    @Bean
    public ShiroFilterFactoryBean getFilter(DefaultWebSecurityManager sm) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(sm);
        //使用过滤器
        Map map = new LinkedHashMap<>();  //这个map是有序的
        //不拦截的页面
        map.put("/vue.js", "anon");  //anon匿名的,任何请求都可以去访问
        map.put("/axios.min.js", "anon");  //anon匿名的,任何请求都可以去访问
        map.put("/page/login", "anon");  //anon匿名的,任何请求都可以去访问
        map.put("/user/login", "anon");
        map.put("/user/insert", "anon");  //注册也不拦截
        // map.put("/*/**", "authc");  //authc需要登录
        map.put("/*/**", "user");  //记住我的需要登录
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
     * @throws
     * @Author: 谢欣
     * @UpdateUser:
     * @Version: 0.0.1
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(org.apache.shiro.mgt.SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    // 记住我 的 功能 ， 需要 依赖于 cookie
    // cookie 的管理对象
    @Bean
    public CookieRememberMeManager cookieRememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(remeberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        // cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        //手动设置对称加密秘钥，防止重启系统后系统生成新的随机秘钥，防止导致客户端cookie无效
        cookieRememberMeManager.setCipherKey(Base64.decode("6ZmI6I2j3Y+R1aSn5BOlAA=="));
        return cookieRememberMeManager;
    }

    //记住我的cookie
    @Bean
    public SimpleCookie remeberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //设置cookie 的时间
        //防止 xss 读取cookie
        simpleCookie.setHttpOnly(true);
        simpleCookie.setPath("/");
        simpleCookie.setMaxAge(60 * 60 * 24 * 30);
        return simpleCookie;
    }

    /*
    统一异常：任何项目，都会有异常，这种异常是怎么来的？
1.代码本身有逻辑问题
2.顾客不规范操作
当1和2情况出现，会在浏览器上面显示错误提示，不美观，容易吓到客户

全局异常处理大致有三种方式
1.使用@ExceptionHandler注解
2.实现HerExceptionResolver接口
3.使用@ControllerAdvice注解
执行的范围@ExceptionHandler < @ControllerAdvice < HerExceptionResolver接口
一起出现会执行最小的
会其中一种方式即可
    */

}
