package com.cmsz.service;

import java.util.List;

import com.cmsz.bean.PageMsg;
import com.cmsz.bean.ResetPara;
import com.cmsz.bean.ReturnMsg;
import com.cmsz.bean.Role;
import com.cmsz.bean.User;
import com.cmsz.bean.UserData;
import com.github.pagehelper.PageInfo;

public interface IUserService {

	PageInfo<User> query(PageMsg page);

	void add(User user);

	boolean update(User user);

	boolean delete(int userId);

	
	/****
	 * 修改用户角色
	 * ***/ 
	void roleUserUpdate(User user);

	User queryByUsername(String username);

	List<String> queryMenusByUid(Integer userId);

	User queryByPhoneNumber(String phone);

	List<Role> queryRoleIdByUserId(Integer userId);

	UserData queryUserDatabyUid(Integer userId);

	ReturnMsg<User> resetPassword(ResetPara reset);
}
