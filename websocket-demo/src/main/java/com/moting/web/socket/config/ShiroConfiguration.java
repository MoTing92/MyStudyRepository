package com.moting.web.socket.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.moting.web.socket.listeners.ShiroSessionListener;
import com.moting.web.socket.realm.AuthRealm;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;


@Configuration
public class ShiroConfiguration {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        //配置登录的url和登录成功的url
        bean.setLoginUrl("/html/login/login.html");
        bean.setUnauthorizedUrl("/html/index.html");
        bean.setSuccessUrl("/index.html");
        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
        filterChainDefinitionMap.put("/html/login/change_password.html", "anon");
        filterChainDefinitionMap.put("/html/login/reset_password.html", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/resetPassword", "anon");
        filterChainDefinitionMap.put("/user/modifiedPassword", "anon");
        filterChainDefinitionMap.put("/user/logout","logout");
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/js/channelManagement/**","authc");
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/img/**","anon");
        filterChainDefinitionMap.put("/**", "anon");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }
    //配置核心安全事务管理器
    @Bean(name="securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        logger.info("正在加载shiro权限框架安全管理器：SecurityManager······");
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        manager.setCacheManager(getEhCacheManager());
        manager.setSessionManager(sessionManager());
        manager.setRememberMeManager(rememberMeManager());
        logger.info("加载shiro权限框架安全管理器：SecurityManager完成！！！！！！");
        return manager;
    }
    @Bean(name = "sessionDAO")
    public MemorySessionDAO memorySessionDAO(){
    	logger.info("实例化shiro权限框架：MemorySessionDAO······");
    	return new MemorySessionDAO();
    }
    
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager(){
    	logger.info("实例化shiro权限框架session管理器：DefaultWebSessionManager······");
    	DefaultWebSessionManager seessionManager = new DefaultWebSessionManager();
    	seessionManager.setSessionDAO(memorySessionDAO());
    	List<SessionListener> listeners = new ArrayList<>();
    	listeners.add(new ShiroSessionListener());
    	seessionManager.setSessionListeners(listeners);
//    	设置会话的有效时间
//    	seessionManager.setGlobalSessionTimeout(10*60*1000);
    	//删除过期会话
    	seessionManager.setDeleteInvalidSessions(true);
    	return seessionManager;
    }
    
	//配置自定义的权限登录器
    @Bean(name="authRealm")
    public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
    	logger.info("实例化shiro权限框架自定义Realm：AuthRealm·······");
    	AuthRealm authRealm=new AuthRealm();
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }
    //配置自定义的密码比较器
    @Bean(name="credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
    	logger.info("实例化shiro权限框架密码匹配器：CredentialsMatcher······");
    	HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher("md5");
    	hashedCredentialsMatcher.setHashIterations(1);
    	return hashedCredentialsMatcher;
    }
    //shiro缓存管理器
    @Bean(name = "shiroEhcacheManager")  
    public EhCacheManager getEhCacheManager() { 
    	logger.info("实例化shiro权限框架缓存管理器：EhCacheManager······");
        EhCacheManager em = new EhCacheManager();  
        em.setCacheManagerConfigFile("classpath:shiro-cache.xml");  
        return em;  
    }
    //记住我cookie
    @Bean("rememberMeCookie")
    public SimpleCookie rememberMeCookie(){
    	logger.info("实例化shiro权限框架cookie：SimpleCookie······");
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }
    //记住我cookie管理器
    @Bean("rememberMeManager")
    public CookieRememberMeManager rememberMeManager(){
    	logger.info("实例化shiro权限框架cookie管理器：CookieRememberMeManager······");
    	CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }
    /**
     * 该类可以保证实现了org.apache.shiro.util.Initializable接口的shiro对象的init或者是destory方法被自动调用，
     * 而不用手动指定init-method或者是destory-method方法
     * 注意：如果使用了该类，则不需要手动指定初始化方法和销毁方法，否则会出错
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
    	return new LifecycleBeanPostProcessor();
    }
    //下面两个配置主要用来开启shiro aop注解支持，使用代理方式，所以需要开启代码支持。
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
