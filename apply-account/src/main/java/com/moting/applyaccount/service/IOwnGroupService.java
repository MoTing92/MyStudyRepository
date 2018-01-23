package com.moting.applyaccount.service;

import java.util.List;

import com.moting.applyaccount.bean.OwnGroup;


public interface IOwnGroupService {

	boolean add(OwnGroup ownGroup);
	
	boolean update(OwnGroup ownGroup);
	
	List<OwnGroup> query();

}
