package com.cmsz.utils;


public class Constant {

	public  static final int  In_PASS = 1;
	public  static final int  Has_PASS = 2;
	public  static final int  UNPASS = 3; 
	
	public  static final String  UNPASS_Str = "审核未通过"; 
	public  static final String  In_PASS_Str = "审核中";
	public  static final String  Has_PASS_Str = "审核已通过";
	
	public  static final String  PASS_WAIT = "审核已通过,待";
	public  static final String  NEXT_PASS = "审核";
	public  static final String  EMAIL_TITLE = "账号申请审核";
	public  static final String  EMAIL_Content = "该申请已经通过了前一级的审核，请您通过以下地址登录账户申请系统  ，对该申请进行审核。谢谢！\n";
	public  static final String  EMAIL_TITLE_NOTE = "您的申请已经通过";
	public  static final String  EMAIL_Content_NOTE = "您的账户申请已经通过。";
	public  static final String  REPEAT_PASS = "您已经通过了该申请，不必重复审核!";
	
	public  static final String  NO_permission = "不好意思，您没有该操作权限";
	public  static final String  Has_UNPASS = "该申请已经被拒绝";
	public  static final String  Has_END_PASS = "该申请已经全部通过审核";
	
	public  static final String  SUCCESS_STATE = "10000";
	public  static final String  SUCCESS_MESSAGE = "成功";
	public  static final String  FAIL_MESSAGE = "该工单已经被拒绝";
	
	public  static final String Test_Email_Username = "13925210167@163.com";
	public  static final String Test_Email_Password = "1234abcd";
	
	public  static final String PASS = "tony";
	public  static final String PASS_AUTO = "ABC";
	public  static final String PHONE_CODE = "phone_code";
	public static final String IMAGE_SID = "sid";
	public static final String SHUOMING = "说明：\n"+
	"1.本申请表由账号使用人员提前2个工作日填写并签名，并经过账号审核人员与相关人员审核通过后，交与账号管理人员执行；\n"+
	"2.账号管理人员执行完毕后，通过申请人口令。申请人确认无误后由账号管理人员完成此表填写并交与账号审核人员存档；\n"+
	"3.账号使用人员为账号负责人，注意保密和定期更改账号口令。";
	
	public static final String ACCOUNT_PROPERTICES_STR1 = "超级用户账号";
	public static final String ACCOUNT_PROPERTICES_STR2 = "程序内置账号";
	public static final String ACCOUNT_PROPERTICES_STR3 = "用户具名账号";
	public static final String ACCOUNT_PROPERTICES_STR4 = "其他";
}
