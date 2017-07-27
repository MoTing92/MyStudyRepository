package com.cmsz.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmsz.bean.PageMsg;
import com.cmsz.bean.ResetPara;
import com.cmsz.bean.ReturnLogin;
import com.cmsz.bean.ReturnMsg;
import com.cmsz.bean.Role;
import com.cmsz.bean.User;
import com.cmsz.bean.UserData;
import com.cmsz.bean.UserLogin;
import com.cmsz.service.IUserService;
import com.cmsz.utils.Constant;
import com.cmsz.utils.ImageUtil;
import com.cmsz.utils.SendSms;
import com.cmsz.utils.UtilFuns;
import com.github.pagehelper.PageInfo;
/***
 * 用户管理 
 */
@Controller
@RequestMapping("/user")
public class UserAction {
	
	static final Logger LOGGER = Logger.getLogger(UserAction.class);
	@Resource
	IUserService userService;
	
	/******
	 * 用户集合信息
	 * 请求参数为PageMsg对象
	 * 响应参数为用户信息与角色集合，返回状态（state）和消息(message)
	 * *****/
	@RequestMapping(value="/query",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMsg<User> query(@RequestBody PageMsg page){
		//封装类存放用户对象
		ReturnMsg<User> returnMsg = new ReturnMsg<User>();
		//分页对象
		PageInfo<User> pageInfo =null;
		try {
			pageInfo=userService.query(page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error("UserAction的query请求异常",e);
		}
		//判断该对象是否为空
		if(pageInfo.getList().size() > 0){
			//放入分页集合
			returnMsg.setData(pageInfo.getList());
			//每页显示数量
			returnMsg.setPageSize(pageInfo.getPageSize());
			//当前页
			returnMsg.setTotalCount(pageInfo.getTotal());
			returnMsg.setState(Constant.SUCCESS_STATE);
			returnMsg.setMessage(Constant.SUCCESS_MESSAGE);
		}
		return returnMsg;
	}
	
	/*****
	 * 用户注册
	 * 请求参数为用户信息
	 * 响应参数为返回状态（state）和消息(message)
	 * **/
	@RequestMapping(value="/apply",method=RequestMethod.POST)
	@ResponseBody
	public ReturnMsg<User> apply(@RequestBody User user){
		//封装类存放用户对象
		ReturnMsg<User> returnMsg = new ReturnMsg<User>();
		//添加用户信息
		try {
			userService.add(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error("UserAction的apply请求异常",e);
		}
		//获取用户Id不为0就添加成功否则失败
		if(user.getUserId() != 0){
			returnMsg.setState(Constant.SUCCESS_STATE);
			returnMsg.setMessage("注册成功");
			return returnMsg;
		}else{
			returnMsg.setState(Constant.SUCCESS_STATE);
			returnMsg.setMessage("注册失败");
			return returnMsg;
		}
	}
	
	/*********
	 * 用户信息修改
	 * 请求参数为用户信息
	 * 响应参数为返回状态（state）和消息(message)
	 * ****/
	@RequestMapping(value="/update")
	@ResponseBody
	public ReturnMsg<User> update(@RequestBody User user){
		//封装类存放用户对象
		ReturnMsg<User> returnMsg = new ReturnMsg<User>();
		//进行用户修改
		boolean isSuccess =false;
		try {
			isSuccess=userService.update(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error("UserAction的update请求异常",e);
		}
		//true ? false
		if(isSuccess){
			returnMsg.setState(Constant.SUCCESS_STATE);
			returnMsg.setMessage("修改成功");
			return returnMsg;
		}else{
			returnMsg.setState(Constant.SUCCESS_STATE);
			returnMsg.setMessage("修改失败");
			return returnMsg;
		}
	}
	
	/******
	 * 管理员删除用户
	 * 请求参数为用户Id(userId)
	 * 响应参数为返回状态（state）和消息(message)
	 * ****/
	@RequestMapping(value="/del")
	@ResponseBody
	public ReturnMsg<User> del(@RequestBody User user){
		//封装类存放用户对象
		ReturnMsg<User> returnMsg = new ReturnMsg<User>();
		//删除
		boolean isSuccess=false;
		try {
			isSuccess= userService.delete(user.getUserId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error("UserAction的del请求异常",e);
		}
		//true ? false
		if(isSuccess){
			returnMsg.setState(Constant.SUCCESS_STATE);
			returnMsg.setMessage("删除成功");
			return returnMsg;
		}else{
			returnMsg.setState(Constant.SUCCESS_STATE);
			returnMsg.setMessage("删除失败");
			return returnMsg;
		}
	}
	
	/***
	 * 管理员修改用户角色
	 * 请求参数为用户手机号与角色集合（一个用户对于多个角色）
	 * 响应参数为返回状态（state）和消息(message)
	 * **/
	@RequestMapping(value="/role",method = RequestMethod.POST)
	@ResponseBody
	public ReturnMsg<User> role(@RequestBody User user){
		//封装类存放用户对象
		ReturnMsg<User> returnMsg = new ReturnMsg<User>();
		//更新
		try {
			userService.roleUserUpdate(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.error("UserAction的role请求异常",e);
		}
		//true ? false
		if(user.getUserId()!=null){
			returnMsg.setState(Constant.SUCCESS_STATE);
			returnMsg.setMessage("角色修改成功");
			return returnMsg;
		}else{
			returnMsg.setState(Constant.SUCCESS_STATE);
			returnMsg.setMessage("角色修改失败");
			return returnMsg;
		}
		
	}
	
	/***
	 * 获取手机验证码
	 * 请求参数为session,手机号
	 * 响应参数为返回状态（state）和消息(message)
	 * **/
	@RequestMapping("/getPhoneCode")
	@ResponseBody
	public ReturnMsg<User> sengMessage(HttpSession session,String phone) {
			//封装类存放用户对象
			ReturnMsg<User> returnMsg = new ReturnMsg<User>();
			//生成6位手机验证码
			try {
				String numbers = UtilFuns.getNumbers();		
				//讲验证码和手机号传入验证码工具类
				SendSms.send(numbers,phone);
				//保存到session作用域
				session.setAttribute(phone, numbers);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LOGGER.error("UserAction的getPhoneCode请求异常",e);
			}
			returnMsg.setMessage("发送成功");
			returnMsg.setState("10000");
			return returnMsg;
	}
	
	
	/****
	 * 用户登录
	 * 请求参数UserLogin对象
	 * 响应参数为ReturnLogin对象
	 * **/
	@RequestMapping("/login")
	@ResponseBody
	public ReturnLogin login(@RequestBody UserLogin userLogin,HttpSession session,ReturnLogin returnLogin,HttpServletRequest request) throws Exception, IOException{
		//用户名
		String username = userLogin.getUsername();
		//密码
		String password = userLogin.getPassword();
		//判断登录方式,1为手机短信登录，0为用户名密码登录
		if("1".equals(userLogin.getLoginType())){
			//定义用户
			User user = null;
			//根据电话查询用户信息
			try {
				user = userService.queryByPhoneNumber(userLogin.getPhone());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LOGGER.error("getPhoneCode的login手机登录异常",e);
			}
			//user对象为空
			if(user == null) {	
				returnLogin.setState("20000");
				returnLogin.setMessage("用户未注册，请先去注册再登录");
				return returnLogin;
			}
			//从session中取手机号验证码
			String str = (String) session.getAttribute(user.getPhoneNumber()+"");
			if(!userLogin.getPhoneCode().equals(str)) {
				returnLogin.setState("20000");
				returnLogin.setMessage("短信验证码输入错误，请重新登录");
				return returnLogin;
			}
			username = user.getUsername();
			password = user.getPassword();
			session.removeAttribute(user.getPhoneNumber()+"");
		}else {
			//为用户名密码登录，需要判断图片验证码是否正确，判断用户名是否存在
			String imageCode = (String) session.getAttribute(request.getRemoteAddr());
			if(!userLogin.getImageValue().equals(imageCode)) {
				returnLogin.setState("20000");
				returnLogin.setMessage("图片验证码输入错误，请重新登录");
				return returnLogin;
			}
			User user = userService.queryByUsername(username);
			if(user == null) {	
				returnLogin.setState("20000");
				returnLogin.setMessage("用户未注册，请先去注册再登录");
				return returnLogin;
			}
			session.removeAttribute(request.getRemoteAddr());
		}
		try {
			//1.得到Subject对象
			Subject subject = SecurityUtils.getSubject();
			//2.调用subject中的方法实现登录   当它执行时，会自动跳到AuthRealm中的认证方法
			subject.login(new UsernamePasswordToken(username, password));
			//3.从Shiro中取出用户的登录信息
			User user = (User) subject.getPrincipal();
			//4.将用户信息放入session域中
			session.setAttribute("CURRENT_USER_INFO", user);
			List<String> menus = userService.queryMenusByUid(user.getUserId());
			List<Role> roles = userService.queryRoleIdByUserId(user.getUserId());
			List<Integer> roleId = new ArrayList<Integer>();
			for (Role role : roles) {
				roleId.add(role.getRoleId());
			}
			UserData userData = userService.queryUserDatabyUid(user.getUserId());		
			returnLogin.setIsAdmin(roleId.contains(130)? true:false);
			returnLogin.setPowerList(menus);
			returnLogin.setUserId(user.getUserId() + "");
			returnLogin.setData(userData);
			returnLogin.setState(Constant.SUCCESS_STATE);
			returnLogin.setMessage("登录成功");
			return returnLogin;
		} catch (Exception e) {
			e.printStackTrace();
			returnLogin.setState("20000");
			returnLogin.setMessage("用户名或密码错误，请重新输入");
			return returnLogin;
		}
	
	}
	
	/****
	 * 获取图片验证码
	 * ***/
    @RequestMapping(value = "/getCodeImage", method = RequestMethod.GET)
 	@ResponseBody
	public void getImage(String sid,HttpServletRequest request,
            HttpServletResponse response,HttpSession session,UserLogin userLogin) {
    	
        // 产生4位数字验证码
        String identifyingCode=null;
		try {
			identifyingCode = ImageUtil.generateNumber();
			session.setAttribute(request.getRemoteAddr(),identifyingCode);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			LOGGER.debug("验证吗"+identifyingCode);
		}
       
        // 将验证码生成图片
        try {
            ImageUtil.generateImage(identifyingCode, response);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("UserAction的getCodeImage异常",e);
        }
    }
    
    @RequestMapping(value = "/resetPassword",method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg<User> resetPassword(@RequestBody ResetPara reset){
    	return userService.resetPassword(reset);
    }
}
