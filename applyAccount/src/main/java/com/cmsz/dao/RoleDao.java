package com.cmsz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cmsz.bean.Menu;
import com.cmsz.bean.ReturnRole;
import com.cmsz.bean.Role;

public interface RoleDao {
	int deleteByPrimaryKey(Integer roleId);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

	List<ReturnRole> query();

	void delRoleMenuById(Integer roleId);

	void saveRoleMenu(@Param("rid") int roleId, @Param("mid") int menuId);

	List<Menu> queryPowerByRoleId(Integer roleId);

	

} 