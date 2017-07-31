package com.svn.model;
/**
 * Svn链接状态信息
 * @author MoTing
 * @date 2017年6月30日
 */
public class SvnLinkPojo extends SvnAccountPojo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String repoPath;// 库链接路径

	public SvnLinkPojo() {
		super();
	}

	public SvnLinkPojo(String repoPath) {
		super();
		this.setRepoPath(repoPath);
	}

	public SvnLinkPojo(String repoPath, String svnAccount, String svnPassword) {
		super.setSvnAccount(svnAccount);
		super.setSvnPassword(svnPassword);
		this.setRepoPath(repoPath);
	}

	public String getRepoPath() {
		return repoPath;
	}

	public void setRepoPath(String repoPath) {
		this.repoPath = repoPath;
	}


}
