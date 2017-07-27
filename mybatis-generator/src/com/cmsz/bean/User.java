package com.cmsz.bean;

public class User extends UserKey {
    private String password;

    private String realName;

    private String email;

    private String fourA;

    private String workDuty;

    private String workCom;

    private Integer level;

    private Integer groupId;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getFourA() {
        return fourA;
    }

    public void setFourA(String fourA) {
        this.fourA = fourA == null ? null : fourA.trim();
    }

    public String getWorkDuty() {
        return workDuty;
    }

    public void setWorkDuty(String workDuty) {
        this.workDuty = workDuty == null ? null : workDuty.trim();
    }

    public String getWorkCom() {
        return workCom;
    }

    public void setWorkCom(String workCom) {
        this.workCom = workCom == null ? null : workCom.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}