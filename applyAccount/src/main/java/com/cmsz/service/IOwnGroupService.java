package com.cmsz.service;

import java.util.List;

import com.cmsz.bean.OwnGroup;

public interface IOwnGroupService {

	boolean add(OwnGroup ownGroup);
	
	boolean update(OwnGroup ownGroup);
	
	List<OwnGroup> query();

}
