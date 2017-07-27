package com.cmsz.springboot.service;

import java.util.List;

import com.cmsz.springboot.bean.User;


public interface IUserService {

	int deleteByPrimaryKey(Integer userId);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> query();

	User queryByUsername(String username);

	List<String> queryMenusByUid(Integer userId);

	List<String> queryRolesByUid(Integer userId);
}
