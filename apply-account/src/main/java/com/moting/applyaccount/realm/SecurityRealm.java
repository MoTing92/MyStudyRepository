package com.moting.applyaccount.realm;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;

import com.moting.applyaccount.bean.User;
import com.moting.applyaccount.service.IRoleService;
import com.moting.applyaccount.service.IUserService;
import com.moting.applyaccount.utils.Constant;


public class SecurityRealm extends AuthorizingRealm{

	@Resource
	private IUserService userService;
	
	@Resource
	private SessionDAO sessionDao;
	
	@Resource
	private IRoleService roleService;
	
	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		//2.根据用户名查询数据库，得到用户名是否存在
		User user = userService.queryByUsername(upToken.getUsername());
		//ShiroUtils.loginInOther(username);
        Collection<Session> sessions = sessionDao.getActiveSessions();
		// 取出每个在线用户
		for (Session session : sessions) {
			SimplePrincipalCollection principal = (SimplePrincipalCollection) session
					.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
			if (principal != null) {
				User user1 = (User) principal.getPrimaryPrincipal();
				if(user.getUsername().equals(user1.getUsername())){
					session.setTimeout(0);
					break;
				}
			}

		}
		//第一个参数：代表用户对象   第二个参数：代表用户在数据库的密码   第三个参数是realm的名字，可以随意起名 ，可以使用这个参数来得到第一个参数的值（Principal）
		return  new SimpleAuthenticationInfo(user,user.getPassword(),ByteSource.Util.bytes("moting".getBytes()),Constant.PASS_AUTO);
		
	}

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		User user = (User) principal.fromRealm(Constant.PASS_AUTO).iterator().next();
		// 根据 用户名到数据库中查询这个用户所拥有的所有的菜单权限
		List<String> Permissions = userService.queryMenusByUid(user.getUserId());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		if(Permissions!=null && Permissions.size()>0){
			info.addStringPermissions(Permissions);
		}
		return info;
	}

}
