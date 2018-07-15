package com.vitea.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	public static String getProperties(String address) {
		Properties p=new Properties();
		// 使用ClassLoader加载properties配置文件生成对应的输入流
	   InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("header.properties");
		try {
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	   return p.getProperty(address);
	}

}
