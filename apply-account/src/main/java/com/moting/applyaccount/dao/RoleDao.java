package com.moting.applyaccount.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.moting.applyaccount.bean.Menu;
import com.moting.applyaccount.bean.ReturnRole;
import com.moting.applyaccount.bean.Role;

@Mapper
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