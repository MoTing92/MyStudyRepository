package com.cmsz.dao;

import java.util.List;

import com.cmsz.bean.History;

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