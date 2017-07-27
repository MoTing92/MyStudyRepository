package com.svn;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.svn.conf.SvnConfig;
import com.svn.factory.DemoSvn;
import com.svn.impl.SvnBaseImpl;
import com.svn.inf.ISvn;
import com.svn.inf.service.ISvnDbLog;
import com.svn.model.RepositoryMsgRequest;
import com.svn.model.RolesUser;
import com.svn.model.SvnRepoPojo;
import com.svn.model.SvnUser;
import com.svn.tools.HttpSend;
import com.svn.conf.Constant;

public class Example {

	String account = "admin";
    String password = "admin";
    String path = "http://192.168.215.200/svn/test";
    String targetHead = "E:/test";
    ISvn svn;

    /**
     * 样例
     * 
     * @throws Exception
     * @author MoTing
     * @date 2016年8月12日
     */

    public void testCore() throws Exception {
        // 初始化实例
        DemoSvn ts = new DemoSvn(account, password, path);
        // 获得操作对象
        this.svn = ts.execute(SvnConfig.log);
        // 得到版本库信息
        svn.createSVNRepository();
        // 得到基础操作对象
        svn.createSVNClientManager();
        /** 测试--Start-- **/
        testGetRepo();
        testCheckOut();
        testAdd();
        testDel();
        testCleanUp();
        testUpdate();
        testDiff();
        /** 测试 --End-- **/
        // 关闭库容器
        svn.closeRepo();

    }
    
    public void beforeTest() throws Exception{
    	// 初始化实例
        DemoSvn ts = new DemoSvn(account, password, path);
        // 获得操作对象
        this.svn = ts.execute(SvnConfig.log);
        // 得到版本库信息
        svn.createSVNRepository();
        // 得到基础操作对象
        // 关闭库容器
        svn.createSVNClientManager();
    }
    
    public void afterTest() throws Exception{
    	svn.closeRepo();
    }

    /**
     * 获得版本路径文件信息
     * 
     * @author MoTing
     * @date 2016年8月12日
     */
    public void testGetRepo() {
        print(svn.getRepoCatalog(""));
    }

    /**
     * 检出到本地路径
     * 
     * @author MoTing
     * @date 2016年8月12日
     */

    public void testCheckOut() {
        svn.checkOut(path, targetHead);
    }

    /**
     * 添加文件到svn
     * 
     * @author MoTing
     * @date 2016年8月12日
     */

    public void testAdd() {
        String[] strs = new String[] { targetHead + "/测试文件夹1"};
        svn.add(strs, "添加测试", false, new ISvnDbLog<String>() {

			@Override
			public boolean addLog(String name, SvnConfig dbType,
					long versionId, File[] files) {
				this.getLog(name, new Date(), new Date());
				return true;
			}

			@Override
			public List<? super String> getLog(String name, Date startTime,
					Date endTime) {
				List<? super String> ss = new ArrayList<String>();
				String s = name + startTime.toString() + endTime.toString();
				ss.add(s);
				return ss;
			}

        });
    }

    /**
     * 删除文件到svn
     * 
     * @author MoTing
     * @date 2016年8月12日
     */

    public void testDel() {
        String[] strs = new String[] { targetHead + "/递归测试2/删除测试"};
        svn.delete(strs, true, "haha", false, new ISvnDbLog<String>() {

			@Override
			public boolean addLog(String name, SvnConfig dbType,
					long versionId, File[] files) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public List<? super String> getLog(String name, Date startTime,
					Date endTime) {
				// TODO Auto-generated method stub
				return null;
			}

        });
    }

    /**
     * 更新文件到svn
     * 
     * @author MoTing
     * @date 2016年8月12日
     */

    public void testUpdate() {
        String strs = targetHead;
        svn.update(strs, "哈哈", false, new ISvnDbLog<String>() {

			@Override
			public boolean addLog(String name, SvnConfig dbType,
					long versionId, File[] files) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public List<? super String> getLog(String name, Date startTime,
					Date endTime) {
				// TODO Auto-generated method stub
				return null;
			}
        });
    }

    /**
     * 测试库比对
     * 
     * @author MoTing
     * @date 2016年8月12日
     */

    public void testDiff() {
        String[] strs = new String[] { targetHead + "/4/a/b/" };
        List<String> s = svn.diffPath(new File(strs[0]));
        for (String t : s)
            System.out.println(t);
    }

    public void testCleanUp() {
        String[] strs = new String[] { targetHead + "/4/a/b/" };
        svn.cleanUp(new File(strs[0]));
    }

