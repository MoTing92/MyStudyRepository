package com.moting.applyaccount.bean;

public class Menu {
    private Integer menuid;

    private String menuName;

    private String menuPower;

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuPower() {
        return menuPower;
    }

    public void setMenuPower(String menuPower) {
        this.menuPower = menuPower == null ? null : menuPower.trim();
    }
}