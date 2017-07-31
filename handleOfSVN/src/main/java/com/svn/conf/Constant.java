package com.svn.conf;

public class Constant {

	public static final String HTTP_OF_HEADER = "http://192.168.215.200:3343/csvn/api/1/";
	public static final String HTTP_OF_CREATE_SVN_USER = HTTP_OF_HEADER + "user?format=json";
	public static final String HTTP_OF_REMOTE_REPOSITORY = HTTP_OF_HEADER + "repository?format=json";
	public static final String HTTP_OF_SVN_ROLE = HTTP_OF_HEADER + "role?format=json";
	public static final String HTTP_OF_LIST_USER = "http://192.168.215.200:3343/csvn/user/list";
	public static final String HTTP_OF_GET_TEMPLATE = HTTP_OF_HEADER + "template?format=json&showInactive=true";
	public static final String HTTP_OF_PUT_TEMPLATE = HTTP_OF_HEADER + "template/2?format=json";
	public static final String HTTP_OF_POST_TEMPLATE = HTTP_OF_HEADER + "template?format=json&name=My%20New%20Template&active=true";
	
}
