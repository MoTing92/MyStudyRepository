package com.svn.service.impl;

import org.springframework.stereotype.Service;

import com.svn.impl.SvnBaseImpl;
import com.svn.inf.ISvn;
import com.svn.pojo.AdministratorsAuth;
import com.svn.service.ISvnService;
import com.svn.tools.ConsoleLog;

@Service
public class SvnServiceImpl extends ConsoleLog implements ISvnService{

	private ISvn svn;
	
	public void before(String account ,String password,String reposPath){
		this.svn = new SvnBaseImpl(account,password,true,reposPath);
        // 得到版本库信息
        svn.createSVNRepository();
        // 得到基础操作对象
        svn.createSVNClientManager();
	}
	
	public void after() throws Exception{
    	svn.closeRepo();
    }
	
	@Override
	public String listRepository(String httpPath,AdministratorsAuth user) {
		String response = svn.listRepository(httpPath, user.getUsername(), user.getPassword());
		return response;
	}

	@Override
	public boolean checkout(String reposPath, String savePath,AdministratorsAuth user) {
		before(user.getUsername(),user.getPassword(),reposPath);
		boolean flag = svn.checkOut(reposPath, savePath);
		return flag;
	}


}
