package com.moting.applyaccount.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.moting.applyaccount.bean.PageMsg;
import com.moting.applyaccount.bean.ResetPara;
import com.moting.applyaccount.bean.ReturnMsg;
import com.moting.applyaccount.bean.Role;
import com.moting.applyaccount.bean.User;
import com.moting.applyaccount.bean.UserData;

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
