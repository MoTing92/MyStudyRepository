package com.svn.impl;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNDiffClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNStatus;
import org.tmatesoft.svn.core.wc.SVNStatusType;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import com.svn.conf.ErrorVal;
import com.svn.conf.SvnConfig;
import com.svn.impl.service.SvnServiceImpl;
import com.svn.inf.ISvn;
import com.svn.inf.service.ISvnDbLog;
import com.svn.model.RepositoryMsgRequest;
import com.svn.model.RolesUser;
import com.svn.model.SvnRepoPojo;
import com.svn.model.SvnUser;
import com.svn.tools.StringOutputSteam;

public class SvnBaseImpl extends SvnServiceImpl implements ISvn {

	public SvnBaseImpl(String account, String password, boolean logStatus,
			String repoPath) {
		super(account, password, logStatus, repoPath);
	}

	@SuppressWarnings("unchecked")
	public List<SvnRepoPojo> getRepoCatalog(String openPath) {
		try {
            if (repository == null)
                throw new Exception(ErrorVal.SVNRepository_is_null);
            Collection<SVNDirEntry> entries = repository.getDir(openPath, -1, null, (Collection<SVNDirEntry>) null);
            List<SvnRepoPojo> svns = new ArrayList<SvnRepoPojo>();
            Iterator<SVNDirEntry> it = entries.iterator();
            while (it.hasNext()) {
                SVNDirEntry entry = it.next();
                SvnRepoPojo svn = new SvnRepoPojo();
                svn.setCommitMessage(entry.getCommitMessage());
                svn.setDate(entry.getDate());
                svn.setKind(entry.getKind().toString());
                svn.setName(entry.getName());
                svn.setRepositoryRoot(entry.getRepositoryRoot().toString());
                svn.setRevision(entry.getRevision());
                svn.setSize(entry.getSize() / 1024);
                svn.setUrl(openPath.equals("") ? new StringBuffer("/").append(entry.getName()).toString() : new StringBuffer(openPath).append("/").append(entry.getName()).toString());
                svn.setAuthor(entry.getAuthor());
                svn.setState(svn.getKind() == "file" ? null : "closed");
                svns.add(svn);
            }
            super.log("获得版本库文件信息");
            return svns;
        } catch (SVNException e) {
            super.log(e);
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            super.log(e);
            e.printStackTrace();
            return null;
        }
	}

