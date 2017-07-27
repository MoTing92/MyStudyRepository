package com.svn.model;
/**
 * 闯进啊版本库相应参数类
 * @author MoTing
 * @date 2017年7月11日
 */
public class RepositoryMsgResponse {

	private int id;
	
	private String name;
	
	private String status;
	
	private String svnUrl;
	
	private String viewvcUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSvnUrl() {
		return svnUrl;
	}

	public void setSvnUrl(String svnUrl) {
		this.svnUrl = svnUrl;
	}

	public String getViewvcUrl() {
		return viewvcUrl;
	}

	public void setViewvcUrl(String viewvcUrl) {
		this.viewvcUrl = viewvcUrl;
	}

	@Override
	public String toString() {
		return "RepositoryMsgResponse [id=" + id + ", name=" + name
				+ ", status=" + status + ", svnUrl=" + svnUrl + ", viewvcUrl="
				+ viewvcUrl + "]";
	}
	
	
}
