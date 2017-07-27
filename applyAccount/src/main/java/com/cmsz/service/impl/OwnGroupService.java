package com.cmsz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cmsz.bean.OwnGroup;
import com.cmsz.bean.User;
import com.cmsz.dao.OwnGroupDao;
import com.cmsz.dao.UserDao;
import com.cmsz.service.IOwnGroupService;

@Service
public class OwnGroupService implements IOwnGroupService {

	@Resource
	private OwnGroupDao groupDao;
	
	@Resource
	private UserDao userDao;
	
	/**
	 * 新增小组
	 * */
	@Override
	public boolean add(OwnGroup ownGroup) {
		//通过手机号查询用户
		User user = userDao.queryByPhone(ownGroup.getGroupPhone());
		//小组GroupTeam存放userId
		ownGroup.setGroupTeam(user.getUserId());
		//新增
		groupDao.insertSelective(ownGroup);
		if(ownGroup.getGroupId() != null){
			return true;
		}
		return false;
	}

	/***
	 * 修改小组
	 * ***/
	@Override
	public boolean update(OwnGroup ownGroup) {
		//通过手机号查询用户
		User user = userDao.queryByPhone(ownGroup.getGroupPhone());
		//小组GroupTeam存放userId
		ownGroup.setGroupTeam(user.getUserId());
		//修改小组
		groupDao.updateByPrimaryKeySelective(ownGroup);
		return true;
	}

	/**
	 * 查询所有
	 * **/
	@Override
	public List<OwnGroup> query() {
		List<OwnGroup> list = groupDao.query();
		for (OwnGroup ownGroup : list) {
			//通过手机号查询用户
			User user = userDao.queryByPhone(ownGroup.getGroupPhone());
			//小组GroupTeam存放userId
			ownGroup.setGroupTeamName(user.getRealName());
			ownGroup.setGroupPhone(user.getPhoneNumber());
		}
		return list;
	}

}
