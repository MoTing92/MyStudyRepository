package com.cmsz.bean;

public class OwnGroup {
    private Integer groupId;

    private Integer groupTm;

    private String groupName;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getGroupTm() {
        return groupTm;
    }

    public void setGroupTm(Integer groupTm) {
        this.groupTm = groupTm;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }
}