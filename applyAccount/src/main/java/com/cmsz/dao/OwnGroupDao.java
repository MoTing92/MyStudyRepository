package com.cmsz.dao;

import java.util.List;

import com.cmsz.bean.OwnGroup;

public interface OwnGroupDao {
    int deleteByPrimaryKey(Integer groupId);

    int insertSelective(OwnGroup record);

    OwnGroup selectByPrimaryKey(Integer groupId);

    int updateByPrimaryKeySelective(OwnGroup record);

	List<OwnGroup> query();
}