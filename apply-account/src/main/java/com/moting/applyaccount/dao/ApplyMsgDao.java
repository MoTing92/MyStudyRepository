package com.moting.applyaccount.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.moting.applyaccount.bean.ApplyMsg;
import com.moting.applyaccount.bean.RequestMsg;
import com.moting.applyaccount.bean.User;

@Mapper
public interface ApplyMsgDao {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ApplyMsg record);

    ApplyMsg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApplyMsg record);

	ApplyMsg look(Integer id);

	List<ApplyMsg> query(RequestMsg requestMsg);

	User queryHandlePersonByUserId(int userId);

}