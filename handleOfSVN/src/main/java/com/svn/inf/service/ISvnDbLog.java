package com.svn.inf.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.svn.conf.SvnConfig;

/**
 * 记录SVN操作人
 * @author MoTing
 * @date 2017年6月30日
 * @param <T>
 */
public interface ISvnDbLog<T> {

	/**
     * 添加日志
     * 
     * @param name
     *            操作人账号
     * @param dbType
     *            数据类型{@link SvnConfig} 
     * @param versionId
     *            版本号
     * @param files
     *            操作的文件组
     * @return true|false
     * @author MoTing
     * @date 2017年6月30日
     */
    public boolean addLog(String name, SvnConfig dbType, long versionId, File[] files);

    /**
     * 获取日志
     * 
     * @param name
     *            操作人账号
     * @param startTime
     *            日志开始时间
     * @param endTime
     *            日志结束时间
     * @return T 类型列表
     * @author MoTing
     * @date 2017年6月30日
     */
    public List<? super T> getLog(String name, Date startTime, Date endTime);

}
