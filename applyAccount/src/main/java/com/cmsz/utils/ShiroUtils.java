package com.cmsz.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;

import com.cmsz.bean.User;

public class ShiroUtils {

	@Resource
	private static SessionDAO sessionDao;
	
	/**
	 * 获取当前登录用户
	 * @return
	 */
	public static User getCurrentUser(){
		Subject subject = SecurityUtils.getSubject();
		return (User) subject.getPrincipal();
	}
	/**
	 * 获取所有在线用户
	 * @return
	 */
	public static List<User> getAllActiveUser(){
		Collection<Session> sessions = sessionDao.getActiveSessions();
		List<User> userList = new ArrayList<User>();
		for (Session session : sessions) {
			SimplePrincipalCollection principal = 
					(SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
			if(principal != null){
				User user = (User) principal.getPrimaryPrincipal();
				userList.add(user);
			}
		}
		return userList;
	}
	/**
	 * 实现单点登录
	 */
	public static void loginInOther(String username){
		
		Collection<Session> sessions = sessionDao.getActiveSessions();
		// 取出每个在线用户
		for (Session session : sessions) {
			SimplePrincipalCollection principal = (SimplePrincipalCollection) session
					.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
			if (principal != null) {
				User user = (User) principal.getPrimaryPrincipal();
				if(username.equals(user.getUsername())){
					session.setTimeout(0);
					break;
				}
			}
		}
	}
}
