package com.cmsz.bean;

public class ReturnApplyMsg {

	//返回状态
	private String state;
	
	//返回信息
	private String message;
	//保存查询结果的集合
	private ApplyMsg data;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ApplyMsg getData() {
		return data;
	}
	public void setData(ApplyMsg data) {
		this.data = data;
	}
}
