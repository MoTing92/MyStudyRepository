package com.svn.model;
/**
 * 创建SVN用户请求参数类
 * @author MoTing
 * @date 2017年7月11日
 */
public class SvnUser {

	private String username;
	
	private String password;
	
	private String fullName;
	
	private String emailAddress;

	public SvnUser(String username, String password, String fullName,
			String emailAddress) {
		super();
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.emailAddress = emailAddress;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
}