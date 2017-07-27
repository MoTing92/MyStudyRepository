package com.cmsz.action;


import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmsz.bean.OwnGroup;
import com.cmsz.bean.ReturnMsg;
import com.cmsz.service.IOwnGroupService;
import com.cmsz.service.IUserService;
import com.cmsz.utils.Constant;
/***
 * 分组管理
 */
@Controller
@RequestMapping("/group")
public class OwnGroupAction {
	
	static final Logger LOGGER = Logger.getLogger(OwnGroupAction.class);

	@Resource
	private IOwnGroupService ownGroupService;
	
	@Resource
	private IUserService userService; 
	
	/**
	 * 小组查看
	 * 请求参数为null
	 * 响应参数为OwnGroup对象，返回状态（state）和消息(message)
	 * **/
	@RequestMapping("/search")
	@ResponseBody
	public ReturnMsg<OwnGroup> query(){
		//封装类存放OwnGroup对象
		ReturnMsg<OwnGroup> returnMsg = new ReturnMsg<OwnGroup>();
		//查询所有组信息
		List<OwnGroup> groupList=null;
		try {
			groupList = ownGroupService.query();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error("OwnGroupAction的search请求异常",e);
		}
		//不为空 
		if(groupList != null && groupList.size() > 0){
			returnMsg.setData(groupList);
			returnMsg.setState(Constant.SUCCESS_STATE);
			returnMsg.setMessage("查询成功");
			return returnMsg;
		}else{
			returnMsg.setData(null);
			returnMsg.setState(Constant.SUCCESS_STATE);
			returnMsg.setMessage("没有相应分组");
			return returnMsg;
		}
	}
	
	/*****
	 * 新增小组信息|| 修改小组
	 * 新增小组信息请求参数为OwnGroup对象
	 * 新增小组信息响应参数为返回状态（state）和消息(message)
	 * 修改小组请求参数为OwnGroup对象
	 * 修改小组响应参数为返回状态（state）和消息(message)
	 * ****/
	@RequestMapping("/add")
	@ResponseBody
	public ReturnMsg<OwnGroup> add(@RequestBody OwnGroup ownGroup){
		//封装类存放OwnGroup对象
		ReturnMsg<OwnGroup> returnMsg = new ReturnMsg<OwnGroup>();
		//groupID不为空修改 为空新增
		if(ownGroup.getGroupId() != null){
			//更新小组信息
			boolean isSuccess =false;
			try {
				isSuccess= ownGroupService.update(ownGroup);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LOGGER.error("OwnGroupAction的add请求的更新出现异常",e);
			}
			//true ? false
			if(isSuccess){
				returnMsg.setState(Constant.SUCCESS_STATE);
				returnMsg.setMessage("修改成功");
				return returnMsg;
			}else{
				returnMsg.setState(Constant.SUCCESS_STATE);
				returnMsg.setMessage("修改失败");
				return returnMsg;
			}
		}else{
			boolean isSuccess =false;
			try {
				isSuccess= ownGroupService.add(ownGroup);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LOGGER.error("OwnGroupAction的add出现异常",e);
			}
			if(isSuccess){
				returnMsg.setState(Constant.SUCCESS_STATE);
				returnMsg.setMessage("添加成功");
				return returnMsg;
			}else{
				returnMsg.setState(Constant.SUCCESS_STATE);
				returnMsg.setMessage("添加失败");
				return returnMsg;
			}
		}
	}
}
