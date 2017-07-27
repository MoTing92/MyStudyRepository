package com.cmsz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cmsz.bean.Role;
import com.cmsz.bean.User;
import com.cmsz.bean.UserData;

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
	
	
}