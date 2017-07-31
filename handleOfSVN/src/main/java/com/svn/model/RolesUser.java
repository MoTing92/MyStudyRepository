package com.svn.model;
/**
 * 权限编辑请求参数类
 * @author MoTing
 * @date 2017年7月11日
 */
public class RolesUser {

	private Integer userId;
	
	private String action;

	public RolesUser(Integer userId, String action) {
		super();
		this.userId = userId;
		this.action = action;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
