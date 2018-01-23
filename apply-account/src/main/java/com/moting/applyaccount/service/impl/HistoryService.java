package com.moting.applyaccount.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moting.applyaccount.bean.History;
import com.moting.applyaccount.dao.HistoryDao;
import com.moting.applyaccount.service.IHistoryService;

@Service
public class HistoryService implements IHistoryService{

	@Resource
	private HistoryDao historyDao;
	
	@Override
	public List<History> query(History history) {
		// TODO Auto-generated method stub
		return historyDao.query();
	}

	@Override
	public void add(History history) {
		// TODO Auto-generated method stub
		historyDao.insertSelective(history);
	}

	@Override
	public void del(int id) {
		// TODO Auto-generated method stub
		historyDao.deleteByPrimaryKey(id);
	}

	@Override
	public void update(History history) {
		// TODO Auto-generated method stub
		historyDao.updateByPrimaryKeySelective(history);
	}

	@Override
	public History findId(int id) {
		// TODO Auto-generated method stub
		return historyDao.selectByPrimaryKey(id);
	}

	@Override
	public List<History> findName(String username) {
		// TODO Auto-generated method stub
		return historyDao.queryName(username);
	}

}
