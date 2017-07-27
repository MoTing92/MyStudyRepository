 package com.cmsz.service;

import java.util.List;

import com.cmsz.bean.ReturnRole;
import com.cmsz.bean.Role;

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
