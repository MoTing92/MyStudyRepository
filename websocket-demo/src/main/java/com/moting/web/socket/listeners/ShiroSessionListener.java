package com.moting.web.socket.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroSessionListener implements SessionListener {

	Logger logger = LoggerFactory.getLogger(ShiroSessionListener.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override  
	public void onStart(Session session) {//会话创建时触发  
        System.err.println("session监听器-会话创建：sessionId = " + session.getId() +",创建时间 = " +sdf.format(new Date()));
		logger.info("session监听器-会话创建：sessionId = " + session.getId() +",创建时间 = " +sdf.format(new Date()));  
    } 
	
    @Override  
    public void onExpiration(Session session) {//会话过期时触发  
    	System.err.println("session监听器-会话过期：sessionId =" + session.getId() +",过期时间 = " +sdf.format(new Date()));
    	logger.info("session监听器-会话过期：sessionId = " + session.getId() +",过期时间 = " +sdf.format(new Date()));  
    }  
    @Override  
    public void onStop(Session session) {//退出时触发  
    	System.err.println("session监听器-会话停止：sessionId = " + session.getId() +",停止时间 = " +sdf.format(new Date()));
    	logger.info("session监听器-会话停止：sessionId = " + session.getId() +",停止时间 = " +sdf.format(new Date()));  
    }  
}
