package com.moting.applyaccount.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.moting.applyaccount.bean.Role;
import com.moting.applyaccount.bean.User;
import com.moting.applyaccount.bean.UserData;

@Mapper
public interface UserDao {
	int deleteByPrimaryKey(Integer userId);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

	List<User> query();

	void deleteUserIdAndRoleIdByUserId(int userId);

	void saveRoleAndUser(@Param("userid") Integer userid,@Param("roleid") Integer roleid);

	User queryByUsername(String username);

	List<String> queryMenusByUid(Integer userId);

	User queryByPhone(String phoneNum);

	User queryTeamByGroupId(Integer groupId);

	User queryManagerUserByLevel(int level);

	List<Role> queryRoleByUserId(Integer userId);

	UserData queryUserDataByUserId(Integer userId);

	List<User> queryAll();
	
	
}