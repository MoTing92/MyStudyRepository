 package com.moting.applyaccount.service;

import java.util.List;

import com.moting.applyaccount.bean.ReturnRole;
import com.moting.applyaccount.bean.Role;

public interface IRoleService {

	/****
	 * 查询所有角色
	 * ****/
	

	/*boolean del(int roleId);*/
	
	Role queryById(int roleId);

	boolean update(Role role);

	boolean add(Role role);

	List<ReturnRole> query();

	boolean del(int roleId);
	
}
