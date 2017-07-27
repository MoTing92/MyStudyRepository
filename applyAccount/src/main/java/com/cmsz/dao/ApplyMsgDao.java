package com.cmsz.dao;

import java.util.List;

import com.cmsz.bean.ApplyMsg;
import com.cmsz.bean.RequestMsg;
import com.cmsz.bean.User;

public interface ApplyMsgDao {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ApplyMsg record);

    ApplyMsg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApplyMsg record);

	ApplyMsg look(Integer id);

	List<ApplyMsg> query(RequestMsg requestMsg);

	User queryHandlePersonByUserId(int userId);

}