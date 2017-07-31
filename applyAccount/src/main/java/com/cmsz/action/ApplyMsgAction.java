package com.cmsz.action;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmsz.bean.ApplyMsg;
import com.cmsz.bean.OwnGroup;
import com.cmsz.bean.RequestMsg;
import com.cmsz.bean.ReturnApplyMsg;
import com.cmsz.bean.ReturnMsg;
import com.cmsz.service.IApplyMsgService;
import com.cmsz.utils.Constant;
import com.github.pagehelper.PageInfo;
/************
 * 工单管理
 */
@Controller
@RequestMapping("/apply")
public class ApplyMsgAction {
	
	static final Logger LOGGER = Logger.getLogger(ApplyMsgAction.class);


	@Resource
	private IApplyMsgService applyService;
	
	
	/****
	 * 查询所有工单
	 * 请求参数为RequestMsg对象
	 * 响应参数为ReturnMsg对象
	 * ***/
	@RequestMapping("/query")
	@ResponseBody
	public ReturnMsg<ApplyMsg> query(@RequestBody RequestMsg requestMsg){
		//查询RequestMsg并放入分页插件
		PageInfo<ApplyMsg> pageInfo = null;
		try {
			pageInfo = applyService.query(requestMsg);
		} catch (Exception e) {
			LOGGER.error("ApplyMsgAction的query请求出现错误信息：", e);
		}
		//实例 话returnMsg
		ReturnMsg<ApplyMsg> returnMsg = new ReturnMsg<ApplyMsg>();
		//pageInfo对象不为空
		if(pageInfo != null){
			returnMsg.setData(pageInfo.getList());
			returnMsg.setCurrentPage(requestMsg.getCurrentPage());
			returnMsg.setPageSize(requestMsg.getPageSize());
			returnMsg.setTotalCount(pageInfo.getTotal());
			returnMsg.setState(Constant.SUCCESS_STATE);
			returnMsg.setMessage("查询成功");
			return returnMsg;
		}else{
			returnMsg.setState(Constant.SUCCESS_STATE);
			returnMsg.setMessage("查询失败");
			return returnMsg;
		}
	}
	
	/****
	 * 工单申请
	 * 请求参数为ApplyMsg对象
	 * 响应参数为返回状态（state）和消息(message)
	 * ****/
	@RequestMapping("/add")
	@ResponseBody
	public ReturnMsg<ApplyMsg> add(@RequestBody ApplyMsg applyMsg, HttpServletRequest request){
		//实例化ReturnMsg
		ReturnMsg<ApplyMsg> returnMsg=null;
		boolean isSuccess=false;
		try {
			returnMsg = new ReturnMsg<ApplyMsg>();
			//添加
			isSuccess = applyService.add(applyMsg,request);
		} catch (Exception e) {
			LOGGER.error("ApplyMsgAction的add请求异常",e);
			e.printStackTrace();
		}
		//true ? false
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
	
	/***
	 * 工单审核
	 * 请求参数为RequestMsg对象
	 * 响应参数为返回状态（state）和消息(message)
	 * **/
	@RequestMapping(value="/deal",method = RequestMethod.POST)
	@ResponseBody
	public ReturnMsg<ApplyMsg> deal(@RequestBody RequestMsg requestMsg,HttpServletRequest request){
		//审核
			ReturnMsg<ApplyMsg> re=null;
			try {
				re=applyService.deal(requestMsg, request);
			} catch (Exception e) {
				LOGGER.error("ApplyMsgAction的deal请求出现异常", e);
				e.printStackTrace();
			}
			return re;
		
	}
	
	/******
	 * 查看单条申请信息
	 * 请求参数为ApplyMsg对象
	 * 响应参数为返回状态（state）和消息(message)
	 * **/
	@RequestMapping(value="/look")
	@ResponseBody
	public ReturnApplyMsg look(@RequestBody ApplyMsg applyMsg){
		
		//实例化ReturnApplyMsg
		ReturnApplyMsg returnMsg = new ReturnApplyMsg();
		//接收工单ID
		Integer id = applyMsg.getId();
		//查看单条信息
		ApplyMsg apply=null;
		try {
			apply = applyService.look(id);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("ApplyMsgAction的look请求出现异常",e);
		}
		//不为空
		if(!apply.equals(null)){
			returnMsg.setData(apply);
			returnMsg.setState(Constant.SUCCESS_STATE);
			returnMsg.setMessage(Constant.SUCCESS_MESSAGE);
		}
		return returnMsg;
	}
	
	/****
	 * 查看所有小组 下拉框
	 * 请求参数为null
	 * 响应参数为ReturnMsg对象
	 * **/
	@RequestMapping(value="/getGroup")
	@ResponseBody
	public ReturnMsg<OwnGroup> getGroup(){
		//实例化ReturnMsg
		ReturnMsg<OwnGroup> returnMsg = new ReturnMsg<OwnGroup>();
		//添加小组小组信息
		try {
			returnMsg.setData(applyService.getGroup());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("ApplyMsgAction的getGroup请求出现异常",e);
		}
		return returnMsg;
	}
	
	/****
	 * 导出Excel
	 * 请求参数PathBean
	 * 响应参数返回状态（state）和消息(message)
	 * **/
	@RequestMapping(value="/exportApply",method = RequestMethod.GET)
	public void export(@RequestParam int id,HttpServletResponse response) {  
	    List<ApplyMsg> applys = new ArrayList<ApplyMsg>();
	    ApplyMsg apply = applyService.look(id);
	    applys.add(apply);
	    applyService.exportApply(id,response);
	}  
	
}
