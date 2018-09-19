package com.moting.applyaccount.bean;

import java.util.List;

public class Role {
	
    private Integer roleId;

    private String roleName;
    
	private List<ReturnMenu> rolePower;
    
    //配置角色权限
    private List<Integer> menuId;

    //配置角色权限
	private List<Menu> menuList;
    
	public List<ReturnMenu> getRolePower() {
		return rolePower;
	}

	public void setRolePower(List<ReturnMenu> rolePower) {
		this.rolePower = rolePower;
	}

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
        this.roleName = roleName == null ? null : roleName.trim();
    }

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public List<Integer> getMenuId() {
		return menuId;
	}

	public void setMenuId(List<Integer> menuId) {
		this.menuId = menuId;
	}

}
