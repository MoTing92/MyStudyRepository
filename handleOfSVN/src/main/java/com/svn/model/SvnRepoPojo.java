package com.svn.model;

import java.io.Serializable;
import java.util.Date;
/**
 * SVN资源库对象
 * commitMessage 提交信息 
 * date 提交日期
 * kind 提交方式 dir目录 file文件 none空 unknown 未知
 * name 目录名
 * repositoryRoot 资源库路径
 * revision 提交的svn版本号
 * size 提交的文件数
 * url 更变的目录地址
 * author 作者
 * state 状态
 * @author MoTing
 * @date 2017年6月30日
 */
public class SvnRepoPojo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String commitMessage; // 提交信息
    private Date date; // 提交日期
    private String kind; // 提交方式 dir目录 file文件 none空 unknown 未知
    private String name;// 目录名
    private String repositoryRoot; // 资源库路径
    private long revision; // 提交的svn版本号
    private long size; // 提交的文件数
    private String url; // 更变的目录地址
    private String author;// 作者
    private String state;// 状态
    
	public String getCommitMessage() {
		return commitMessage;
	}
	public void setCommitMessage(String commitMessage) {
		this.commitMessage = commitMessage;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRepositoryRoot() {
		return repositoryRoot;
	}
	public void setRepositoryRoot(String repositoryRoot) {
		this.repositoryRoot = repositoryRoot;
	}
	public long getRevision() {
		return revision;
	}
	public void setRevision(long revision) {
		this.revision = revision;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
