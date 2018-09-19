package com.moting.applyaccount.bean;

public class PageMsg {

	//分页查询的当前页
	private Integer currentPage = 1;
	//每页显示的条数
	private Integer pageSize = 3;
	//查询记录的总条数
	private long totalCount;
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
}
