package com.moting.applyaccount.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.moting.applyaccount.bean.User;


public class SendEMailUtils {

	public static String myEmailSMTPHost = "smtp.163.com";

	/**
	 * 
	 * @param senduser 发件人名字
	 * @param user 收件人
	 * @param title 邮件标题
	 * @param content 邮件内容
	 * @param myEmailAccount 发件邮箱地址
	 * @param myEmailPassword 发件邮箱密码
	 * @throws Exception
	 */
	public static void sendMsg(String senderName , User user,String title, String content,String myEmailAccount,String myEmailPassword) throws Exception {
//		String myEmailAccount = "13925210167@163.com";
//		String myEmailPassword = "1234abcd";
		// 收件人邮箱
//		String receiveMailAccount = "13925210167@163.com";
//		String receiveMailAccount = user.getEmail().toString();
		
		// 1. 创建参数配置, 用于连接邮件服务器的参数配置
		Properties properties = new Properties(); // 参数配置
		properties.put("mail.transport.protocol", "smtp");// 连接协议
		properties.put("mail.smtp.host", myEmailSMTPHost); // 主机名
//		properties.put("mail.smtp.port", 465); // 端口号
		properties.put("mail.smtp.auth", "true");
//		properties.put("mail.smtp.ssl.enable", "true"); // 设置是否使用ssl安全连接
														// ---一般都使用
		properties.put("mail.debug", "true");

		Session session = Session.getDefaultInstance(properties);
		session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log

		// 3. 创建一封邮件
		MimeMessage message = SendEMailUtils.createMimeMessage( title, content,session, myEmailAccount, user);

		// 4. 根据 Session 获取邮件传输对象
		Transport transport = session.getTransport();

		transport.connect(myEmailAccount, myEmailPassword);

		// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人,
		// 抄送人, 密送人
		transport.sendMessage(message, message.getAllRecipients());

		// 7. 关闭连接
		transport.close();
	}
	
	/**
	 * 创建一封只包含文本的简单邮件
	 *
	 * @param session
	 *            和服务器交互的会话
	 * @param sendMail
	 *            发件人邮箱
	 * @param receiveMail
	 *            收件人邮箱
	 * @return
	 * @throws Exception
	 */
	private static MimeMessage createMimeMessage(String title, String content,Session session, String sendMail, User user) throws Exception {
		// 1. 创建一封邮件
		MimeMessage message = new MimeMessage(session);

		// 2. From: 发件人
		message.setFrom(new InternetAddress(sendMail, user.getUsername(), "UTF-8"));

		// 3. To: 收件人（可以增加多个收件人、抄送、密送）
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(user.getEmail(), user.getUsername(), "UTF-8"));

		// 4. Subject: 邮件主题
		message.setSubject(title, "UTF-8");

		// 5. Content: 邮件正文（可以使用html标签）
		message.setContent(content, "text/html;charset=UTF-8");

		// 6. 设置发件时间
		message.setSentDate(new Date());

		// 7. 保存设置
		message.saveChanges();

		return message;
	}
	
	public static void main(String[] args) {
//		String a = CommonUtils.getVerificationCode();
//		List<Integer> list = new ArrayList<Integer>();
		User user = new User();
		user.setEmail("18209202332@163.com");
		user.setUsername("moting");
		try {
			SendEMailUtils.sendMsg("yidong", user, "title", "content", "18209202332@163.com", "1234abcd");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
