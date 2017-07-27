package com.cmsz.bean;

import java.util.List;

public class ReturnRole {

	private Integer roleId;

    private String roleName;
    
    private List<ReturnMenu> rolePower;
    
    public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<ReturnMenu> getRolePower() {
		return rolePower;
	}

	public void setRolePower(List<ReturnMenu> rolePower) {
		this.rolePower = rolePower;
	}

}