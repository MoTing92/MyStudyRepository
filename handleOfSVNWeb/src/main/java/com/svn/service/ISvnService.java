package com.svn.service;

import com.svn.pojo.AdministratorsAuth;

public interface ISvnService {

	public String listRepository(String httpPath,AdministratorsAuth user);
	
	public boolean checkout(String reposPath,String savePath,AdministratorsAuth user);
}
