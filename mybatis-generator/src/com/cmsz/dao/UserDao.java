package com.cmsz.dao;

import java.util.List;

import com.cmsz.bean.User;

public interface UserDao {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> query(User user);

	User queryById(int userid);

	User queryByUsername(String username);

}