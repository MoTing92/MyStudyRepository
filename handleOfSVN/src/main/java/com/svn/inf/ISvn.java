package com.svn.inf;

import java.io.File;
import java.util.List;

import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNException;

import com.svn.inf.service.ISvnDbLog;
import com.svn.inf.service.ISvnService;
import com.svn.model.RepositoryMsgRequest;
import com.svn.model.RolesUser;
import com.svn.model.SvnRepoPojo;
import com.svn.model.SvnUser;

/**
 * svn操作大全
 * @author MoTing
 * @date 2017年6月30日
 */
public interface ISvn extends ISvnService{

	/**
     * 获取目标路径下版本库数据信息
     * 
     * @param openPath
     *            需要查看的版本库路径
     * @return 版本库列表 {@link SvnRepoPojo}
     * @author MoTing
     * @date 2017年6月30日
     */
    public List<SvnRepoPojo> getRepoCatalog(String openPath);

    /**
     * 检出到目录
     * 
     * @param checkUrl
     *            检出目标URL
     * @param savePath
     *            检出到本地路径
     * @return true|false
     * @author MoTing
     * @date 2017年6月30日
     */
    public boolean checkOut(String checkUrl, String savePath);

    /**
     * 添加到本地版本库并提交远程库
     * 
     * @see 先添加文件夹再添加文件
     * @param paths
     *            提交文件路径
     * @param message
     *            提交信息
     * @param uLocks
     *            是否解锁
     * @param isvnLog
     *            数据持久化接口 {@link ISvnDbLog}
     * @return trun|false
     * @author MoTing
     * @date 2017年6月30日
     */
    public <T> boolean add(String[] paths, String message, boolean uLocks, ISvnDbLog<? extends T> isvnLog);

    /**
     * 提交到版本库(所有写操作已在内部调用过COMMIT,自行调用则需要手动同步到DbLog)
     * 
     * @param files
     *            提交的文件路径
     * @param message
     *            提交信息
     * @param uLocks
     *            是否解锁
     * @return 返回提交后版本号-1为提交失败
     * @author MoTing
     * @date 2017年6月30日
     */
    public Long commit(File[] files, String message, boolean uLocks);

    /**
     * 删除到版本库
     * 
     * @see 先删除文件再删除文件夹
     * @param paths
     *            提交文件路径
     * @param localDelete
     *            <ul>
     *            <li>如果是true则在本地也删除此文件,false则只删除版本库中的此文件</li>
     *            <li>删除实体文件时要注意</li>
     *            <li>删除文件夹时其目录下所有内容都要提交到<b>参数paths</b>中,否则无法删除实体文件</li>
     *            </ul>
     * @param message
     *            提交内容解释
     * @param uLock
     *            是否解锁
     * @param isvnLog
     *            数据持久化接口 {@link ISvnDbLog}
     * @return true|false
     * @author MoTing
     * @date 2017年6月30日
     */
    public <T> boolean delete(String[] paths, boolean localDelete, String message, boolean uLock, ISvnDbLog<? extends T> isvnLog);

    /**
     * 更新到版本库
     * 
     * @param path
     *            要更新的文件目录
     * @param message
     *            提交内容解释
     * @param uLock
     *            是否解锁
     * @param isvnLog
     *            数据持久化接口 {@link ISvnDbLog}
     * @return true|false
     * @author MoTing
     * @date 2017年6月30日
     */
    public <T> boolean update(String path, String message, boolean uLock, ISvnDbLog<? super T> isvnLog);

    /**
     * 比对目录下内容信息
     * 
     * @see 返回delete,update文件列表
     * @param file
     *            待比对的目标文件路径
     * @return 返回有差异的文件路径否则为null
     * @author MoTing
     * @date 2017年6月30日
     */
    public List<String> diffPath(File file);

    /**
     * 清理目录
     * 
     * @param file
     *            待清理目录
     * @return true|false
     * @author MoTing
     * @date 2017年6月30日
     */
    public boolean cleanUp(File file);
    /**
     * commit文件
     * @param wcPath 文件路径
     * @param commitMessage 提交的提示信息
     * @return
     */
    public SVNCommitInfo commit(File wcPath,String commitMessage);
    /**
	* 将srcURL和dstURL中的文件进行merge，放入dstPath中并提交到svn。
	* @param srcURL 
	* @param dstURL 
	* @param dstPath 
	* @param commitMessage 
	* @throws SVNException 
	*/
	public void merge(String srcURL,String dstURL,String dstPath,String commitMessage) throws SVNException;

    public boolean doLock();

    public boolean unLock();
    /**
     * 登录SVN服务器
     * @param svnRoot 服务器根目录
     * @param userName 用户名
     * @param password 密码
     * @return 登录成功与否
     */
    public boolean login(String svnRoot,String userName,String password);
    /**
     * 创建一个SVN本地仓库
     * @param reposFilePath 本地仓库地址
     * @param enableRevisionProperties 
     * @param force
     * @return
     * 		仓库地址（格式为：file:///E:/testRepos。。。。）
     */
    public String createLocalRepos(String reposFilePath,boolean enableRevisionProperties,boolean force);
    /**
     * 创建SVN用户
     * @param svnUser 新用户信息对象
     * 			username 用户名
     *  		password 密码
     * 			fullName 全名 
     *  		emailAddress 邮箱
     * @param httpPath 请求地址
     * @param username 管理员用户名
     * @param password 管理员密码
     * @return
     * 		返回新用户的id
     * 		提示信息
     */
    public String createSvnUser(SvnUser svnUser,String httpPath,String username,String password);
    /**
     * 查看版本库
     * @param httpPath 请求地址
     * @param username 管理员用户名
     * @param password 管理员密码
     * @return
     * 		返回版本库json数组
     */
    public String listRepository(String httpPath,String username,String password);
    /**
     * 创建版本库
     * @param repositoryMsg 创建版本库信息
     * 			name 版本库库
     * 			applyStandardLayout 是否创建一个标准版本库
     * 			applyTemplateId 
     * @param httpPath 请求地址
     * @param username 管理员用户名
     * @param password 管理员密码
     * @return
     * 		返回创建状态信息
     */
    public String createRemoteRepos(RepositoryMsgRequest repositoryMsg,String httpPath,String username,String password);
    /**
     * 列出所有角色信息
     * @param httpPath 请求地址
     * @param username 管理员用户名
     * @param password 管理员密码
     * @return
     * 		所有角色信息的json数组
     */
    public String listRoles(String httpPath,String username,String password);
    /**
     * 给指定用户添加角色
     * @param rolesUser 编辑角色需要参数对象
     * 			userId 需要编辑的用户id
     * 			action 操作：add为添加；remove为删除
     * @param httpPath 请求地址（地址中带有角色id）
     * @param username 管理员用户名
     * @param password 管理员密码
     * @return
     * 		编辑结果提示信息
     */
    public String editRoles(RolesUser rolesUser,String httpPath,String username,String password);
    /**
     * 查看SVN用户信息列表
     * @param httpPath 请求地址
     * @param username 管理员用户名
     * @param password 管理员密码
     * @return
     */
    public Object listUser(String httpPath,String username,String password);
}
