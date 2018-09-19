package com.moting.applyaccount.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.moting.applyaccount.bean.History;

@Mapper
public interface HistoryDao {
    int deleteByPrimaryKey(Integer id);

    int insert(History record);

    int insertSelective(History record);

    History selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(History record);

    int updateByPrimaryKey(History record);

	List<History> query();

	List<History> queryName(String username);
}