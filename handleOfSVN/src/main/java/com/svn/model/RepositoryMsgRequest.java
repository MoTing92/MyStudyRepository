package com.svn.model;
/**
 * 创建版本库请求参数类
 * @author MoTing
 * @date 2017年7月11日
 */
public class RepositoryMsgRequest {
	//版本库名称
	private String name;
	//是否创建标准版本库：“true”/“false”
	private String applyStandardLayout;
	//
	private Integer applyTemplateId;

	public RepositoryMsgRequest(String name, String applyStandardLayout,
			Integer applyTemplateId) {
		super();
		this.name = name;
		this.applyStandardLayout = applyStandardLayout;
		this.applyTemplateId = applyTemplateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApplyStandardLayout() {
		return applyStandardLayout;
	}

	public void setApplyStandardLayout(String applyStandardLayout) {
		this.applyStandardLayout = applyStandardLayout;
	}

	public Integer getApplyTemplateId() {
		return applyTemplateId;
	}

	public void setApplyTemplateId(Integer applyTemplateId) {
		this.applyTemplateId = applyTemplateId;
	}
}
