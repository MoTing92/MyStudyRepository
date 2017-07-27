package com.cmsz.springboot.service;

import java.util.List;

import com.cmsz.springboot.domain.User;

public interface IUserService {

	List<User> query(User user);
	
	int add(User user);

	int delete(int ... userId);
	
	int update(User user);
	
	
}
