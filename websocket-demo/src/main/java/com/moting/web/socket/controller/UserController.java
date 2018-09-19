package com.moting.web.socket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moting.web.socket.common.ReturnData;
import com.moting.web.socket.pojo.User;
import com.moting.web.socket.utils.CookieUtil;
import com.moting.web.socket.utils.ShiroUtil;

@RestController
@RequestMapping("/user")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostMapping("/login")
	public ReturnData<User> login(@RequestBody User user,HttpServletResponse response,HttpServletRequest request){
		logger.info("渠道门户-账号管理-登录-输入参数："+user.getUsername());
		if(StringUtils.isEmpty(user.getUsername())){
			logger.error("用户名为空");
			return new ReturnData<User>("1001", "用户名为空");
		}
		if(StringUtils.isEmpty(user.getPassword())){
			logger.error("密码为空");
			return new ReturnData<User>("1002", "密码为空");
		}
		UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(user.getUsername(),user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        try {
        	//完成登录
        	if(subject.isAuthenticated()) {
            	User userOrg = (User)session.getAttribute("channelManagerEmp");
            	if(userOrg != null && userOrg.getUsername().equals(user.getUsername()) 
            			&& userOrg.getPassword().equals(ShiroUtil.md5Encrypt(user.getPassword(), userOrg.getUserId().toString()))) {
            		logger.info("该用户已经登录，无需重复登录！");
                    return new ReturnData<User>("1003","该用户已经登录，无需重复登录",userOrg);
                }
        	}
        	subject.login(usernamePasswordToken);   
        	User userLogined = (User) subject.getPrincipal();
        	logger.info("登录成功后将用户信息保存到session作用域中");
        	session.setAttribute("userLogined", userLogined);
        	logger.info("登录成功后将用户名和密码保存在cookie中，cookie对象为:loginInfo，格式为：[用户名，密码]");
            String loginInfo = user.getUsername()+"#"+user.getPassword();
            //存活期为一个月 30*24*60*60
            response = CookieUtil.setCookie(response, "loginInfo", loginInfo, 7*24*60*60,request);
            logger.info("渠道门户-账号管理-登录成功");
            return new ReturnData<User>("0000","登录成功",userLogined);
        }  catch(UnknownAccountException e) {
        	logger.error("用户不存在");
        	logger.error("异常信息：\n :"+e.getMessage());
        	return new ReturnData<User>("1004","用户不存在");//返回登录页面
        }  catch(AuthenticationException e) {
        	logger.error("用户名或密码错误");
        	logger.error("异常信息：\n :"+e.getMessage());
            return new ReturnData<User>("1005","用户名或密码错误");//返回登录页面
        } catch(Exception e) {
        	logger.error("其他未知错误");
        	logger.error("异常信息：\n :"+e.getMessage());
        	return new ReturnData<User>("9000", "其他未知错误"+":"+e.getMessage());
		}
	}
}
