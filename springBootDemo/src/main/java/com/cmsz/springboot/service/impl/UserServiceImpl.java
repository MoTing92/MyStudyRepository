package com.cmsz.springboot.service.impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmsz.springboot.bean.User;
import com.cmsz.springboot.mapper.UserMapper;
import com.cmsz.springboot.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public int deleteByPrimaryKey(Integer userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User selectByPrimaryKey(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> query() {
		// TODO Auto-generated method stub
		return userMapper.query();
	}

	@Override
	public User queryByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> queryMenusByUid(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> queryRolesByUid(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
