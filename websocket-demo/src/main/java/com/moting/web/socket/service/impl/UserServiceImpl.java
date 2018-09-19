package com.moting.web.socket.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moting.web.socket.mapper.UserMapper;
import com.moting.web.socket.pojo.User;
import com.moting.web.socket.service.UserService;

@Service
public class UserServiceImpl implements UserService{

//	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getUserByUsername(String username) {
		return userMapper.getUserByUsername(username);
	}

}
