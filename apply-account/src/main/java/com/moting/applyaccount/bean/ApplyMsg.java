package com.moting.applyaccount.bean;

import java.util.Date;
//每一条申请对应一个申请人信息
public class ApplyMsg extends User{
	
	private Integer id;

    private Integer userId;

    private String phoneNumber;

    private String accountName;

    private String mainPath;

    private Integer accountProperties;
    
    private String accountPropertiesName;

    private Date startTime;
    //时间类型转化传值的中间变量
    private String startTimeStr;

    private Date endTime;
    
    //时间类型转化传值的中间变量
    private String endTimeStr;

    private String subSystem;

    private String platform;

    private String applyReason;

    private Integer handlePerson;
    
    private String handlePersonName;

    private String workDuty;
    
    private Date applyTime;
    
    //时间类型转化传值的中间变量
    private String applyTimeStr;
    
    private String powerDetail;

    private Integer applyStatus;
    
    //审核状态	1：审核中    2：已通过     3：未通过
    private String applyStatusStr;
    
    private String remark;
    
    public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public String getApplyTimeStr() {
		return applyTimeStr;
	}

	public void setApplyTimeStr(String applyTimeStr) {
		this.applyTimeStr = applyTimeStr;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getWorkDuty() {
		return workDuty;
	}

	public void setWorkDuty(String workDuty) {
		this.workDuty = workDuty;
	}

	public String getPowerDetail() {
		return powerDetail;
	}

	public void setPowerDetail(String powerDetail) {
		this.powerDetail = powerDetail;
	}

	public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getApplyStatusStr() {
		return applyStatusStr;
	}

	public void setApplyStatusStr(String applyStatusStr) {
		this.applyStatusStr = applyStatusStr;
	}

	public String getHandlePersonName() {
		return handlePersonName;
	}

	public void setHandlePersonName(String handlePersonName) {
		this.handlePersonName = handlePersonName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAccountPropertiesName() {
		return accountPropertiesName;
	}

	public void setAccountPropertiesName(String accountPropertiesName) {
		this.accountPropertiesName = accountPropertiesName;
	}
}