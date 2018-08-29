package com.vitea.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mongo.domain.InterfaceLog;
import com.mongodb.BasicDBObject;
import com.vitea.domain.InterFace;
import com.vitea.endpoint.dto.PageResult;
import com.vitea.service.IGetInterface;
import com.vitea.service.IGetInterfaceLog;
/**
 * 日志查询控制器
 * @author liushahe
 *
 */
@Controller
public class InterfaceLogController {
	@Autowired
	IGetInterfaceLog iGetInterfaceLog;
	@Autowired
	IGetInterface iGetInterface;
	/**
	 * 管理界面首页
	 * @return
	 */
	@RequestMapping("/getInterfaceLog")
	public String getInterfaceLog() {
		return "admin/interfacelog/logger";
	}
	@ResponseBody
	@RequestMapping("/getInterfaceLogByPage")
	public PageResult<InterfaceLog> getInterfaceLogByPage(@RequestParam(defaultValue = "null") String starttime,@RequestParam(defaultValue = "null") String endtime,@RequestParam(defaultValue = "null") String number,@RequestParam(defaultValue = "0") int pageNumber,@RequestParam(defaultValue = "10") int pageSize) {      
		Long count=iGetInterfaceLog.findAllCount(starttime, endtime, number);
		List<InterfaceLog> list=iGetInterfaceLog.getInterfaceLogByKey(starttime, endtime, number, pageNumber, pageSize);
		PageResult<InterfaceLog> pageResult=new PageResult<InterfaceLog>();
		pageResult.setTotal(count.intValue());
		pageResult.setRows(list);
	    return pageResult;
	}
	/**
	 * 区域分组信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("areacodeGroupScatter")
	public Object areacodeGroupScatter() {
		Iterator<BasicDBObject> it=iGetInterfaceLog.groupByMapReduceOfAreaCode();
		Map<String,Object> map=new HashMap<String,Object>();
		List<Integer> listvalue=new ArrayList<Integer>();
		List<Object> listname=new ArrayList<Object>();
		while(it.hasNext()) {
             String json=it.next().toJson();
             JSONObject jsObj=JSONObject.parseObject(json);
             JSONObject value=jsObj.getJSONObject("value");
			 listvalue.add(Integer.parseInt(value.getString("count").substring(0, value.getString("count").indexOf("."))));
			 switch(jsObj.getString("_id")) {
			 case "930":
				 listname.add("临夏");
				 break;
			 case "931":
				 listname.add("兰州");
				 break;
			 case "932":
				 listname.add("定西");
				 break;
			 case "933":
				 listname.add("平凉");
				 break;				 
			 case "934":
				 listname.add("庆阳");
				 break;				 
			 case "935":
				 listname.add("武威");
				 break;				 
			 case "936":
				 listname.add("张掖");
				 break;				 
			 case "937":
				 listname.add("酒泉");
				 break;
			 case "938":
				 listname.add("天水");
				 break;		
			 case "939":
				 listname.add("陇南");
				 break;
			 case "941":
				 listname.add("甘南");
				 break;
			 case "943":
				 listname.add("白银");
				 break;	
			 case "945":
				 listname.add("金昌");
				 break;		
			 case "947":
				 listname.add("嘉峪关");
				 break;	
			default	 :
				listname.add("其他");
			 }
			 
		}
		
		map.put("name", listname);
		map.put("value", listvalue);
		return JSONObject.toJSON(map);
		
	}
	/**
	 * 产品分组信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("productGroupScatter")
	public Object productGroupScatter() {
		Iterator<BasicDBObject> it=iGetInterfaceLog.groupByMapReduceOfProduct();
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		while(it.hasNext()) {
			Map<String,String> map=new HashMap<String,String>();
             String json=it.next().toJson();
             JSONObject jsObj=JSONObject.parseObject(json);
             JSONObject value=jsObj.getJSONObject("value");
             if(jsObj.getString("_id").equals("0")) {
            	 map.put("name", "移动手机"); 
             }else if(jsObj.getString("_id").equals("1")){
            	 map.put("name", "固话号码");  
             }else if(jsObj.getString("_id").equals("2")) {
            	 map.put("name", "宽带号码"); 
             }else if(jsObj.getString("_id").equals("3")) {
            	 map.put("name", "itv号码"); 
             }else {
            	 map.put("name", "其他号码"); 
             }
			 
			 map.put("value",value.getString("count"));
			 list.add(map);
		}
		return JSONObject.toJSON(list);
	}
	/**
	 * 10分钟内成功量
	 * @return
	 */
	@ResponseBody
	@RequestMapping("daySuccessTrans")
	public Object daySuccessTrans() {
		Iterator<BasicDBObject> it=iGetInterfaceLog.daySuccessTrans();
		Map<String,Object> map=new HashMap<String,Object>();
		List<Object> listvalue=new ArrayList<Object>();
		List<Object> listname=new ArrayList<Object>();
		while(it.hasNext()) {
             String json=it.next().toJson();
             JSONObject jsObj=JSONObject.parseObject(json);
             JSONObject value=jsObj.getJSONObject("value");
			 listvalue.add(value.getString("count"));
             InterFace interFace=iGetInterface.getInterfaceById(Integer.parseInt(jsObj.getString("_id")));
             listname.add(interFace.getName()); 
		}
		map.put("name", listname);
		map.put("value", listvalue);
		return JSONObject.toJSON(map);
		
	}
	/**
	 * 10分钟内失败量
	 * @return
	 */
	@ResponseBody
	@RequestMapping("dayFailTrans")
	public Object dayFailTrans() {
		Iterator<BasicDBObject> it=iGetInterfaceLog.dayFailTrans();
		Map<String,Object> map=new HashMap<String,Object>();
		List<Object> listvalue=new ArrayList<Object>();
		List<Object> listname=new ArrayList<Object>();
		while(it.hasNext()) {
             String json=it.next().toJson();
             JSONObject jsObj=JSONObject.parseObject(json);
             JSONObject value=jsObj.getJSONObject("value");
			 listvalue.add(value.getString("count"));
			 InterFace interFace=iGetInterface.getInterfaceById(Integer.parseInt(jsObj.getString("_id")));
             listname.add(interFace.getName()); 
		}
		
		map.put("name", listname);
		map.put("value", listvalue);
		return JSONObject.toJSON(map);
		
	}
	/**
	 * 当月各接口调用情况
	 * @return
	 */
	@ResponseBody
	@RequestMapping("currentTrans")
	public Object currentTrans() {
		Iterator<BasicDBObject> it=iGetInterfaceLog.currentTrans("success");
		Map<String,Object> map=new HashMap<String,Object>();
		List<Object> listsuccess=new ArrayList<Object>();
		List<Object> listname=new ArrayList<Object>();
		while(it.hasNext()) {
             String json=it.next().toJson();
             JSONObject jsObj=JSONObject.parseObject(json);
             JSONObject value=jsObj.getJSONObject("value");
             listsuccess.add(value.getString("count"));
             InterFace interFace=iGetInterface.getInterfaceById(Integer.parseInt(jsObj.getString("_id")));
			 listname.add(interFace.getName()); 
		}
		map.put("name", listname);
		map.put("success", listsuccess);
		return JSONObject.toJSON(map);
		
	}
	
	
}