	public boolean checkOut(String checkUrl, String savePath) {
		SVNUpdateClient updateClient = clientManager.getUpdateClient();
        updateClient.setIgnoreExternals(false);
        try {
            if (savePath == null || savePath.trim().equals(""))
                throw new Exception(ErrorVal.Path_no_having);
            else if (checkUrl == null || checkUrl.trim().equals(""))
                throw new Exception(ErrorVal.Url_no_having);
            File save = new File(savePath);
            if (!save.isDirectory())
                save.mkdir();
            long version = updateClient.doCheckout(SVNURL.parseURIEncoded(checkUrl), save, SVNRevision.HEAD, SVNRevision.HEAD, SVNDepth.INFINITY, false);
            super.log("检出版本库信息,版本号为"+version);
            return true;
        } catch (SVNException e) {
            super.log(e);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

	public <T> boolean add(String[] paths, String message, boolean uLocks,ISvnDbLog<? extends T> isvnLog) {
		try {
//			File[] files = checkFilePaths(paths);
			super.log("检查文件路径信息并组装到文件容器");
			File[] files = checkFilePaths2(paths);
			super.log("文件排序");
            files = sortF_S(files);
            SVNStatus status;
            List<File> targetList = new ArrayList<File>();
            List<List<File>> fileList = bindFile(files);
            for (int i = 0; i < fileList.size(); i++) {
                for (File f : fileList.get(i)) {
                    if ((status = clientManager.getStatusClient().doStatus(f, true, true)) != null && status.getContentsStatus() != SVNStatusType.STATUS_UNVERSIONED
                            && status.getContentsStatus() != (SVNStatusType.STATUS_NONE))
                        continue;
                    else if (f.isFile()) {
                        clientManager.getWCClient().doAdd(f, true, false, true, SVNDepth.fromRecurse(true), false, false, true);
                        targetList.add(f);
                        super.log("添加文件到提交队列");
                    } else if (f.isDirectory()) {
                        // SVNDepth.empty 保证不递归文件夹下文件
                        clientManager.getWCClient().doAdd(f, false, false, false, SVNDepth.EMPTY, false, false, false);
                        targetList.add(f);
                        super.log("添加文件夹到提交队列");
                    }
                }
            }
            long versionId = commit(targetList.toArray(new File[targetList.size()]), message, uLocks);
            if (versionId == -1)
                throw new Exception(ErrorVal.Commit_error);
            if (!isvnLog.addLog(this.svnAccount, SvnConfig.add, versionId, files))
                throw new Exception(ErrorVal.AddDbLog_error);
        } catch (SVNException e) {
            super.log(e);
            e.printStackTrace();
        } catch (Exception e) {
        	super.log(e);
            e.printStackTrace();
        }
        return true;
	}

	public Long commit(File[] files, String message, boolean uLocks) {
		 try {
	            if (files.length == 0) {
	                super.log("无效的提交信息");
	                return -1l;
	            }
	            long versionId = clientManager.getCommitClient().doCommit(files, uLocks, message, null, null, false, false, SVNDepth.INFINITY).getNewRevision();
	            super.log("提交队列中预处理的操作操作  => 版本号: " + versionId);
	            return versionId;
	        } catch (SVNException e) {
	            super.log(e);
	            e.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return -1l;
	}

	public <T> boolean delete(String[] paths, boolean localDelete,
			String message, boolean uLock, ISvnDbLog<? extends T> isvnLog) {
		try {
            File[] files = checkFilePaths(paths);
            files = sortS_F(files);
            SVNStatus status = null;
            {
                List<File> targetList = new ArrayList<File>();
                List<List<File>> fileList = bindFile(files);
                StringBuffer deleteFileNames = new StringBuffer();
                for (int i = fileList.size() - 1; i >= 0; i--) {
                    for (File f : fileList.get(i)) {
                        if ((status = clientManager.getStatusClient().doStatus(f, true, true)) == null)
                            throw new Exception(ErrorVal.File_Repo_no_having);
                        else if (status.getContentsStatus() != SVNStatusType.STATUS_NORMAL)
                            throw new Exception(ErrorVal.Repo_Status_error + status.getContentsStatus().toString());
                        else {
                            clientManager.getWCClient().doDelete(f, false, localDelete, false);
                            if (f.isFile()){
                            	deleteFileNames.append(f.getName()+"、");
                            	super.log("添加文件到删除队列");
                            }
                            else{
                            	deleteFileNames.append(f.getName()+"、");
                            	super.log("添加文件夹到删除队列");
                            }
                            targetList.add(f);
                        }
                    }
                }
                boolean successUpdate = update(this.svnRepoPath, "删除"+deleteFileNames+"后先进行更新", false, new ISvnDbLog<T>() {

					@Override
					public boolean addLog(String name, SvnConfig dbType,
							long versionId, File[] files) {
						// TODO Auto-generated method stub
						return true;
					}

					@Override
					public List<? super T> getLog(String name, Date startTime,
							Date endTime) {
						// TODO Auto-generated method stub
						return null;
					}

				});
                if(!successUpdate){
                	throw new Exception(ErrorVal.Faild_update);
                }else{
                	long versionId = commit(targetList.toArray(new File[targetList.size()]), message, uLock);
                    if (versionId == -1){
                    	throw new Exception(ErrorVal.Commit_error);
                    }
                    if (!isvnLog.addLog(this.svnAccount, SvnConfig.delete, versionId, files)){
                    }
                }
            }
        } catch (SVNException e) {
            super.log(e);
            e.printStackTrace();
        } catch (Exception e) {
        	super.log(e);
            e.printStackTrace();
        }
        return true;
	}

	public <T> boolean update(String path, String message, boolean uLock,
			ISvnDbLog<? super T> isvnLog) {
		try {
            File[] files = checkFilePaths(new String[] { path });
            // diffPath(files);
            long[] l = clientManager.getUpdateClient().doUpdate(files, SVNRevision.HEAD, SVNDepth.INFINITY, true, false);
            super.log("更新文件到操作队列");
            long versionId = l[0];// commit(files, message, uLocks);
            if (versionId == -1)
                throw new Exception(ErrorVal.Update_no_change);
            if (!isvnLog.addLog(this.svnAccount, SvnConfig.update, versionId, files))
                throw new Exception(ErrorVal.AddDbLog_error);
        } catch (SVNException e) {
            super.log(e);
            e.printStackTrace();
        } catch (Exception e) {
        	super.log(e);
            e.printStackTrace();
        }
        return true;
    }

	public List<String> diffPath(File file) {
		try {
            if (file == null || !file.exists())
                throw new Exception(ErrorVal.Path_no_having);
            // 获取SVNDiffClient
            SVNDiffClient diffClient = clientManager.getDiffClient();
            diffClient.setIgnoreExternals(false);
            StringOutputSteam os = new StringOutputSteam(new ArrayList<String>());
            diffClient.doDiff(new File[] { file }, SVNRevision.HEAD, SVNRevision.BASE, null, SVNDepth.INFINITY, true, os, null);
            super.log("比对库路径");
            return os.s;
        } catch (SVNException e) {
            super.log(e);
            e.printStackTrace();
        } catch (Exception e) {
        	super.log(e);
            e.printStackTrace();
        }
        return null;
	}

	public boolean cleanUp(File file) {
		try {
            if (!file.exists())
                throw new Exception(ErrorVal.Path_no_having);
            else if (!file.isDirectory())
                throw new Exception(ErrorVal.File_is_not_directory);
            clientManager.getWCClient().doCleanup(file, false);
            super.log("清理完成");
        } catch (SVNException e) {
            super.log(e);
            e.printStackTrace();
        } catch (Exception e) {
        	super.log(e);
            e.printStackTrace();
        }

        return false;
	}

	public boolean doLock() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean unLock() {
		return false;
	}

	@Override
	public SVNCommitInfo commit(File wcPath, String commitMessage) {
		try {
			return clientManager.getCommitClient().doCommit(
				new File[] { wcPath }, false, commitMessage, null,
			null, false, false, SVNDepth.INFINITY);
		} catch (SVNException e) {
			super.log(e.getErrorMessage().toString());
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void merge(String srcURL, String dstURL, String dstPath,
			String commitMessage) throws SVNException {
		SVNDiffClient diffClient = clientManager.getDiffClient();
	    SVNURL src = null;
	    SVNURL dst = null;
		try {
			src = SVNURL.parseURIDecoded(srcURL);
			dst = SVNURL.parseURIDecoded(dstURL);
		} catch (SVNException e1) {
			e1.printStackTrace();
		}
		File file = new File(dstPath);
	    diffClient.doMerge(src, SVNRevision.HEAD, dst, SVNRevision.HEAD, file, 
        SVNDepth.UNKNOWN, true, false, true, false);
        //将merge后的文件夹commit到svn
        commit(file, commitMessage);
		
	}
	
	private static void setupLibrary() {

	       // 对于使用http://和https：//
	       DAVRepositoryFactory.setup();
	       //对于使用svn：/ /和svn+xxx：/ /
	       SVNRepositoryFactoryImpl.setup();
	       //对于使用file://
	       FSRepositoryFactory.setup();

	    }
	
	@Override
	public boolean login(String svnRoot, String userName, String password) {
		setupLibrary();
		repository = null;
		try{
			//创建库连接
			SVNRepository repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(svnRoot));
			//身份验证
			ISVNAuthenticationManager authManager = SVNWCUtil
					.createDefaultAuthenticationManager(userName,password.toCharArray());
			//创建身份验证管理器
			repository.setAuthenticationManager(authManager);
		} catch(SVNException svne){
			svne.printStackTrace();
			return false;
		}
		return true;
    }

	@Override
	public String createLocalRepos(String reposFilePath,boolean enableRevisionProperties,boolean force) {
		try {
			SVNURL tgtURL = SVNRepositoryFactory.createLocalRepository( new File( reposFilePath ), enableRevisionProperties , force );
			String url = tgtURL.toString();
			super.log("获取创建的仓库路径");
			return url;
		} catch ( SVNException svne ) {
			super.log(svne);
			svne.printStackTrace();
		}
		return null;
	}

	@Override
	public String createSvnUser(SvnUser svnUser,String httpPath,String username,String password) {
		HttpClient httpClient = new HttpClient();
		super.log("创建httpClient");
		PostMethod postMethod = new PostMethod(httpPath);
		super.log("创建postMethod");
		String responseBody = "";
		//设置http头    
        List <Header> headers = new ArrayList <Header>();  
        headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));    
        httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);    
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", svnUser.getUsername());
        jsonObject.put("password", svnUser.getPassword());
        jsonObject.put("fullName", svnUser.getFullName());
        jsonObject.put("emailAddress", svnUser.getEmailAddress());
        super.log("设置请求参数");
        try {
        	UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
        	super.log("设置凭证");
        	httpClient.getState().setCredentials(AuthScope.ANY, credentials);
			postMethod.setRequestEntity(new StringRequestEntity(jsonObject.toString(),null,"utf-8"));
			int responseState = httpClient.executeMethod(postMethod);
			postMethod.getParams().setContentCharset("utf-8");
			if(responseState == 201){
				//201表示创建成功
				super.log("创建成功");
				responseBody += postMethod.getResponseBodyAsString();
			}else{
				//400表示创建参数错误，比如重名等
				super.log("创建参数错误，比如重名等");
	            responseBody += postMethod.getResponseBodyAsString();
			}
		} catch (UnsupportedEncodingException e) {
			super.log(e);
			e.printStackTrace();
		} catch (HttpException e) {
			super.log(e);
			e.printStackTrace();
		} catch (IOException e) {
			super.log(e);
			e.printStackTrace();
		} finally {  
            // 关闭连接,释放资源    
            postMethod.releaseConnection(); 
        }  
		return responseBody;
	}

	@Override
	public String listRepository(String httpPath,String username,String password) {
		HttpClient httpclient = new HttpClient();
		super.log("创建httpclient");
		GetMethod getMethod = new GetMethod(httpPath);
		super.log("创建getMethod");
		String responseBody = "";
		try {  
            // 创建httpget.
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
            super.log("创建凭证");
            httpclient.getState().setCredentials(AuthScope.ANY, credentials);
			// 执行get请求.    
            int responseStatu = httpclient.executeMethod(getMethod); 
        	if(responseStatu == 200){
        		super.log("查询成功");
            	getMethod.getParams().setContentCharset("utf-8");
            	responseBody += getMethod.getResponseBodyAsString();
        	}
        } catch (ClientProtocolException e) { 
        	super.log(e);
            e.printStackTrace();
            super.log(e);
        } catch (ParseException e) {
        	super.log(e);
            e.printStackTrace();  
        } catch (IOException e) { 
        	super.log(e);
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            getMethod.releaseConnection(); 
        }  
		return responseBody;
	}

	@Override
	public String createRemoteRepos(RepositoryMsgRequest repositoryMsg,String httpPath,String username,String password) {
		HttpClient httpClient = new HttpClient();
		super.log("创建httpClient");
		PostMethod postMethod = new PostMethod(httpPath);
		super.log("创建postMethod");
		String responseBody = "";
		//设置http头    
        List <Header> headers = new ArrayList <Header>();  
        headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));    
        httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);    
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", repositoryMsg.getName());
        jsonObject.put("applyStandardLayout", repositoryMsg.getApplyStandardLayout());
        jsonObject.put("applyTemplateId", repositoryMsg.getApplyTemplateId());
        super.log("设置请求参数");
        try {
        	UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
        	super.log("创建凭证");
        	httpClient.getState().setCredentials(AuthScope.ANY, credentials);
			postMethod.setRequestEntity(new StringRequestEntity(jsonObject.toString(),null,"utf-8"));
			int responseState = httpClient.executeMethod(postMethod);
			postMethod.getParams().setContentCharset("utf-8");  
			if(responseState == 201){
				super.log("创建仓库成功");
	            responseBody += postMethod.getResponseBodyAsString();
			}else{
				super.log("参数异常");
	            responseBody += postMethod.getResponseBodyAsString();
			}
		} catch (UnsupportedEncodingException e) {
			super.log(e);
			e.printStackTrace();
		} catch (HttpException e) {
			super.log(e);
			e.printStackTrace();
		} catch (IOException e) {
			super.log(e);
			e.printStackTrace();
		} finally {  
            // 关闭连接,释放资源    
            postMethod.releaseConnection(); 
        }  
		return responseBody;
	}

	@Override
	public String listRoles(String httpPath,String username,String password) {
		HttpClient httpClient = new HttpClient(); 
		super.log("创建httpClient");
		GetMethod getMethod = new GetMethod(httpPath);
		super.log("创建getMethod");
		String responseBody = "";
		try {  
            // 创建httpget.
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
            super.log("创建凭证");
            httpClient.getState().setCredentials(AuthScope.ANY, credentials);
			// 执行get请求.    
            int responseStatu = httpClient.executeMethod(getMethod); 
        	if(responseStatu == 200){
        		super.log("查询成功");
            	getMethod.getParams().setContentCharset("utf-8");
            	responseBody += getMethod.getResponseBodyAsString();
        	}
        } catch (ClientProtocolException e) { 
        	super.log(e);
            e.printStackTrace();  
        } catch (ParseException e) { 
        	super.log(e);
            e.printStackTrace();  
        } catch (IOException e) {
        	super.log(e);
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            getMethod.releaseConnection(); 
        }  
		return responseBody;
	}

	@Override
	public String editRoles(RolesUser rolesUser,String httpPath,String username,String password) {
		HttpClient httpClient = new HttpClient();
		super.log("创建httpClient");
		PutMethod putMethod = new PutMethod(httpPath);
		super.log("创建putMethod");
		String responseBody = "";
		//设置http头    
        List <Header> headers = new ArrayList <Header>();  
        headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));    
        httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);    
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", rolesUser.getUserId());
        jsonObject.put("action", rolesUser.getAction());
        super.log("设置参数");
        try {
        	UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
        	super.log("创建凭证");
        	httpClient.getState().setCredentials(AuthScope.ANY, credentials);
            putMethod.setRequestEntity(new StringRequestEntity(jsonObject.toString(),null,"utf-8"));
			int responseState = httpClient.executeMethod(putMethod);
			putMethod.getParams().setContentCharset("utf-8");  
	        responseBody += putMethod.getResponseBodyAsString();
	        super.log("授权成功");
	        System.out.println(responseState);
		} catch (UnsupportedEncodingException e) {
			super.log(e);
			e.printStackTrace();
		} catch (HttpException e) {
			super.log(e);
			e.printStackTrace();
		} catch (IOException e) {
			super.log(e);
			e.printStackTrace();
		} finally {  
            // 关闭连接,释放资源    
            putMethod.releaseConnection(); 
        }  
		return responseBody;
	}

	@Override
	public Object listUser(String httpPath, String username, String password) {
		HttpClient httpClient = new HttpClient(); 
		super.log("创建httpClient");
		GetMethod getMethod = new GetMethod(httpPath);
		super.log("创建getMethod");
		Object responseBody = null;
		try {  
            // 创建httpget.
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
            super.log("创建凭证");
            httpClient.getState().setCredentials(AuthScope.ANY, credentials);
			// 执行get请求.    
            int responseStatu = httpClient.executeMethod(getMethod); 
        	if(responseStatu == 200){
        		super.log("查询成功");
            	getMethod.getParams().setContentCharset("utf-8");
            	responseBody = getMethod.getResponseBodyAsString();
        	}
        } catch (ClientProtocolException e) { 
        	super.log(e);
            e.printStackTrace();  
        } catch (ParseException e) { 
        	super.log(e);
            e.printStackTrace();  
        } catch (IOException e) {
        	super.log(e);
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            getMethod.releaseConnection(); 
        }  
		return responseBody;
	}

}
