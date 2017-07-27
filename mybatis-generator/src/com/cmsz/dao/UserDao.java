package com.cmsz.dao;

import com.cmsz.bean.User;
import com.cmsz.bean.UserKey;

public interface UserDao {
    int deleteByPrimaryKey(UserKey key);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(UserKey key);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}