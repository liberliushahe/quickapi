package com.vitea.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 格式化日期并且返回日期类型
 * @author liushahe
 *
 */
public class DateFormatUtil {
	/**
	 * 返回当前时间
	 * @return
	 */
	public static Date getFormatDate() {
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateString=format.format(date);
		Date dateformat=null;
		try {
			dateformat=format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateformat;
		
	}
   /**
    * 返回毫秒日期
    * @return
    */
	public static String getFormatDateMill() {
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		return format.format(date);
	}
}
