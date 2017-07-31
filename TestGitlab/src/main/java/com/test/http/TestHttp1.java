package com.test.http;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import com.cmsz.utils.Utils;

public class TestHttp1  {
	
	//说明运行下面的方法需要将ip地址和private_token切换为Gitlab服务器所在的服务器地址和用户自己的private_token才可得到想要结果
	//项目的增删改查
	@Test
	//查询当前用户下的所有项目
	public void httpGet1() throws Exception {
		//url中变量为ip为Gitlab服务器ip地址，private_token为用户的private_token，url中的其他地方固定不变
		
		String url = "http://192.168.124.46/api/v3/projects?private_token=dsP3AyeVWj6WNQBeE1x8";
		String entity = Utils.httpGet(url);
		System.out.println(entity);
	}
	
	
	//查询当前用户的某个项目
		@Test
		public void httpGet2() throws Exception {
			//变量为项目id
			String url = "http://192.168.124.46/api/v3/projects/id?private_token=2W6HbdQc4NuVbgTaz1hW";
			String entity = Utils.httpGet(url);
			System.out.println(entity);
			
		}
	
	@Test
	//新建项目
	public void post1() throws Exception {
		
		String url = "http://192.168.124.46/api/v3/projects";
		List<NameValuePair> formparams = new ArrayList <NameValuePair>();
		//新建项目大约有20个参数可以设置
		formparams.add(new BasicNameValuePair("private_token", "2W6HbdQc4NuVbgTaz1hW"));
		formparams.add(new BasicNameValuePair("name", "aaa"));
		formparams.add(new BasicNameValuePair("path", "bbb"));
		System.out.println(Utils.httpPost(url, formparams));
		
	}
	
	
	@Test
	//删除单个项目(根据id删除，返回true，或者false,projects后面为项目id)
	public void httpDelete() throws Exception {
		
		String url = "http://192.168.124.46/api/v3/projects/24?private_token=2W6HbdQc4NuVbgTaz1hW";
		System.out.println(Utils.httpDelete(url));
	}
	
	@Test
	//修改某个项目(根据id修改)
	public void httpPut() throws Exception {
		
		String url = "http://192.168.124.46/api/v3/projects/23?private_token=2W6HbdQc4NuVbgTaz1hW";
		List<NameValuePair> formparams = new ArrayList <NameValuePair>();
		//修改项目大约有20个参数可以设置
		formparams.add(new BasicNameValuePair("private_token", "2W6HbdQc4NuVbgTaz1hW"));
		formparams.add(new BasicNameValuePair("name", "ffff"));
		formparams.add(new BasicNameValuePair("id", "23"));
		formparams.add(new BasicNameValuePair("path", "aaaa"));
		System.out.println(Utils.httpPut(url, formparams));
	}
	
	
	//项目成员的增删改查
	//查询某个项目的成员列表
	@Test
	public void httpGet12() throws Exception {
		//变量为项目id
		String url = "http://192.168.124.46/api/v3/projects/id/members?private_token=2W6HbdQc4NuVbgTaz1hW";
		String entity = Utils.httpGet(url);
		System.out.println(entity);
		
	}
		
	//查询项目成员中某个成员信息
	@Test
	public void httpGet13() throws Exception {
		//变量为项目id，members后为userid
		String url = "http://192.168.124.46/api/v3/projects/23/members/15?private_token=2W6HbdQc4NuVbgTaz1hW";
		String entity = Utils.httpGet(url);
		System.out.println(entity);
		
	}
		
	@Test
	//给项目添加成员
	public void post3() throws Exception {
		//projects后为项目id
		String url = "http://192.168.124.46/api/v3/projects/23/members";
		List<NameValuePair> formparams = new ArrayList <NameValuePair>();
		//有多个参数可设置
		formparams.add(new BasicNameValuePair("private_token", "2W6HbdQc4NuVbgTaz1hW"));
		formparams.add(new BasicNameValuePair("user_id", "15"));
		formparams.add(new BasicNameValuePair("access_level", "30"));
		System.out.println(Utils.httpPost(url, formparams));
		
	}
	
	
	//删除项目中的成员
	@Test
	public void httpDelete4() throws Exception {
		//变量为项目id，members后为userid
		String url ="http://192.168.124.46/api/v3/projects/23/members/15?private_token=2W6HbdQc4NuVbgTaz1hW";
		System.out.println(Utils.httpDelete(url));
	}
	
