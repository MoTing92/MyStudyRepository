package com.moting.web.socket.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 
* @ClassName: ShiroUtil
* @Description: shiro相关工具类
* @author MoTing
* @date 2017年10月18日
* @version V1.0
 */
public class ShiroUtil {

	private ShiroUtil() {
	    throw new IllegalStateException("Utility class");
	  }
	
	public static String md5Encrypt(String orgPwd,String salt){
		Md5Hash encryptPwdd = new Md5Hash(orgPwd, salt, 1);
		return encryptPwdd.toString();
	}
}
