package com.svn.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.svn.pojo.AdministratorsAuth;
import com.svn.pojo.RequestBodyPara;
import com.svn.service.ISvnService;

@Controller
@RequestMapping("/svn")
public class SvnAction {

	@Resource
	private ISvnService svnService;
	
	@RequestMapping("/listRepository")
	public String listRepository(RequestBodyPara requestBody) throws Exception{
		AdministratorsAuth user = new AdministratorsAuth(requestBody.getUsername(),requestBody.getPassword());
		String response = svnService.listRepository(requestBody.getHttpPath(),user);
		return response;
	}
	
	@RequestMapping("/init")
	public String init(){
		
		return "forward:/repos/list.jsp";
	}
	
	@RequestMapping("/checkout")
	public String checkout(RequestBodyPara requestBody){
		AdministratorsAuth user = new AdministratorsAuth(requestBody.getUsername(),requestBody.getPassword());
		boolean flag = svnService.checkout(requestBody.getHttpPath(), requestBody.getSavePath(), user);
		if(flag){
			return "true";
		}
		return "false";
	}
	
	
}
