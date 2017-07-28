package com.cmsz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cmsz.bean.OwnGroup;
import com.cmsz.bean.PageMsg;
import com.cmsz.bean.ResetPara;
import com.cmsz.bean.ReturnMsg;
import com.cmsz.bean.Role;
import com.cmsz.bean.User;
import com.cmsz.bean.UserData;
import com.cmsz.dao.OwnGroupDao;
import com.cmsz.dao.UserDao;
import com.cmsz.service.IUserService;
import com.cmsz.utils.Constant;
import com.cmsz.utils.Encrypt;
import com.cmsz.utils.TimeTypeTransform;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserService implements IUserService {

	@Resource
	private UserDao userDao;
	
	@Resource
	private OwnGroupDao groupDao;
	
	/***
	 * 查询用户
	 * 将角色信息添加到用户集合
	 * **/
	@Override
	public PageInfo<User> query(PageMsg page) {
		//添加分页信息
		PageHelper.startPage(page.getCurrentPage(), page.getPageSize());
		//查询用户信息
		List<User> userList = userDao.query();
		//循环用户信息集合
		for (User user2 : userList) {
			//查询角色信息
			List<Role> roles = userDao.queryRoleByUserId(user2.getUserId());
			//定义角色ID集合
			List<Integer> roleIds = new ArrayList<Integer>();
			//定义角色名称集合
			String roleNames = "";
			//循环角色信息
			for (Role role2 : roles) {
				//将角色ID添加到roleIds
				roleIds.add(role2.getRoleId());
				//讲角色名称添加到roleNames
				roleNames += role2.getRoleName()+"/";
			}
			//注册时间转换
			user2.setRegisterTimeStr(TimeTypeTransform.getTimeForSeconds(user2.getRegisterTime()));
			user2.setExpireTimeStr(TimeTypeTransform.getTimeForSeconds(user2.getExpireTime()));
			//将角色名称添加到用户信息
			user2.setRoleName(roleNames);
			//将角色Id添加到用户信息
			user2.setRoleIds(roleIds);
		}
		//讲集合放进分页对象
		PageInfo<User> pageInfo = new PageInfo<User>(userList);
		//返回对象
		return pageInfo;
	}

	/******
	 * 注册
	 * ****/
	@Override
	public void add(User user) {
		//注册用户为普通员工
		user.setLevel(1);
		//md5加密
		String pwd = Encrypt.md5(user.getPassword(), Constant.PASS);
		//set进用户
		user.setPassword(pwd);
		//执行新增
		userDao.insertSelective(user);
		Date registerTime = userDao.selectByPrimaryKey(user.getUserId()).getRegisterTime();
		Date expireTime = TimeTypeTransform.setTimeSecondsToDateAfterYear(registerTime);
		user.setExpireTime(expireTime);
		userDao.updateByPrimaryKeySelective(user);
	}

	/***
	 * 修改
	 * **/
	@Override
	public boolean update(User user) {
		userDao.updateByPrimaryKeySelective(user);
		return true;
	}

	/***
	 * 删除
	 * */
	@Override
	public boolean delete(int userId) {
		//先删除用户角色表Id
		userDao.deleteUserIdAndRoleIdByUserId(userId);
		//在删除用户表Id
		userDao.deleteByPrimaryKey(userId);
		try {
			User user = userDao.selectByPrimaryKey(userId);
			if(user == null){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	/****
	 * 管理员修改用户角色
	 * **/
	@Override 
	public void roleUserUpdate(User user) {
		//先删除用户角色
		userDao.deleteUserIdAndRoleIdByUserId(user.getUserId());
//		userDao.updateByPrimaryKeySelective(user);
		//角色集合
		List<Integer> roleList=user.getRoleIds();
		//角色集合不为空
		if(roleList != null && roleList.size()>0){
			//循环角色
			for (Integer roleId : roleList) {
				//添加到用户角色
				userDao.saveRoleAndUser(user.getUserId(), roleId);
			}
		}
	}

	/*****
	 * 查询用户名
	 * ***/
	@Override
	public User queryByUsername(String username) {
		return userDao.queryByUsername(username);
	}

	/***
	 *查询菜单权限 
	 * **/
	@Override
	public List<String> queryMenusByUid(Integer userId) {
		return userDao.queryMenusByUid(userId);
	}

	/***
	 * 查询用户电话
	 * ***/
	@Override
	public User queryByPhoneNumber(String phone) {
		return userDao.queryByPhone(phone);
	}

	/***
	 * 查询角色
	 * **/
	@Override
	public List<Role> queryRoleIdByUserId(Integer userId) {
		return userDao.queryRoleByUserId(userId);
	}
	
	/****
	 * 通过ID查询单条用户记录
	 * ***/
	@Override
	public UserData queryUserDatabyUid(Integer userId) {
		UserData userData = userDao.queryUserDataByUserId(userId);
		OwnGroup group = groupDao.selectByPrimaryKey(userData.getGroupId());
		userData.setGroupName(group.getGroupName());
		return userData;
	}

	@Override
	public ReturnMsg<User> resetPassword(ResetPara reset) {
		ReturnMsg<User> returnMsg = new ReturnMsg<User>();
		String realPassword = userDao.selectByPrimaryKey(reset.getUserId()).getPassword();
		String oldPwd = Encrypt.md5(reset.getOldPassword(), Constant.PASS);
		String newPwd = Encrypt.md5(reset.getNewPassword(), Constant.PASS);
		if(realPassword.equals(newPwd)){
			returnMsg.setState(Constant.SUCCESS_STATE);
    		returnMsg.setMessage("新设置的密码与愿密码一致");
    		return returnMsg;
		}
		if(realPassword.equals(oldPwd)){
			User user = new User();
			user.setUserId(reset.getUserId());
			user.setPassword(newPwd);
			userDao.updateByPrimaryKeySelective(user);
			returnMsg.setState(Constant.SUCCESS_STATE);
    		returnMsg.setMessage("密码修改成功");
    		return returnMsg;
		}
		returnMsg.setState(Constant.SUCCESS_STATE);
		returnMsg.setMessage("输入的旧密码与原密码不匹配，修改失败");
		return returnMsg;
	}

}
