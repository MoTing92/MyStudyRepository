package com.moting.applyaccount.bean;

public class OwnGroup {
    private Integer groupId;

    private String groupName;

    private Integer groupTeam;
    
	private String groupPhone;
	
	private String groupTeamName;

    public String getGroupPhone() {
		return groupPhone;
	}

	public void setGroupPhone(String groupPhone) {
		this.groupPhone = groupPhone;
	}

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

	public String getGroupTeamName() {
		return groupTeamName;
	}

	public void setGroupTeamName(String groupTeamName) {
		this.groupTeamName = groupTeamName;
	}
}