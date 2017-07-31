package com.cmsz.bean;

import java.util.Date;
import java.util.List;

public class User {
    private Integer userId;

    private String username;

    private String password;

    private String realName;

    private String phoneNumber;

    private String email;

    private String fourA;

    private String workDuty;

    private String workCom;

    private Integer level;

    private Date expireTime;

    private Date registerTime;
    
    private String expireTimeStr;

    private String registerTimeStr;

    private Integer groupId;
    
    private String groupName;
    
    //配置用户和角色的一对多关系
    private List<Integer> roleIds;
    
    //一个用户对应多个角色名
//  private List<String> roleName;
    private String roleName; 

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
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

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

	public List<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getExpireTimeStr() {
		return expireTimeStr;
	}

	public void setExpireTimeStr(String expireTimeStr) {
		this.expireTimeStr = expireTimeStr;
	}

	public String getRegisterTimeStr() {
		return registerTimeStr;
	}

	public void setRegisterTimeStr(String registerTimeStr) {
		this.registerTimeStr = registerTimeStr;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

<<<<<<< HEAD
=======
	public User(Integer userId, String username, String realName,Date expireTime) {
		super();
		this.userId = userId;
		this.username = username;
		this.realName = realName;
		this.expireTime = expireTime;
	}
	
	public User() {
		super();
	}

>>>>>>> myDevelop
}