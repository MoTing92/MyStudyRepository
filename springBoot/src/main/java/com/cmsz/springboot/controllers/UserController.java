package com.cmsz.springboot.controllers;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cmsz.springboot.domain.User;
import com.cmsz.springboot.service.IUserService;


@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/query")
	@ResponseBody
	public List<User> query() {
		List<User> userIngfo;
		try {
			User user = new User();
			user.setRealName("MoTing");
			List<User> userList = userService.query(user);
			userIngfo = userList;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return userIngfo;
	}
	
	@RequestMapping(value="/add")
	@ResponseBody
	public int add() {
		int a = -1;
		User user=new User();
		user.setUsername("moting");
//		user.setPassword("123");
		user.setRealName("MoTing3");
		try {
			a = userService.add(user);
		}catch(Exception ex) {
			ex.printStackTrace();
			return a;
		}
		return a;
	}
	
	@RequestMapping(value="/del")
	@ResponseBody
	public int delete() {
		int a;
		try {
			a = userService.delete(9,10);
		}catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
		return a;
	}
	
	@RequestMapping(value="/upadte")
	@ResponseBody
	public int upadte() {
		int a;
		User user=new User();
		user.setUsername("moting");
		user.setPassword("123");
		user.setRealName("MoTing");
		try {
			a = userService.add(user);
		}catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
		return a;
	}
} 
