package com.moting.applyaccount.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.moting.applyaccount.bean.OwnGroup;

@Mapper
public interface OwnGroupDao {
    int deleteByPrimaryKey(Integer groupId);

    int insertSelective(OwnGroup record);

    OwnGroup selectByPrimaryKey(Integer groupId);

    int updateByPrimaryKeySelective(OwnGroup record);

	List<OwnGroup> query();
}