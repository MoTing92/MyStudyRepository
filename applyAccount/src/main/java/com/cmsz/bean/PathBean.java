package com.cmsz.bean;

public class PathBean {
	
	private String exportPath;
	
	private int id;

	public String getExportPath() {
		return exportPath;
	}

	public void setExportPath(String exportPath) {
		this.exportPath = exportPath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PathBean(String exportPath, int id) {
		super();
		this.exportPath = exportPath;
		this.id = id;
	}

	public PathBean() {
		super();
	}
	
}
