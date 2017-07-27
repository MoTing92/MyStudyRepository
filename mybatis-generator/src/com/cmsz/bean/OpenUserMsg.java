package com.cmsz.bean;

import java.util.Date;

public class OpenUserMsg {
    private Integer id;

    private String phoneNum;

    private String username;

    private Integer groupId;

    private String mainpath;

    private Integer numberProperty;

    private Date startTime;

    private Date endTime;

    private String ownSystem;

    private String equipmentStage;

    private String openReason;

    private Integer statue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getMainpath() {
        return mainpath;
    }

    public void setMainpath(String mainpath) {
        this.mainpath = mainpath == null ? null : mainpath.trim();
    }

    public Integer getNumberProperty() {
        return numberProperty;
    }

    public void setNumberProperty(Integer numberProperty) {
        this.numberProperty = numberProperty;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getOwnSystem() {
        return ownSystem;
    }

    public void setOwnSystem(String ownSystem) {
        this.ownSystem = ownSystem == null ? null : ownSystem.trim();
    }

    public String getEquipmentStage() {
        return equipmentStage;
    }

    public void setEquipmentStage(String equipmentStage) {
        this.equipmentStage = equipmentStage == null ? null : equipmentStage.trim();
    }

    public String getOpenReason() {
        return openReason;
    }

    public void setOpenReason(String openReason) {
        this.openReason = openReason == null ? null : openReason.trim();
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }
}