package com.cmsz.dao;

import com.cmsz.bean.OwnGroup;

public interface OwnGroupDao {
    int deleteByPrimaryKey(Integer groupId);

    int insert(OwnGroup record);

    int insertSelective(OwnGroup record);

    OwnGroup selectByPrimaryKey(Integer groupId);

    int updateByPrimaryKeySelective(OwnGroup record);

    int updateByPrimaryKey(OwnGroup record);
}