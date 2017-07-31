package com.svn.model;
/**
 * 权限编辑响应参数类
 * @author MoTing
 * @date 2017年7月11日
 */
public class RolesMsgResponse {

	private String id;
	
	private String authority;
	
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