	//修改项目成员信息
	@Test
	public void httpPut1() throws Exception {
		//变量为项目id，members后为userid
		String url = "http://192.168.124.46/api/v3/projects/23/members/15";
		List<NameValuePair> formparams = new ArrayList <NameValuePair>();
		formparams.add(new BasicNameValuePair("private_token", "2W6HbdQc4NuVbgTaz1hW"));
		formparams.add(new BasicNameValuePair("access_level", "30"));
		System.out.println(Utils.httpPut(url, formparams));
	}
	
	
	
	//对项目分支的增删改查
	
	//查询项目的所有分支
	@Test
	public void httpGet6() throws Exception {
		//变量为项目id
		String url = "http://192.168.124.46/api/v3/projects/23/repository/branches?private_token=2W6HbdQc4NuVbgTaz1hW";
		String entity = Utils.httpGet(url);
		System.out.println(entity);
		
	}
	
	//查询项目的某个分支
	@Test
	public void httpGet8() throws Exception {
		//变量为项目id，branches后为分支名称
		String url = "http://192.168.124.46/api/v3/projects/19/repository/branches/develop?private_token=2W6HbdQc4NuVbgTaz1hW";
		String entity = Utils.httpGet(url);
		System.out.println(entity);
		
	}
	@Test
	//给项目创建分支
	public void post4() throws Exception {
		//变量为项目id
		String url = "http://192.168.124.46/api/v3/projects/23/repository/branches";
		
		List<NameValuePair> formparams = new ArrayList <NameValuePair>();
		formparams.add(new BasicNameValuePair("private_token", "2W6HbdQc4NuVbgTaz1hW"));
		formparams.add(new BasicNameValuePair("branch_name", "wwww"));  //新分支名称 
		formparams.add(new BasicNameValuePair("ref", "newbranch"));     //ref原始分支
		System.out.println(Utils.httpPost(url, formparams));
		
	}
	
	//删除项目中的某个分支
		@Test
		public void httpDelete98() throws Exception {
			//变量为项目id，branches后为分支名称
			String url ="http://192.168.124.46/api/v3/projects/23/repository/branches/wwww?private_token=2W6HbdQc4NuVbgTaz1hW";
			
			System.out.println(Utils.httpDelete(url));
		}
		
	//对用户的增删改查
		//管理员查询所有用户
		@Test
		public void httpGet67() throws Exception {
			
			String url = "http://192.168.124.46/api/v3/users?private_token=2W6HbdQc4NuVbgTaz1hW";
			String entity = Utils.httpGet(url);
			System.out.println(entity);
			
		}
		//管理员创建用户
		@Test
		//
		public void post40() throws Exception {
			
			String url = "http://192.168.124.46/api/v3/users";
			
			List<NameValuePair> formparams = new ArrayList <NameValuePair>();
			formparams.add(new BasicNameValuePair("private_token", "2W6HbdQc4NuVbgTaz1hW"));
			formparams.add(new BasicNameValuePair("email", "1069649738@qq.com"));  
			formparams.add(new BasicNameValuePair("password", "shuwang"));    
			formparams.add(new BasicNameValuePair("username", "shuwang"));    
			formparams.add(new BasicNameValuePair("name", "shuwang"));    
			
			System.out.println(Utils.httpPost(url, formparams));
			
		}
		
		//管理员修改用户
		@Test
		public void httpPut12() throws Exception {
			//变量为用户id
			String url = "http://192.168.124.46/api/v3/users/id";
			
			List<NameValuePair> formparams = new ArrayList <NameValuePair>();
			//大约十多个参数可以设置
			formparams.add(new BasicNameValuePair("email", "2W6HbdQc4NuVbgTaz1hW"));
			formparams.add(new BasicNameValuePair("username ", "30"));
			
			
			System.out.println(Utils.httpPut(url, formparams));
		}
		
		//管理员删除用户
		@Test
		public void httpDelete92() throws Exception {
			//变量为用户id
			String url ="http://192.168.124.46/api/v3/users/id?private_token=2W6HbdQc4NuVbgTaz1hW";
			
			System.out.println(Utils.httpDelete(url));
		}
		
	@Test
	//通过用户名和密码获取private_token
	public void post2() throws Exception {
		
		String url = "http://192.168.124.46/api/v3/session";
		
		List<NameValuePair> formparams = new ArrayList <NameValuePair>();
		formparams.add(new BasicNameValuePair("login", "liyanjun"));
		formparams.add(new BasicNameValuePair("password", "liyanjun"));
		
		System.out.println(Utils.httpPost(url, formparams));
		
	}
	
	
}
