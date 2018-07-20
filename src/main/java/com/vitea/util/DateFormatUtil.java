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
	//返回当前时间
	public static Date getFormatDate() {
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String dateString=format.format(date);
		Date dateformat=null;
		try {
			dateformat=format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateformat;
		
	}

}
