package com.cmsz.bean;

import java.util.Date;

public class ApplyMsg {
    private Integer id;

    private String phoneNum;

    private String accountName;

    private String mainPath;

    private Integer accountProperties;

    private Date startTime;

    private Date endTime;

    private String subSystem;

    private String platform;

    private String applyReason;

    private Integer handlePerson;

    private String workNeed;

    private Date applyTime;

    private String powerdetail;

    private Integer applyStatus;

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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getMainPath() {
        return mainPath;
    }

    public void setMainPath(String mainPath) {
        this.mainPath = mainPath == null ? null : mainPath.trim();
    }

    public Integer getAccountProperties() {
        return accountProperties;
    }

    public void setAccountProperties(Integer accountProperties) {
        this.accountProperties = accountProperties;
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

    public String getSubSystem() {
        return subSystem;
    }

    public void setSubSystem(String subSystem) {
        this.subSystem = subSystem == null ? null : subSystem.trim();
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason == null ? null : applyReason.trim();
    }

    public Integer getHandlePerson() {
        return handlePerson;
    }

    public void setHandlePerson(Integer handlePerson) {
        this.handlePerson = handlePerson;
    }

    public String getWorkNeed() {
        return workNeed;
    }

    public void setWorkNeed(String workNeed) {
        this.workNeed = workNeed == null ? null : workNeed.trim();
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getPowerdetail() {
        return powerdetail;
    }

    public void setPowerdetail(String powerdetail) {
        this.powerdetail = powerdetail == null ? null : powerdetail.trim();
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }
}