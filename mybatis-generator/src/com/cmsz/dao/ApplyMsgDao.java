package com.cmsz.dao;

import com.cmsz.bean.ApplyMsg;

public interface ApplyMsgDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ApplyMsg record);

    int insertSelective(ApplyMsg record);

    ApplyMsg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApplyMsg record);

    int updateByPrimaryKey(ApplyMsg record);
}