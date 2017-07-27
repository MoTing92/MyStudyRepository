package com.cmsz.bean;

public class OwnGroup {
    private Integer groupId;

    private String groupName;

    private Integer groupTeam;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Integer getGroupTeam() {
        return groupTeam;
    }

    public void setGroupTeam(Integer groupTeam) {
        this.groupTeam = groupTeam;
    }
}