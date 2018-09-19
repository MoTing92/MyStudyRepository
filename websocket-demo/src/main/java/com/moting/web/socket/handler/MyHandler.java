/*package com.moting.web.socket.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyHandler extends TextWebSocketHandler{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	*//**
     * 建立连接后触发的回调
     *//*
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		logger.info("建立连接后出发的回调"); 
		System.err.println("建立连接后出发的回调");
  	}

	*//**
	 * 收到消息时触发的回调
	 *//*
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception{
		logger.info("收到消息时触发的回调"); 
		System.err.println("收到消息时触发的回调");
	}
	
	*//**
     * 传输消息出错时触发的回调
     *//*
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception{
		logger.info("传输消息出错时触发的回调"); 
		System.err.println("传输消息出错时触发的回调");
	}
	
	*//**
     * 断开连接后触发的回调
     *//*
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception{
		logger.info("断开连接后触发的回调"); 
		System.err.println("断开连接后触发的回调");
	}
	
	*//**
     * 是否处理分片消息
     *//*
	@Override
	public boolean supportsPartialMessages() {
		logger.info("是否处理分片消息"); 
		System.err.println("是否处理分片消息");
		return false;
	   
	}
}
*/