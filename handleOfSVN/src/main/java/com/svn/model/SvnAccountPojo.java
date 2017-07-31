package com.svn.model;

import java.io.Serializable;
/**
 * svn账户信息类
 * @author MoTing
 * @date 2017年6月30日
 */
public class SvnAccountPojo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String svnAccount;// svn账号
	private String svnPassword;// svn密码
	
	public SvnAccountPojo() {
		super();
	}

	public SvnAccountPojo(String svnAccount, String svnPassword) {
		super();
		this.svnAccount = svnAccount;
		this.svnPassword = svnPassword;
	}

	public String getSvnAccount() {
		return svnAccount;
	}

	public void setSvnAccount(String svnAccount) {
		this.svnAccount = svnAccount;
	}

	public String getSvnPassword() {
		return svnPassword;
	}

	public void setSvnPassword(String svnPassword) {
		this.svnPassword = svnPassword;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
