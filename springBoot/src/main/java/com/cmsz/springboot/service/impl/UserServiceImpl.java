package com.cmsz.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cmsz.springboot.domain.User;
import com.cmsz.springboot.mapper.UserMapper;
import com.cmsz.springboot.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private UserMapper userMapper;
	
	@Override
	public List<User> query(User user) {
		List<User> userList = new ArrayList<User>();
		if(user != null){
			userList = userMapper.query(user);
		}else{
			userList = userMapper.queryAll();
		}
		return userList ;
	}

	@Override
	public int add(User user) {
		int influenceRow = userMapper.insertSelective(user);
		return influenceRow;
	}

	@Override
	public int delete(int ...userId) {
		int k = 0;
		for (int i = 0; i < userId.length; i++) {
			userMapper.delete(userId[i]);
			k++;
		}
		return k;
	}

	@Override
	public int update(User user) {
		int influenceRow = userMapper.update(user);
		return influenceRow;
	}

}
