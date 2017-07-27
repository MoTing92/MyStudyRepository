package com.cmsz.bean;

import java.util.List;

public class  ReturnMsg<T> extends PageMsg{

	//返回状态
	private String state;
	//返回信息
	private String message;
	//保存查询结果的集合
	private List<T> data;

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

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
