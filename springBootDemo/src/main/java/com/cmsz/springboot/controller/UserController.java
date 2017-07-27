package com.cmsz.springboot.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmsz.springboot.bean.User;
import com.cmsz.springboot.mapper.UserMapper;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Resource
	private UserMapper userMapper;
	
	@RequestMapping(value="/query")
	@ResponseBody
	public List<User> query(User user){
		List<User> list = userMapper.query();
		return list;
	}
}
