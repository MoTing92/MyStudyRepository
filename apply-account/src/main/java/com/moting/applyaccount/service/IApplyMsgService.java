package com.moting.applyaccount.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.moting.applyaccount.bean.ApplyMsg;
import com.moting.applyaccount.bean.OwnGroup;
import com.moting.applyaccount.bean.RequestMsg;
import com.moting.applyaccount.bean.ReturnMsg;

public interface IApplyMsgService {

	PageInfo<ApplyMsg> query(RequestMsg requestMsg);

	boolean add(ApplyMsg applyMsg, HttpServletRequest request);

	ApplyMsg look(Integer id);

	List<OwnGroup> getGroup();

	ReturnMsg<ApplyMsg> deal(RequestMsg requestMsg,HttpServletRequest request);

	void exportApply(int id,HttpServletResponse response);

}
