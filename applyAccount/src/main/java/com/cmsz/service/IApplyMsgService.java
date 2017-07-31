package com.cmsz.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
<<<<<<< HEAD

import com.cmsz.bean.ApplyMsg;
import com.cmsz.bean.OwnGroup;
import com.cmsz.bean.PathBean;
=======
import javax.servlet.http.HttpServletResponse;

import com.cmsz.bean.ApplyMsg;
import com.cmsz.bean.OwnGroup;
>>>>>>> myDevelop
import com.cmsz.bean.RequestMsg;
import com.cmsz.bean.ReturnMsg;
import com.github.pagehelper.PageInfo;

public interface IApplyMsgService {

	PageInfo<ApplyMsg> query(RequestMsg requestMsg);

	boolean add(ApplyMsg applyMsg, HttpServletRequest request);

	ApplyMsg look(Integer id);

	List<OwnGroup> getGroup();

	ReturnMsg<ApplyMsg> deal(RequestMsg requestMsg,HttpServletRequest request);

<<<<<<< HEAD
	boolean exportApply(PathBean path);
=======
	void exportApply(int id,HttpServletResponse response);
>>>>>>> myDevelop

}
