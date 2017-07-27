package com.cmsz.service;

import java.util.List;

import com.cmsz.bean.History;

public interface IHistoryService {
	
	List<History> query(History history);
	
	void add(History history);
	
	void del(int id);
	
	void update(History history);
	
	History findId(int history);
	
	List<History> findName(String username);
}
