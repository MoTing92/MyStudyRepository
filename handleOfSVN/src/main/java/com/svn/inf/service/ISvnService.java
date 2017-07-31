package com.svn.inf.service;
	/**
	 * svn主服务创建
	 * @author MoTing
	 * @date 2017年6月30日
	 */
	public interface ISvnService {

	 /**
     * 创建SNV版本库服务
     * @author MoTing
     * @date 2017年6月30日
     */
    public void createSVNRepository();

    /**
     * 关闭版本库容器,便于刷新重连等
     * @author MoTing
     * @date 2017年6月30日
     */
    public void closeRepo();

    /**
     * 创建svn客户操作服务
     * @author MoTing
     * @date 2017年6月30日
     */
    public void createSVNClientManager();
}
