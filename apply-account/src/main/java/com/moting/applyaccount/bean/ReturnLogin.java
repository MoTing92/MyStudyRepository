package com.moting.applyaccount.bean;

import java.util.List;

public class ReturnLogin {
	
	
	private String userId;
	
	private List<String> powerList;
	
	private Boolean isAdmin;
	
	private UserData data;
	
	private String state;
	
	private String message;
	
	public UserData getData() {
		return data;
	}
	public void setData(UserData data) {
		this.data = data;
	}
	
	public List<String> getPowerList() {
		return powerList;
	}
	public void setPowerList(List<String> powerList) {
		this.powerList = powerList;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

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
	
}
