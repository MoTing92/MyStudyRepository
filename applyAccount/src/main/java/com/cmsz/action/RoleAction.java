
package com.cmsz.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmsz.bean.ReturnMsg;
import com.cmsz.bean.ReturnRole;
import com.cmsz.bean.Role;
import com.cmsz.service.IRoleService;
/*****
 * 角色控管理
 * ****/
@Controller
@RequestMapping("/role")
public class RoleAction {

	static final Logger LOGGER = Logger.getLogger(RoleAction.class);
	
	@Resource
	IRoleService roleService;
	
	/*******
	 * 查询角色信息
	 * 请求参数为 null
	 * 响应参数为ReturnRole对象，返回状态（state）和消息(message)
	 * ****/
	@RequestMapping(value="/search",method=RequestMethod.GET)
	@ResponseBody
	public ReturnMsg<ReturnRole> findRole() {
		//封装类存放ReturnRole对象
		ReturnMsg<ReturnRole> returnMsg = new ReturnMsg<ReturnRole>();
		//查询角色集合
		List<ReturnRole> roleList =null;
		try {
			roleList= roleService.query();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error("RoleAction的search请求异常",e);
		}
		//角色集合不为空
		if(roleList.size() >0 && roleList != null){
			returnMsg.setData(roleList);
			returnMsg.setMessage("查询成功");
			returnMsg.setState("10000");
			return returnMsg;
		}else{
			returnMsg.setMessage("查询失败");
			returnMsg.setState("10001");
			return returnMsg;
		}
		
	}
	
	/***
	 * 更新角色
	 * 请求参数为Role
	 * 响应参数为返回状态（state）和消息(message)
	 * **/
	@RequestMapping(value="/update")
	@ResponseBody
	public ReturnMsg<Role> updateRole(@RequestBody Role role){
		//封装类存放Role对象
		ReturnMsg<Role> returnMsg = new ReturnMsg<Role>();
		//更新角色
		boolean falg=false;
		try {
			falg=roleService.update(role);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error("RoleAction的update请求异常",e);
		}
		//tuer ? false
		if(falg){
			returnMsg.setState("10000");
			returnMsg.setMessage("更新成功");
			return returnMsg;
		}else{
			returnMsg.setState("10001");
			returnMsg.setMessage("更新失败");
			return returnMsg;
		}
	}
	
	/****
	 * 删除角色
	 * 请求参数为null
	 * 响参数树应为null
	 * **/
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	@ResponseBody
	public ReturnMsg<Role> delete(int roleId) {
		//封装类存放Role对象
		ReturnMsg<Role> returnMsg = new ReturnMsg<Role>();
		//删除
		boolean isSuccess=false;
		try {
			isSuccess=roleService.del(roleId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error("RoleAction的delete请求异常",e);
		}
		//ture ? false
		if(isSuccess){
			returnMsg.setMessage("10000");
			returnMsg.setState("删除成功");
			return returnMsg;
		}else{
			returnMsg.setMessage("10001");
			returnMsg.setState("删除失败");
			return returnMsg;
		}
		
	}
}
