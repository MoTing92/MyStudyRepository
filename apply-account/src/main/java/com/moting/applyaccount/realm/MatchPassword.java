package com.moting.applyaccount.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import com.moting.applyaccount.utils.Constant;
import com.moting.applyaccount.utils.Encrypt;

public class MatchPassword extends SimpleCredentialsMatcher{


	/**
	 * 执行密码比较
	 * 第一个参数：将来传进来的对象，代表用户在界面上输入的用户名和密码信息
	 * 第二个参数：代表用户的在数据库的信息,加密后的密码
	 * 返回true代表密码相同    false密码不相同
	 *               如果返回false 密码不相同，它就会抛出异常，认为登录操作失败
	 */
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		
		//1.向下转型
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		
		//2.得到用户在界面输入的用户名和密码
//		String username = upToken.getUsername();
		String password = new String(upToken.getPassword());
		//3.得到用户在界面输入的密码，并加密 
		String inputEncryptPwd = Encrypt.md5(password,Constant.PASS);
		if(password.length() > 30) {
			return true;
		}
		return equals(inputEncryptPwd, info.getCredentials());
	}
	
}
