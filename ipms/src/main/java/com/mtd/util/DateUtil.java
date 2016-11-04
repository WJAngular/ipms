
package com.mtd.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 
 * @ClassName: DateUtil
 * @Description: TODO
 * @author just_bamboo
 * @date 2015年12月29日 上午10:17:18
 *
 */
public class DateUtil {
	private static String F="yyyy-MM-dd HH:mm:ss";
	private static String F2="yyyy/MM/dd";
	private static SimpleDateFormat format=new SimpleDateFormat(F);
	private static SimpleDateFormat format2=new SimpleDateFormat(F2);
	
	public static String getTimeStr(long d){
		return format.format(new Date(d*1000));
	}
	
	public static String formatTimeStr(Date d){
		return format.format(d);
	}
	
	public static String formatDateStr(Date d){
		return format2.format(d);
	}
}