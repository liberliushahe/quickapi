package com.vitea.util;

import org.json.JSONObject;
import org.json.XML;

import com.thoughtworks.xstream.XStream;
import com.vitea.dto.Report;
import com.vitea.dto.Result;

/**
 * 比较快的方法
 * 处理xml json相互转换
 * @author liushahe
 *
 */
public class XmlJsonParseUtil {
	
	/**org.json实现
	 * xml转json
	 * @param xml
	 * @return
	 */
	public static String xmlToJson(String xml) {
		    JSONObject xmlJSONObj = XML.toJSONObject(xml);
	        //设置缩进
	        String jsonPrettyPrintString = xmlJSONObj.toString(4);
		    return jsonPrettyPrintString;
		
	}
	/**
	 * org.json实现
	 * json转xml
	 * @param json
	 * @return
	 */
	public static String jsonToXml(String json) {
        JSONObject object=new JSONObject(json.trim());
        return XML.toString(object);		
	}
	
	/**
	 * volte对象-xml
	 * @param xml
	 * @return
	 */
	public static String object2XmlOfVolte(Object object) {
		 XStream xStream = new XStream();
	     //将对象转成字符串
		 //设置根别名
		 xStream.alias("report",Report.class);
		 xStream.alias("result",Result.class);
	     String s = xStream.toXML(object);
		 return s;
		
	}
	/**
	 * xml转对象
	 * @param xml
	 * @return
	 */
	public static Object xml2Object(String xml) {
		 //XStream xStream = new XStream();
         //xml转对象
         System.out.println(xml);
		 return xml;		
	}
}
