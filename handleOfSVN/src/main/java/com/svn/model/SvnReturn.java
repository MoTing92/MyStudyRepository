package com.svn.model;
/**
 * 创建SVN用户返回信息类
 * @author MoTing
 * @date 2017年7月11日
 */
public class SvnReturn {

	private String state;
	
	private String userId;
	
	private String message;
	
	private String errorMessage;
	
	private String errorDetail;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}
	
	@Override
	public String toString() {
		return "SvnReturn [state=" + state + ", userId=" + userId
				+ ", message=" + message + ", errorMessage=" + errorMessage
				+ ", errorDetail=" + errorDetail + "]";
	}
}
