package com.cmsz.dao;

import com.cmsz.bean.OpenUserMsg;

public interface OpenUserMsgDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OpenUserMsg record);

    int insertSelective(OpenUserMsg record);

    OpenUserMsg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OpenUserMsg record);

    int updateByPrimaryKey(OpenUserMsg record);
}