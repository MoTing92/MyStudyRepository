package com.moting.applyaccount.config;

import java.util.LinkedHashMap;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import com.moting.applyaccount.realm.SecurityRealm;

@Configuration
public class ShiroConfiguration {

	@Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        //配置登录的url和登录成功的url
        bean.setLoginUrl("/login.jsp");
        bean.setUnauthorizedUrl("/refuse.jsp");
        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
        filterChainDefinitionMap.put("/login.jsp*", "anon"); 
        filterChainDefinitionMap.put("/user/login", "anon"); 
        filterChainDefinitionMap.put("/user/apply","anon");
        filterChainDefinitionMap.put("/index.html","anon");
        filterChainDefinitionMap.put("/svn.jsp","anon");
        filterChainDefinitionMap.put("/apply/getGroup", "anon");
        filterChainDefinitionMap.put("/user/getPhoneCode", "anon");
        filterChainDefinitionMap.put("/user/getCodeImage", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/scripts/**", "anon");
        filterChainDefinitionMap.put("/styles/**", "anon");
        filterChainDefinitionMap.put("/user/logout", "logout");
        filterChainDefinitionMap.put("/**", "anon");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }
    //配置核心安全事务管理器
    @Bean(name="securityManager")
    public SecurityManager securityManager(@Qualifier("securityRealm") SecurityRealm securityRealm) {
        System.err.println("--------------shiro已经加载----------------");
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(securityRealm);
        manager.setCacheManager(getEhCacheManager());
        manager.setSessionManager(sessionManager());
        return manager;
    }
    //配置自定义的权限登录器
    @Bean(name="securityRealm")
    public SecurityRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
    	SecurityRealm securityRealm = new SecurityRealm();
    	securityRealm.setCredentialsMatcher(matcher);
        return securityRealm;
    }
    //配置自定义的密码比较器
    @Bean(name="credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
//    	MatchPassword matchPassword = new MatchPassword();
//    	return matchPassword;
    	HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher("md5");
    	hashedCredentialsMatcher.setHashIterations(1);
    	return hashedCredentialsMatcher;
    }
    
    @Bean(name = "shiroEhcacheManager")  
    public EhCacheManager getEhCacheManager() {  
        EhCacheManager em = new EhCacheManager();  
        em.setCacheManagerConfigFile("classpath:shiro-cache.xml");  
        return em;  
    } 
    
    @Bean(name = "sessionDAO")
    public MemorySessionDAO memorySessionDAO(){
    	return new MemorySessionDAO();
    }
    
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager(){
    	DefaultWebSessionManager seessionManager = new DefaultWebSessionManager();
    	seessionManager.setSessionDAO(memorySessionDAO());
    	seessionManager.setGlobalSessionTimeout(60000);
    	seessionManager.setDeleteInvalidSessions(true);
    	return seessionManager;
    }
    
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
    
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }
}
