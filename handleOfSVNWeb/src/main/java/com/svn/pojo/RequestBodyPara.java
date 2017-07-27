package com.svn.pojo;

public class RequestBodyPara extends AdministratorsAuth {

	private String httpPath;
	
	private String reposPath;
	
	private String savePath;

	public String getHttpPath() {
		return httpPath;
	}

	public void setHttpPath(String httpPath) {
		this.httpPath = httpPath;
	}

	public String getReposPath() {
		return reposPath;
	}

	public void setReposPath(String reposPath) {
		this.reposPath = reposPath;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
}
