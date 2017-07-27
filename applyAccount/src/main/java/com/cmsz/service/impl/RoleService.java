
package com.cmsz.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cmsz.bean.Menu;
import com.cmsz.bean.ReturnMenu;
import com.cmsz.bean.ReturnRole;
import com.cmsz.bean.Role;
import com.cmsz.dao.RoleDao;
import com.cmsz.service.IRoleService;

@Service
public class RoleService implements IRoleService {

	@Resource
	private RoleDao roleDao;

	/******
	 * 查询角色信息
	 * ***/
	@Override
	public List<ReturnRole> query() {
		//定义ReturnRole集合
		List<ReturnRole> list = new ArrayList<ReturnRole>();
		//查询角色信息
		List<ReturnRole> roleList = roleDao.query();
		//循环roleList遍历
		for (ReturnRole role2 : roleList) {
			//查询权限信息
			List<Menu> powerList = roleDao.queryPowerByRoleId(role2.getRoleId());
			//定义ReturnMenu集合
			List<ReturnMenu> menuList = new ArrayList<ReturnMenu>();
			//循环powerList遍历
			for (Menu menu : powerList) {
				ReturnMenu rm = new ReturnMenu();
				rm.setPower(menu.getMenuName());
				rm.setValue(menu.getMenuid());
				menuList.add(rm);
				role2.setRolePower(menuList);
			}
			list.add(role2);
		}
		return list;
	}

	/***
	 * 删除角色
	 * **/
	@Override
	public boolean del(int roleId) {
		//删除角色权限
		roleDao.deleteByPrimaryKey(roleId);
		//删除角色
		roleDao.delRoleMenuById(roleId);
		//查询角色Id
		Role role = roleDao.selectByPrimaryKey(roleId);
		//查询角色权限
		List<Menu> menuList = roleDao.queryPowerByRoleId(roleId);
		//角色Id null  角色权限null
		if (role == null && menuList.size() == 0){
			return true;
		} else {
			return false;
		}

	}

	/****
	 * 查询角色ID
	 * **/
	@Override
	public Role queryById(int roleId) {
		return roleDao.selectByPrimaryKey(roleId);
	}

	/****
	 * 修改角色
	 * **/
	@Override
	public boolean update(Role role) {
		//先删除角色权限
		roleDao.delRoleMenuById(role.getRoleId());
		//修改
	//	roleDao.updateByPrimaryKeySelective(role);
		//定义角色权限集合
		List<Integer> menuList2 = role.getMenuId();
		//不为空
		if (menuList2 != null && menuList2.size() > 0) {
			//遍历集合保存角色权限
			for (Integer menuId : menuList2) {
				roleDao.saveRoleMenu(role.getRoleId(), menuId);		
			}			
		}
		return true;
		
	}

	@Override
	public boolean add(Role role) {
		/*// TODO Auto-generated method stub
		roleDao.insertSelective(role);
		int roleId = role.getRoleId();
//		System.out.println(roleId);
		List<Integer> menuList = role.getRolePower();
		if (menuList != null && menuList.size() > 0) {
			for (Integer menuId : menuList) {
				roleDao.saveRoleMenu(roleId, menuId);		
			}			
		}
		if (role.getRoleId() != null) {
			return true;
		} else {
		}*/
		return false;
	}

}
