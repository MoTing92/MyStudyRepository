package com.cmsz.utils;

import java.io.InputStream;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Utils {
	//get请求，一般用于查询
	public static String httpGet(String url) throws Exception {
		
		CloseableHttpClient httpclients = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpclients.execute(httpGet);
		HttpEntity httpEntity = response.getEntity();
		
		try{
			HttpEntity entity = response.getEntity();
			if(entity != null) {
				InputStream is = entity.getContent();
			}
		}finally{
			response.close();
		}
		
		return EntityUtils.toString(httpEntity);
		
	}
	
	
	//post请求，一般用于创建
	public static String httpPost(String url,List<NameValuePair> formparams) throws Exception {
		CloseableHttpClient httpclients = HttpClients.createDefault();
		
		HttpPost httpPost = new HttpPost(url);
		
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,Consts.UTF_8);
		
		httpPost.setEntity(entity);
		CloseableHttpResponse response = httpclients.execute(httpPost);
		HttpEntity entity1 = response.getEntity();
		
		try{
			HttpEntity entity5 = response.getEntity();
			if(entity != null) {
				InputStream is = entity.getContent();
			}
		}finally{
			response.close();
		}
		
		return EntityUtils.toString(entity1);
		
	}
	
	
	//put请求，一般用于修改
	public static String httpPut(String url,List<NameValuePair> formparams) throws Exception {
		CloseableHttpClient httpclients = HttpClients.createDefault();
		
		HttpPut httpPut = new HttpPut(url);
		
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,Consts.UTF_8);
		
		httpPut.setEntity(entity);
		CloseableHttpResponse response = httpclients.execute(httpPut);
		HttpEntity entity1 = response.getEntity();
		
		try{
			HttpEntity entity5 = response.getEntity();
			if(entity != null) {
				InputStream is = entity.getContent();
			}
		}finally{
			response.close();
		}
		
		return EntityUtils.toString(entity1);
		
	}
	//delete请求，一般用于删除
	public static String httpDelete(String url) throws Exception {
		
		
		CloseableHttpClient httpclients = HttpClients.createDefault();
		HttpDelete hd = new HttpDelete(url);
		CloseableHttpResponse response = httpclients.execute(hd);
		HttpEntity entity1 = response.getEntity();
		
		try{
			HttpEntity entity = response.getEntity();
			if(entity != null) {
				InputStream is = entity.getContent();
			}
		}finally{
			response.close();
		}
		
		return EntityUtils.toString(entity1);
	}


}
