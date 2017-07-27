package com.svn;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.svn.tools.FindAllFile;

public class SvnTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
//		Example ex = new Example();
//		System.out.println("初始化/身份认证");
//		ex.beforeTest();
//		System.out.println("开始测试");
	}

	@After
	public void tearDown() throws Exception {
//		Example ex = new Example();
//		System.out.println("结束测试");
//		ex.afterTest();
//		System.out.println("关闭资源");
	}

	Example ex = new Example();
	/**
	 * 打印当前版本库路径目录
	 * @throws Exception
	 */
	@Test
	public void testGetRepo() throws Exception {
		ex.beforeTest();
		ex.testGetRepo();
		ex.afterTest();
	}
	
	/**
	 * 获取远程仓库
	 * @throws Exception
	 */
	@Test
	public void testCheckOut() throws Exception {
		ex.beforeTest();
		ex.testCheckOut();
        ex.afterTest();
	}
        
	@Test
	public void testAdd() throws Exception {
		ex.beforeTest();
		ex.testAdd();
        ex.afterTest();
	}
	
	@Test
	public void testCommit() throws Exception {
		ex.beforeTest();
		ex.testCommit();
        ex.afterTest();
	}
	
	@Test
	public void testUpdate() throws Exception {
		ex.beforeTest();
		ex.testUpdate();
        ex.afterTest();
	}
	
	@Test
	public void testDel() throws Exception {
		ex.beforeTest();
		ex.testDel();
        ex.afterTest();
	}
	
	
	@Test
	public void resultToFiles() throws Exception {
		FindAllFile s = new FindAllFile();
		String [] pathArray = {"E:\\test\\递归测试2","E:\\test\\递归测试","E:\\test\\测试文件夹"};
//		File[] files = s.resultToFiles("E:\\test\\测试文件夹");
		File[] files = s.resultToFiles(pathArray);
		for (int i = 0; i < files.length; i++) {
			System.out.println(files[i].getPath());
		}
	}
	
	@Test
	public void testCreateLocalRepos() throws Exception {
		ex.beforeTest();
		String url = ex.testCreateLocalRepos();
		System.out.println(url);
        ex.afterTest();
	}
	
	@Test
	public void createSvnUser() throws Exception {
		ex.createSvnUser();
	}
	
	@Test
	public void listRepository() throws Exception {
		ex.listRepository();
	}
	
	@Test
	public void listRoles() throws Exception {
		ex.testLogin();
		ex.listRoles();
	}
	
	@Test
	public void editRoles() throws Exception {
		ex.editRoles();
	}
	
	@Test
	public void createRemoteRepos() throws Exception {
		ex.createRemoteRepos();
	}
	
	@Test
	public void testLogin() throws Exception {
		System.out.println(ex.testLogin());
	}
	
	@Test
	public void listUser() throws Exception {
		ex.listUser();
	}
	
	@Test
	public void getTemplate() throws Exception {
		ex.getTemplate();
	}
	
	@Test
	public void putTemplate() throws Exception {
		ex.putTemplate();
	}
	
	@Test
	public void postTemplate() throws Exception {
		ex.postTemplate();
	}
	
	@Test
	public void createRemoteRepos1() throws Exception {
		ex.createRemoteRepos1();
	}
	
	@Test
	public void editRoles1() throws Exception {
		ex.editRoles1();
	}
}
