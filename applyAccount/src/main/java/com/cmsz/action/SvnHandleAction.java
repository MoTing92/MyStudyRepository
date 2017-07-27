package com.cmsz.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmsz.bean.SvnReturn;
import com.cmsz.bean.SvnUser;

@Controller
@RequestMapping("/csvn")
public class SvnHandleAction {

	@RequestMapping(value = "/createUser",method = RequestMethod.POST)
	@ResponseBody
	public SvnReturn createUser(SvnUser svnUser,HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.sendRedirect("http://192.168.215.190:3343/csvn/api/1/user?format=json");
		return null;
	}
}
