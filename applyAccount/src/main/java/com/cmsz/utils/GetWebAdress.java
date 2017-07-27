package com.cmsz.utils;

import javax.servlet.http.HttpServletRequest;


public class GetWebAdress {

	public static String getAdress(HttpServletRequest request){
		String path = request.getScheme() +"://"+ request.getServerName() 
				+":"+ request.getServerPort() +request.getContextPath() + "/";
//		System.out.println(path) ;
		return path;
	}
	
}
