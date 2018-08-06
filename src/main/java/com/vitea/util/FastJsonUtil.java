package com.vitea.util;

import com.alibaba.fastjson.JSONObject;

/**
 * fastjson util
 * @author liushahe
 *
 */
public class FastJsonUtil {
	/**
	 * json2object
	 * @param input
	 * @return
	 */
	public static JSONObject json2Object(String input) {
		
     return JSONObject.parseObject(input);	
		 
	}

}
