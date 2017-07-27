package com.cmsz.utils;

public class UtilFuns {

	public static boolean isEmpty(String str){
		if("".equals(str) || str.length() == 0 || str.equals(null)){
			return true;
		}else{
			return false;
		}
	}
	
	public static String getNumbers() {
		String str = "";
		for (int i = 0; i < 6 ; i++) {
			int d = (int)(Math.random()*1000);
			str = str + d % 9;
		}
			
		return str;
	}
	
}
