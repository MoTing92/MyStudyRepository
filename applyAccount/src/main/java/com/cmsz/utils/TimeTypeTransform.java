package com.cmsz.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeTypeTransform {

	public static String getTimeForSeconds(Date date){
		SimpleDateFormat formate = new SimpleDateFormat("yyyyMMddhhmm");
		String dateStr = formate.format(date);
		try {
			long timeSeconds = formate.parse(dateStr).getTime();
			String timeSecondsStr = String.valueOf(timeSeconds);
			return timeSecondsStr;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date setTimeSecondsToDate(String timeSecondsStr){
		long timeSecondsLong = Long.parseLong(timeSecondsStr);
		return new Date(timeSecondsLong);
	}
	
	public static Date setTimeSecondsToDateAfterYear(Date beforeDate){
		
		Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(beforeDate); 
        calendar.add(Calendar.YEAR, 1); 
        Date afterDate = calendar.getTime();
        return afterDate;
	}
	
	public static void main(String[] args) {
		Date date = new Date();
//		String dateSeconds = getTimeForSeconds(date);
//		System.out.println(dateSeconds);
//		Date dateTime = setTimeSecondsToDate(getTimeForSeconds(date));
//		System.out.println(dateTime);
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd-hh-mm");
//		String formateTinf = formate.format(date);
//		System.out.println(formateTinf);
		Date afterDate = setTimeSecondsToDateAfterYear(date);
		System.out.println("当前时间："+formate.format(date));
		System.out.println("一年后："+formate.format(afterDate));
	}
}
