package com.cmsz.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.cmsz.bean.ApplyMsg;
import com.cmsz.bean.OwnGroup;
import com.cmsz.bean.RequestMsg;
import com.cmsz.bean.ReturnMsg;
import com.cmsz.bean.User;
import com.cmsz.dao.ApplyMsgDao;
import com.cmsz.dao.OwnGroupDao;
import com.cmsz.dao.UserDao;
import com.cmsz.service.IApplyMsgService;
import com.cmsz.utils.Constant;
import com.cmsz.utils.ExportExcel;
import com.cmsz.utils.GetWebAdress;
import com.cmsz.utils.SendEMailUtils;
import com.cmsz.utils.ShiroUtils;
import com.cmsz.utils.TimeTypeTransform;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ApplyMsgService implements IApplyMsgService {

	@Resource
	private ApplyMsgDao applyDao;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private OwnGroupDao groupDao;

	
	/***
	 * 查看工单申请记录
	 * **/
	@Override
	public PageInfo<ApplyMsg> query(RequestMsg requestMsg) {
		//当前页
		int currentPage = requestMsg.getCurrentPage();
		//每页显示条数
		int pageSize = requestMsg.getPageSize();
		//分页插件放入当前页和显示条数
		PageHelper.startPage(currentPage, pageSize);
		//查询工单信息
		List<ApplyMsg> applyList = applyDao.query(requestMsg);
		//循环遍历工单
		for (ApplyMsg apply : applyList) {
			//下一级处理人
			User handleUser = userDao.selectByPrimaryKey(apply.getHandlePerson());
			//将名称set到工单申请临时属性（对应HandlePerson）
			apply.setHandlePersonName(handleUser.getRealName());
			//接收状态	
			Integer applyStatus = apply.getApplyStatus();
			    //审核中
			if(applyStatus.equals(Constant.In_PASS)){
				apply.setApplyStatusStr(Constant.In_PASS_Str);
				//审核通过
			}else if(applyStatus.equals(Constant.Has_PASS)){
				apply.setApplyStatusStr(Constant.Has_PASS_Str);
				//审核未通过
			}else if(applyStatus.equals(Constant.UNPASS)){
				apply.setApplyStatusStr(Constant.UNPASS_Str);
			}
		}
		//将集合放入分页对象
		PageInfo<ApplyMsg> pageInfo = new PageInfo<ApplyMsg>(applyList);
		return pageInfo;
	}

	/******
	 * 申请工单
	 * ***/
	@Override
	public boolean add(ApplyMsg applyMsg,HttpServletRequest request) {
		//获取登录请求的地址：http://l92.168.xxx.xx:80xx/applyAccount/
		String path = GetWebAdress.getAdress(request);
		//申请初始状态为1
		applyMsg.setApplyStatus(1);
		//开始时间
		applyMsg.setStartTime(TimeTypeTransform.setTimeSecondsToDate(applyMsg.getStartTimeStr()));
		//到期时间
		applyMsg.setEndTime(TimeTypeTransform.setTimeSecondsToDate(applyMsg.getEndTimeStr()));
		//查找下一级处理人
		User handlePerson = applyDao.queryHandlePersonByUserId(applyMsg.getUserId());
		if(handlePerson.getUserId().equals(applyMsg.getUserId())){
			//主管
			int managerUserevel = 3;
			User managerUser = userDao.queryManagerUserByLevel(managerUserevel);
			applyMsg.setHandlePerson(managerUser.getUserId());
		}else{
			//将名称set到工单申请临时属性（对应HandlePerson）
			applyMsg.setHandlePerson(handlePerson.getUserId());
		}
		//添加申请工单
		applyDao.insertSelective(applyMsg);
		if(!applyMsg.getId().equals(null)){
			try {
				SendEMailUtils.sendMsg(ShiroUtils.getCurrentUser().getRealName(), handlePerson, Constant.EMAIL_TITLE, Constant.EMAIL_Content + path, Constant.Test_Email_Username, Constant.Test_Email_Password);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}else{
			return false;
		}
	}

	/*****
	 * 审核流程
	 * **/
	@Override
	public ReturnMsg<ApplyMsg> deal(RequestMsg requestMsg,HttpServletRequest request) {
		//获取登录请求的地址：http://l92.168.xxx.xx:80xx/applyAccount/
		String path = GetWebAdress.getAdress(request);
		//实例化ReturnMsg对象
		ReturnMsg<ApplyMsg> returnMsg = new ReturnMsg<ApplyMsg>();
		//申请记录
		ApplyMsg applyMsg = applyDao.selectByPrimaryKey(requestMsg.getId());
		//获取当前登录的用户
		User currentUser = ShiroUtils.getCurrentUser();
		//申请人的申请状态为1（审核中）
		if(applyMsg.getApplyStatus().equals(1)){
			//申请人
			User applyUser = userDao.selectByPrimaryKey(applyMsg.getUserId());
			//申请人组长
			User teamUser = userDao.queryTeamByGroupId(applyUser.getGroupId());
			//主管
			int managerUserevel = 3;
			User managerUser = userDao.queryManagerUserByLevel(managerUserevel);
			//配置管理员
			int configureUserevel = 4;
			User configureUser = userDao.queryManagerUserByLevel(configureUserevel);
			if(!applyMsg.getHandlePerson().equals(currentUser.getUserId())){
				returnMsg.setState(Constant.SUCCESS_STATE);
				returnMsg.setMessage(Constant.NO_permission);
				return returnMsg;
			}
			SimpleDateFormat fm = new SimpleDateFormat("yyyy年MM月dd日");
			//下一级处理人为小组的组长
			if(applyMsg.getHandlePerson().equals(teamUser.getUserId())){
				//状态为通过
				if(requestMsg.getApplyStatus() == 2){
					applyMsg.setHandlePerson(managerUser.getUserId());
					Date currentDate = new Date();
					String fd = fm.format(currentDate);
					applyMsg.setRemark(teamUser.getRealName()+":"+requestMsg.getRemark()+":"+fd);
					//更新工单
					applyDao.updateByPrimaryKeySelective(applyMsg);
					returnMsg.setState(Constant.SUCCESS_STATE);
					returnMsg.setMessage(Constant.SUCCESS_MESSAGE);
					returnMsg.setMessage(teamUser.getRealName()+Constant.PASS_WAIT+managerUser.getRealName()+Constant.NEXT_PASS);
					try {
						//发送邮件
						SendEMailUtils.sendMsg(teamUser.getRealName(), managerUser, Constant.EMAIL_TITLE, Constant.EMAIL_Content + path, Constant.Test_Email_Username, Constant.Test_Email_Password);
						return returnMsg;
					} catch (Exception e) {
						e.printStackTrace();
					}
					//状态是拒绝
				}else if(requestMsg.getApplyStatus() == 3){
					applyMsg.setApplyStatus(requestMsg.getApplyStatus());
					Date currentDate = new Date();
					String fd = fm.format(currentDate);
					applyMsg.setRemark(teamUser.getRealName()+":"+requestMsg.getRemark()+":"+fd);
					//更新工单
					applyDao.updateByPrimaryKeySelective(applyMsg);
					returnMsg.setState(Constant.SUCCESS_STATE);
					returnMsg.setMessage(Constant.FAIL_MESSAGE);
					return returnMsg;
				}
				//下一级处理人为主管
			}else if(applyMsg.getHandlePerson().equals(managerUser.getUserId())){
				//状态为通过
				if(requestMsg.getApplyStatus() == 2){
					applyMsg.setHandlePerson(configureUser.getUserId());
					Date currentDate = new Date();
					String fd = fm.format(currentDate);
					applyMsg.setRemark(applyMsg.getRemark()+";"+managerUser.getRealName()+":"+requestMsg.getRemark()+":"+fd);
					//更新工单
					applyDao.updateByPrimaryKeySelective(applyMsg);
					returnMsg.setState(Constant.SUCCESS_STATE);
					returnMsg.setMessage(Constant.SUCCESS_MESSAGE);
					returnMsg.setMessage(managerUser.getRealName()+Constant.PASS_WAIT+configureUser.getRealName()+Constant.NEXT_PASS);
					try {
						//发送邮件
						SendEMailUtils.sendMsg(managerUser.getRealName(), configureUser, Constant.EMAIL_TITLE, Constant.EMAIL_Content + path, Constant.Test_Email_Username, Constant.Test_Email_Password);
						return returnMsg;
					} catch (Exception e) {
						e.printStackTrace();
					}
					//状态为拒绝
				}else if(requestMsg.getApplyStatus() == 3){
					applyMsg.setApplyStatus(requestMsg.getApplyStatus());
					Date currentDate = new Date();
					String fd = fm.format(currentDate);
					applyMsg.setRemark(applyMsg.getRemark()+";"+managerUser.getRealName()+":"+requestMsg.getRemark()+":"+fd);
					//更新工单
					applyDao.updateByPrimaryKeySelective(applyMsg);
					returnMsg.setState(Constant.SUCCESS_STATE);
					returnMsg.setMessage(Constant.FAIL_MESSAGE);
					return returnMsg;
				}
				//下一级处理人配置管理员
			}else if(applyMsg.getHandlePerson().equals(configureUser.getUserId())){
				//状态为通过
				if(requestMsg.getApplyStatus() == 2){
					applyMsg.setApplyStatus(2);
					Date currentDate = new Date();
					String fd = fm.format(currentDate);
					applyMsg.setRemark(applyMsg.getRemark()+";"+configureUser.getRealName()+":"+requestMsg.getRemark()+":"+fd);
					//更新工单
					applyDao.updateByPrimaryKeySelective(applyMsg);
					returnMsg.setState(Constant.SUCCESS_STATE);
					returnMsg.setMessage(Constant.Has_END_PASS);
					try {
						//发送邮件
						SendEMailUtils.sendMsg(configureUser.getRealName(), applyUser, Constant.EMAIL_TITLE_NOTE, Constant.EMAIL_Content_NOTE, Constant.Test_Email_Username, Constant.Test_Email_Password);
						return returnMsg;
					} catch (Exception e) {
						e.printStackTrace();
					}
					//状态为拒绝
				}else if(requestMsg.getApplyStatus() == 3){
					applyMsg.setApplyStatus(requestMsg.getApplyStatus());
					Date currentDate = new Date();
					String fd = fm.format(currentDate);
					applyMsg.setRemark(applyMsg.getRemark()+";"+configureUser.getRealName()+":"+requestMsg.getRemark()+":"+fd);
					//更新工单
					applyDao.updateByPrimaryKeySelective(applyMsg);
					returnMsg.setState(Constant.SUCCESS_STATE);
					returnMsg.setMessage(Constant.FAIL_MESSAGE);
					return returnMsg;
				}
			}
			//状态为通过
		}else if(applyMsg.getApplyStatus() == 2){
			returnMsg.setState(Constant.SUCCESS_STATE);
			returnMsg.setMessage(Constant.REPEAT_PASS);
			return returnMsg;
		}else{
			returnMsg.setState(Constant.SUCCESS_STATE);
			returnMsg.setMessage(Constant.Has_UNPASS);
			return returnMsg;
		}
		return returnMsg;
	}
	
	/******
	 * 查询单条工单信息
	 * ***/
	@Override
	public ApplyMsg look(Integer id) {
		//接收单条信息
		ApplyMsg apply = applyDao.look(id);
		//开始时间
		Date startTime = apply.getStartTime();
		//结束时间
		Date endTime = apply.getEndTime();
		apply.setStartTimeStr(TimeTypeTransform.getTimeForSeconds(startTime));
		apply.setEndTimeStr(TimeTypeTransform.getTimeForSeconds(endTime));
		Integer accountProperties = apply.getAccountProperties();
		if(accountProperties.equals(1)){
			apply.setAccountPropertiesName(Constant.ACCOUNT_PROPERTICES_STR1);
		}else if(accountProperties.equals(2)){
			apply.setAccountPropertiesName(Constant.ACCOUNT_PROPERTICES_STR2);
		}else if(accountProperties.equals(3)){
			apply.setAccountPropertiesName(Constant.ACCOUNT_PROPERTICES_STR3);
		}else{
			apply.setAccountPropertiesName(Constant.ACCOUNT_PROPERTICES_STR4);
		}
		//找到用户
		User user = userDao.selectByPrimaryKey(apply.getUserId());
		//分组名
		String groupName = groupDao.selectByPrimaryKey(user.getGroupId()).getGroupName();
		apply.setGroupName(groupName);
		apply.setRealName(user.getRealName());
		apply.setPhoneNumber(user.getPhoneNumber());
		apply.setWorkCom(user.getWorkCom());
		apply.setWorkDuty(user.getWorkDuty());
		apply.setFourA(user.getFourA());
		apply.setEmail(user.getEmail());
		return apply;
	}

	/**
	 * 查询所有小组信息
	 * */
	@Override
	public List<OwnGroup> getGroup() {
		return groupDao.query();
	}

	@Override
	public void exportApply(int id,HttpServletResponse response) {
		try {
			ApplyMsg apply = applyDao.look(id);
			ExportExcel.exportExcel(apply,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
