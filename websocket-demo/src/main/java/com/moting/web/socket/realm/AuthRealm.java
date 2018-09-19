package com.moting.web.socket.realm;


import java.util.Collection;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.moting.web.socket.handler.WebSocket;
import com.moting.web.socket.pojo.User;
import com.moting.web.socket.service.UserService;

public class AuthRealm extends AuthorizingRealm {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private SessionDAO sessionDao;
	
	@Autowired
	private WebSocket webSocket;
    
    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    	UsernamePasswordToken utoken=(UsernamePasswordToken) token;//获取用户输入的token
        String username = utoken.getUsername();
        logger.info("渠道门户-登录认证-username="+username);
        User user = null;
		try {
			logger.info("查询用户信息："+username);
			user = userService.getUserByUsername(username);
		} catch (Exception e) {
			logger.error("查询用户信息异常");
			e.printStackTrace();
			return null;
		}
        //放入shiro.调用CredentialsMatcher检验密码
		logger.info("渠道门户-登录认证-登录用户信息：\n"+user.toString());
		Collection<Session> sessions = sessionDao.getActiveSessions();
		// 取出每个在线用户
		for (Session session : sessions) {
			SimplePrincipalCollection principal = (SimplePrincipalCollection) session
					.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
			if (principal != null) {
				User userOrg = (User) principal.getPrimaryPrincipal();
				logger.info("渠道门户-登录认证-当前在线用户有："+userOrg.getUsername());
				if(user.getUsername().equals(userOrg.getUsername())){
					logger.info("用户名："+user.getUsername()+"在其他地方登录");
					webSocket.sendMessage("用户名："+user.getUsername()+"在其他地方登录");
					session.setTimeout(0);
					break;
				}
			}

		}
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(),ByteSource.Util.bytes(user.getUserId().toString()),this.getClass().getName());
        return info;
    }
    
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
    	
    	/*User user = (User) principal.fromRealm(this.getClass().getName()).iterator().next();
        logger.info("渠道门户-用户授权-当前登录用户所拥有的所有角色："+User.getRole());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		if(channelManagerEmp.getRole() != null ){
			info.addRole(channelManagerEmp.getRole());
		}*/
		return null;
    }


}
