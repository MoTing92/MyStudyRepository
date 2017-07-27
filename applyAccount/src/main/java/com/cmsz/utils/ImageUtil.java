package com.cmsz.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

public class ImageUtil {

	/*
	 * 验证码宽度
	 */
	public static int WIDTH = 65;
	/*
	 * 验证码高度
	 */
	public static int HEIGHT = 25;

	/*
	 * 验证码背景颜色COLOR_FC_BG 应当小于COLOR_BC_BG
	 */
	public static int COLOR_FC_BG = 200;
	/*
	 * 验证码背景颜色COLOR_FC_BG 应当小于COLOR_BC_BG
	 */
	public static int COLOR_BC_BG = 250;

	/*
	 * 验证码背景干扰线颜色COLOR_FC_LINE 应当小于COLOR_BC_LINE
	 */
	public static int COLOR_FC_LINE = 160;
	/*
	 * 验证码背景干扰线颜色COLOR_FC_LINE 应当小于COLOR_BC_LINE
	 */
	public static int COLOR_BC_LINE = 200;

	/*
	 * 验证码颜色COLOR_FC_CODE 应当小于COLOR_BC_CODE
	 */
	public static int COLOR_FC_CODE = 20;
	/*
	 * 验证码颜色COLOR_FC_CODE 应当小于COLOR_BC_CODE
	 */
	public static int COLOR_BC_CODE = 170;

	/*
	 * 生成在指定范围内的颜色
	 * 
	 * @param fc 范围fc color值 小于255
	 * 
	 * @param bc 范围bc color值 小于255
	 * 
	 * @return Color
	 */
	public static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc < 0)
			fc = 0;
		if (bc < 0)
			bc = 1;
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		if (bc == fc)
			bc += 10;
		int temp = 0;
		if (bc < fc) {
			temp = bc;
			bc = fc;
			fc = temp;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 
	 * @Description:生成图片方法
	 * @author:
	 * @time:2016年5月17日 下午6:21:15
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @param @throws Exception
	 * @return boolean
	 * @exception
	 */
	public synchronized static boolean generateImage(String codeValue,
			HttpServletResponse response) throws Exception {
		response.setContentType("image/jpeg");
		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 在内存中创建图象
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);

		// 获取图形上下文
		Graphics img = image.getGraphics();
		// 生成随机类
		Random random = new Random();

		// 设定背景色
		img.setColor(getRandColor(COLOR_FC_BG, COLOR_BC_BG));
		img.fillRect(0, 0, WIDTH, HEIGHT);

		// 设定字体
		img.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		/*
		 * 画边框 g.setColor(new Color()); g.drawRect(0,0,width-1,height-1);
		 */
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		img.setColor(getRandColor(COLOR_FC_LINE, COLOR_BC_LINE));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(WIDTH);
			int y = random.nextInt(HEIGHT);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			img.drawLine(x, y, x + xl, y + yl);
		}
		/*
		 * // 取随机产生的认证码(4位数字) imageValue = ""; for (int i = 0; i < 4; i++) { //
		 * String rand = String.valueOf(random.nextInt(10)); String rand =
		 * String.valueOf(Math.round(Math.random() * 9)); imageValue =
		 * imageValue.concat(rand); // img.setFont(getRandomFont());// 随机字体
		 * img.setFont(new Font("Times New Roman", Font.BOLD, 20)); //
		 * 将认证码显示到图象中 img.setColor(getRandColor(COLOR_FC_CODE, COLOR_BC_CODE));
		 * img.drawString(rand, 13 * i + 6, 16); }
		 */
		/* 注释时间为2016-08-10 */

		char[] array = codeValue.toCharArray();
		for (int i = 0; i < array.length; i++) {
			img.setFont(new Font("Times New Roman", Font.BOLD, 20));// 随机字体
			// 将认证码显示到图象中
			img.setColor(getRandColor(COLOR_FC_CODE, COLOR_BC_CODE));
			img.drawString(String.valueOf(array[i]), 13 * i + 6, 16);
		}
		// 图象生效
		img.dispose();
		// 输出图象到页面
		return ImageIO.write(image, "JPEG", response.getOutputStream());
	}

	/**
	 * 随机生成的验证码(4位数字)
	 * 
	 * @param request
	 * @return
	 */
	public static synchronized String generateNumber() {
		StringBuffer identifyingCode = new StringBuffer("");
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(Math.round(Math.random() * 9));
			identifyingCode.append(rand);
		}
		return identifyingCode.toString();
	}

	/**
	 * 
	 * @Description:随机生成字符，含大写、小写、数字
	 * @author:
	 * @time:2016年5月17日 下午6:22:26
	 * @param @return
	 * @return String
	 * @exception
	 */
	public static String getRandomChar() {
		int index = (int) Math.round(Math.random() * 2);
		String randChar = "";
		switch (index) {
		case 0:// 大写字符
			randChar = String
					.valueOf((char) Math.round(Math.random() * 25 + 65));
			break;
		case 1:// 小写字符
			randChar = String
					.valueOf((char) Math.round(Math.random() * 25 + 97));
			break;
		default:// 数字
			randChar = String.valueOf(Math.round(Math.random() * 9));
			break;
		}
		return randChar;
	}

	/**
	 * 
	 * @Description:随机生成字体、文字大小
	 * @author:
	 * @time:2016年5月17日 下午6:22:58
	 * @param @return
	 * @return Font
	 * @exception
	 */
	public static Font getRandomFont() {
		String[] fonts = { "Georgia", "Verdana", "Arial", "Tahoma",
				"Time News Roman", "Courier New", "Arial Black", "Quantzite" };
		int fontIndex = (int) Math.round(Math.random() * (fonts.length - 1));
		int fontSize = (int) Math.round(Math.random() * 4 + 16);
		return new Font(fonts[fontIndex], Font.PLAIN, fontSize);
	}

	public static void main(String[] args) {
		String string = "123";
		char[] stringArr = string.toCharArray();
		System.out.println(stringArr);
	}
}
