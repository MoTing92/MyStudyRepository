package com.svn.tools;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

/**
 * 通过HTTPclient发送不同的http请求
 * @author MoTing
 * @date 2017年7月20日
 */
public class HttpSend extends ConsoleLog{

	/**
	 * 通过HTTPclient发送GET请求，并且该请求地址需要身份认证
	 * @param httpUrl 请求地址
	 * @param username 认证账户名
	 * @param password 认证密码
	 * @param name_value 请求参数列表，格式为：
	 * 			（parameterName1:parameterValue1,parameterName2:parameterValue2...）
	 * @return	返回该请求地址响应的内容
	 */
	public Object sendGetRequest(String httpUrl,String username,String password,String ...name_value){
		HttpClient httpclient = new HttpClient();
		super.log("创建httpclient");
		//将请求参数添加到地址中
		if(name_value != null && name_value.length > 0){
            String[] paras = dealRequestPara(name_value);
            for (int i = 0; i < paras.length; i++) {
            	if(i == 0){
            		httpUrl += "?" + paras[i] + "=" + paras[++i];
            	}else{
            		httpUrl += "&" + paras[i] + "=" + paras[++i];
            	}
    		}
        }
		GetMethod getMethod = new GetMethod(httpUrl);
		super.log("创建getMethod");
		String responseBody = "";
		try {  
            // 创建httpget.
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
            super.log("创建凭证");
            httpclient.getState().setCredentials(AuthScope.ANY, credentials);
			// 执行get请求.    
            int responseStatu = httpclient.executeMethod(getMethod); 
        	getMethod.getParams().setContentCharset("utf-8");
        	responseBody += getMethod.getResponseBodyAsString();
        	System.out.println(responseStatu);
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

	/**
	 * 通过HTTPclient发送POST请求，并且该请求地址需要身份认证
	 * @param httpUrl 请求地址
	 * @param username 认证账户名
	 * @param password 认证密码
	 * @param name_value 请求参数列表，格式为：
	 * 			（parameterName1:parameterValue1,parameterName2:parameterValue2...）
	 * @return	返回该请求地址响应的内容
	 */
	public Object sendPostRequest(String httpUrl,String username,String password,String ...name_value) {
		
		HttpClient httpClient = new HttpClient();
		super.log("创建httpClient");
		PostMethod postMethod = new PostMethod(httpUrl);
		super.log("创建postMethod");
		String responseBody = "";
		//设置http头    
        List <Header> headers = new ArrayList <Header>();  
        headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));    
        httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);    
        JSONObject jsonObject = new JSONObject();
        if(name_value != null && name_value.length > 0){
            String[] paras = dealRequestPara(name_value);
            for (int i = 0; i < paras.length; i++) {
            	jsonObject.put(paras[i], paras[++i]);
    		}
        }
        super.log("设置请求参数");
        try {
        	UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
        	super.log("创建凭证");
        	httpClient.getState().setCredentials(AuthScope.ANY, credentials);
			postMethod.setRequestEntity(new StringRequestEntity(jsonObject.toString(),null,"utf-8"));
			int responseState = httpClient.executeMethod(postMethod);
			postMethod.getParams().setContentCharset("utf-8");  
            responseBody += postMethod.getResponseBodyAsString();
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
            postMethod.releaseConnection(); 
        }  
		return responseBody;
	}
	
	/**
	 * 通过HTTPclient发送PUT请求，并且该请求地址需要身份认证
	 * @param httpUrl 请求地址
	 * @param username 认证账户名
	 * @param password 认证密码
	 * @param name_value 请求参数列表，格式为：
	 * 			（parameterName1:parameterValue1,parameterName2:parameterValue2...）
	 * @return	返回该请求地址响应的内容
	 */
	public Object sendPutRequest(String httpUrl,String username,String password,String ...name_value){
		HttpClient httpClient = new HttpClient();
		super.log("创建httpClient");
		PutMethod putMethod = new PutMethod(httpUrl);
		super.log("创建putMethod");
		String responseBody = "";
		//设置http头    
        List <Header> headers = new ArrayList <Header>();  
        headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));    
        httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);    
        JSONObject jsonObject = new JSONObject();
        if(name_value != null && name_value.length > 0){
            String[] paras = dealRequestPara(name_value);
            for (int i = 0; i < paras.length; i++) {
            	jsonObject.put(paras[i], paras[++i]);
    		}
        }
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
	
	public String [] dealRequestPara(String [] name_value){
		List<String> responseList = new ArrayList<String>();
		for (int i = 0; i < name_value.length; i++) {
			String[] content = name_value[i].split(":");
			String name = content[0];
			String value = content[1];
			responseList.add(name);
			responseList.add(value);
		}
		try {
			String [] responseArray = new String [responseList.size()];
			for (int i = 0; i < responseArray.length; i++) {
				responseArray[i] = responseList.get(i);
			}
			return responseArray;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
