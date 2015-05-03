package com.computer.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author sq
 *
 */
public class SqTools {
	/**
	 * @param 获得服务器当前日期 yyyy-MM-dd (HH:mm:ss.SSS)
	 * @return String 日期
	 */
	public static String getCurrentDate(String dateformat){
		// TimeZone.setDefault(TimeZone.getTimeZone("GMT+8")); 东八区
		Date date = Calendar.getInstance().getTime();
		return  new SimpleDateFormat(dateformat).format(date);// 获取服务器当前日期
	}
	/**
	 * @param smdate Date   
	 * @param edate Date   
	 * @return 获取两个日期之间相差天数
	 */
	public static int daysBetween(Date smdate,Date edate) 
    {    
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(edate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24); 
        if (between_days<0)between_days=-between_days;
        return Integer.parseInt(String.valueOf(between_days));           
    }
	
	/**
	 * @param number
	 * @return
	 */
	private static String  regex="^[1]([3-5]|8)[0-9]{9}$";
	public static Boolean matchPhoneNumber(String number){
		Pattern p = Pattern.compile(regex);  
		Matcher m = p.matcher(number);
	    return m.matches();
	}
}