    /**
     * 打印当前版本库路径目录
     */
    public void print(List<SvnRepoPojo> paramList) {
        System.out.print("commitMessage ");
        System.out.print("\t\t  date \t  ");
        System.out.print("\t   kind \t  ");
        System.out.print("\t name \t  ");
        System.out.print("\t repositoryRoot \t  ");
        System.out.print("\t revision \t  ");
        System.out.print("\t size \t  ");
        System.out.print("\t url \t  ");
        System.out.print("\t author \t  ");
        System.out.println("\t state \t  ");
        Collections.sort(paramList, new Comparator<SvnRepoPojo>() {
            @Override
            public int compare(SvnRepoPojo o1, SvnRepoPojo o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        SimpleDateFormat fm = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        for (SvnRepoPojo pojo : paramList) {
            System.out.print("\t" + pojo.getCommitMessage() + "\t");
            System.out.print("\t" + fm.format(pojo.getDate()) + "\t");
            System.out.print("\t" + (pojo.getKind().equals("dir")?"文件夹":pojo.getKind().equals("file")?"文件":pojo.getKind().equals("none")?"空":"未知") + "\t");
            System.out.print("\t" + pojo.getName() + "\t");
            System.out.print("\t" + pojo.getRepositoryRoot() + "\t");
            System.out.print("\t" + pojo.getRevision() + "\t");
            System.out.print("\t" + pojo.getSize() + "\t");
            System.out.print("\t" + pojo.getUrl() + "\t");
            System.out.print("\t" + pojo.getAuthor() + "\t");
            System.out.print("\t" + pojo.getState() + "\t");
            System.out.print("\r\n");
        }
    }
    
    public void testCommit(){
    	String [] path = {"E:/test/递归测试2/删除测试","E:/test/递归测试2/删除测试/新建文本文档"};
    	File [] files = new File[path.length];
    	for (int i = 0; i < path.length; i++) {
			File file = new File(path[i]);
    		files[i] = file;
		}
    	svn.commit(files, "提交测试", false);
    }

    public String testCreateLocalRepos(){
    	String path = "E:/testRepos";
    	return svn.createLocalRepos(path, true, false);
    }
    
    public void createSvnUser() throws Exception {
		SvnUser svnUser = new SvnUser("liyanjun1","liyanjun", "李焱军", "123@qq.com");
		String httpPath = Constant.HTTP_OF_CREATE_SVN_USER;
		SvnBaseImpl svn1 = new SvnBaseImpl("admin", "admin", false, null);
		String message = svn1.createSvnUser(svnUser,httpPath,"admin", "admin");
		System.out.println(message+"bbb");
		
	}
    
    public void listRepository() throws Exception {
		String httpPath = Constant.HTTP_OF_REMOTE_REPOSITORY;
		SvnBaseImpl svn1 = new SvnBaseImpl("admin", "admin", false, null);
		String message = svn1.listRepository(httpPath,"admin", "admin");
		System.out.println(message);
		
	}
    
    public void listRoles() throws Exception {
		String httpPath = Constant.HTTP_OF_SVN_ROLE;
		SvnBaseImpl svn1 = new SvnBaseImpl("admin", "admin", false, null);
		String message = svn1.listRoles(httpPath,"admin", "admin");
		System.out.println(message);
		
	}
    
    public void createRemoteRepos() throws Exception {
    	RepositoryMsgRequest repositoryMsg = new RepositoryMsgRequest("test2", "true", 2);
		String httpPath = Constant.HTTP_OF_REMOTE_REPOSITORY;
		SvnBaseImpl svn1 = new SvnBaseImpl("admin", "admin", false, null);
		String message = svn1.createRemoteRepos(repositoryMsg, httpPath,"admin", "admin");
		System.out.println(message);
	}
    
    public void editRoles() throws Exception {
    	RolesUser rolesUser = new RolesUser(3,"remove");
		String httpPath = Constant.HTTP_OF_SVN_ROLE;
		SvnBaseImpl svn1 = new SvnBaseImpl("admin", "admin", false, null);
		String message = svn1.editRoles(rolesUser, httpPath,"admin", "admin");
		System.out.println(message);
	}
    
    public boolean testLogin(){
    	SvnBaseImpl svn1 = new SvnBaseImpl("admin", "admin", false, null);
    	return svn1.login(path, account, "123");
    }
    
    public void listUser() throws Exception {
		String httpPath = Constant.HTTP_OF_LIST_USER;
		String index = "http://192.168.215.200:3343/csvn/user/index";
		SvnBaseImpl svn1 = new SvnBaseImpl("admin", "admin", false, null);
		Object message1 = svn1.listUser(index,"admin", "admin");
		System.err.println(message1);
		Object message = svn1.listUser(httpPath,"admin", "admin");
		System.out.println(message);
		
	}
    
    public void getTemplate(){
    	HttpSend httpSend = new HttpSend();
    	String httpUrl = Constant.HTTP_OF_GET_TEMPLATE;
		Object message = httpSend.sendGetRequest(httpUrl , "admin", "admin");
		System.out.println(message);
    }
    
    public void putTemplate(){
    	HttpSend httpSend = new HttpSend();
    	String httpUrl = Constant.HTTP_OF_PUT_TEMPLATE;
		Object message = httpSend.sendPutRequest(httpUrl , "admin", "admin");
		System.out.println(message);
    }
    
    public void postTemplate(){
    	HttpSend httpSend = new HttpSend();
    	String httpUrl = Constant.HTTP_OF_POST_TEMPLATE;
		Object message = httpSend.sendPostRequest(httpUrl , "admin", "admin");
		System.out.println(message);
    }
    
    public void createRemoteRepos1(){
    	HttpSend httpSend = new HttpSend();
    	String httpUrl = Constant.HTTP_OF_REMOTE_REPOSITORY;
		Object message = httpSend.sendPostRequest(httpUrl , "admin", "admin","name:aaa","applyStandardLayout:true","applyTemplateId:2");
		System.out.println(message);
    }
    
    public void editRoles1(){
    	HttpSend httpSend = new HttpSend();
    	String httpUrl = "http://192.168.215.200:3343/csvn/api/1/role/5?format=json";
		Object message = httpSend.sendPutRequest(httpUrl , "admin", "admin","userId:3","action:add");
		System.out.println(message);
    }
    
    public static void main(String[] args) throws Exception {
         new Example().testCore(); 
    }
}
