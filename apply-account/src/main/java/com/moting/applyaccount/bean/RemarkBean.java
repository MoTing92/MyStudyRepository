package com.moting.applyaccount.bean;

public class RemarkBean {

	private String realName;
	
	private String remark;

	private String date;
	
	public RemarkBean() {
		super();
	}
	
	public RemarkBean(String realName, String remark, String date) {
		super();
		this.realName = realName;
		this.remark = remark;
		this.date = date;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
