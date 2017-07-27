package com.cmsz.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cmsz.springboot.bean.User;

@Mapper
public interface UserMapper {
//    int deleteByPrimaryKey(Integer userId);
//
//    int insert(User record);
//
//    int insertSelective(User record);
//
//    User selectByPrimaryKey(Integer userId);
//
//    int updateByPrimaryKeySelective(User record);
//
//    int updateByPrimaryKey(User record);
    @Select("select * from user")
	List<User> query();

//	User queryByUsername(String username);
//
//	List<String> queryMenusByUid(Integer userId);
//
//	List<String> queryRolesByUid(Integer userId);
}