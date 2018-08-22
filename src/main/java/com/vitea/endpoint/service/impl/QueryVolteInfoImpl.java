package com.vitea.endpoint.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vitea.dao.InterfaceDao;
import com.vitea.domain.InterFace;
import com.vitea.endpoint.dto.Report;
import com.vitea.endpoint.dto.Result;
import com.vitea.endpoint.service.IQueryVolteInfo;
import com.vitea.util.DateFormatUtil;
import com.vitea.util.FastJsonUtil;
import com.vitea.util.HttpClientUtil;
import com.vitea.util.XmlJsonParseUtil;

/**
 * 查询volte信息实现类
 * 
 * @author liushahe
 *
 */
public class QueryVolteInfoImpl implements IQueryVolteInfo {
	private final String HSS="hss";
	private final String AS="as";
	private final String CSCF="cscf";
	private final String ENUMDNS="enumdns";
	private final String CFU="m:DSP_CFU";
	private final String CFNR="m:DSP_CFNR";
	private final String CFNRC="m:DSP_CFNRC";
	private final String CFB="m:DSP_CFB";
	private final String EPS="EPS动态APN信息";
	
	
	@Autowired
	private InterfaceDao iPortDao;
	private Logger logger = LoggerFactory.getLogger("MONGODB");

	@Override
	public String platformQryVolteHss(String xml) {
		return parseParamAndGetDataInfo(xml);
	}
	@Override
	public String platformQryVolteCsCf(String xml) {
		return parseParamAndGetDataInfo(xml);
	}
	@Override
	public String platformQryVolteAs(String xml) {
		return parseParamAndGetDataInfo(xml);
	}
	@Override
	public String platformQryVolteEnumDns(String xml) {

		return parseParamAndGetDataInfo(xml);
	}
	/**
	 * 将获取到的xml数据解析
	 * 
	 * @param xml
	 * @return
	 */
	public Element parseXML(String xml) throws DocumentException {
		Document doc = null;
		doc = DocumentHelper.parseText(xml.trim());
		Element rootElt = doc.getRootElement();
		return rootElt;
	}

	/**
	 * 迭代JSONObject对象
	 * 
	 * @param results
	 * @param object
	 */
	public void interator(List<Result> results, JSONObject object) {
		Set<String> keySet = object.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			Result res = new Result("volte", key, object.getString(key));
			results.add(res);
		}

	}

	/**
	 * AS迭代JSONObject对象中属性为对象的情况 
	 * 
	 * @param results
	 * @param object
	 */
	public void interatorMult(List<Result> results, JSONObject object) {
		Set<String> keySet = object.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			// 判断是键值是否为对象
			if (CFU.equals(key)) {
				JSONObject jsonObj = (JSONObject) object.get(key);
				interator(results, jsonObj);
			}
			if (CFNR.equals(key)) {
				JSONObject jsonObj = (JSONObject) object.get(key);
				interator(results, jsonObj);
			}
			if (CFNRC.equals(key)) {
				JSONObject jsonObj = (JSONObject) object.get(key);
				interator(results, jsonObj);
			}
			if (CFB.equals(key)) {
				JSONObject jsonObj = (JSONObject) object.get(key);
				interator(results, jsonObj);
			}
			 if(EPS.equals(key)) {
             	JSONArray jsonArray=(JSONArray)object.get(key);
         		for(int i=0;i<jsonArray.size();i++){			
         			JSONObject one=(JSONObject)jsonArray.get(i);
         			interator(results,one);
         		}
             }
			 String paramkey=objectParamTranslateKey(key);
			 String value=objectParamTranslateValue(key,object.getString(key));
			 Result res = new Result("volte", paramkey, value);
			 results.add(res);
		}

	}
	/**
	 * HSS类型返回值转XML
	 * @param hssJson
	 * @return
	 */
	public String jsonToXmlOfHss(String hssJson) {
		//根节点对象
		Report reportArray=new Report();
		List<Result> reports=new ArrayList<Result>();
		//JSON转对象
		JSONObject object = (JSONObject) FastJsonUtil.json2Object(hssJson);
		JSONObject envlope=(JSONObject) object.get("Envelope");
		JSONObject body=(JSONObject) envlope.get("Body");
		JSONObject message=(JSONObject)body.get("签约信息");
		//获取HSS对象
		JSONObject hss=(JSONObject)message.get("HSS");
		//HSS标签下volte信息对象
		JSONObject volte=(JSONObject)hss.get("volte信息");
		//HSS标签下签约信息对象
		JSONObject sign=(JSONObject)hss.get("签约信息");
		//HSS标签下动态信息对象
		JSONObject dynamic=(JSONObject)hss.get("动态信息");
        /***************************华丽的分割线(hss标签下volte信息)*************************/		
		interator(reports,volte);
        /***************************华丽的分割线(hss标签下签约信息)**************************/	
		interator(reports,sign);
        /***************************华丽的分割线(hss标签下动态信息)**************************/	
		//EPS动态APN信息数组对象
		//迭代动态信息属性 如果是EPS则遍历以下对象
		interatorMult(reports,dynamic);
        /***************************华丽的分割线(增加对象到列表中)*******************************************/	
        //增加result对象到reportArray数组中
		reportArray.setResult(reports);
		//List对象转XML
		String s=XmlJsonParseUtil.object2XmlOfVolte(reportArray);
		return s;
	}
    /**
     * CSCF JSON转XML
     * @param cscfJson
     * @return
     */
	public String jsonToXmlOfCSCF(String cscfJson) {
		//根节点
		Report reportArray=new Report();
		List<Result> reports=new ArrayList<Result>();
		//JSON转对象
		JSONObject object = (JSONObject) FastJsonUtil.json2Object(cscfJson);
		JSONObject envlope=(JSONObject) object.get("Envelope");
		JSONObject body=(JSONObject) envlope.get("Body");
		JSONObject message=(JSONObject)body.get("签约信息");								
		//HSS标签下CSCF对象
		JSONObject cscf=(JSONObject)message.get("CSCF");
        /***************************华丽的分割线(cscf标签下dynamic信息)**************************/		
		//对象数组
		JSONArray jsonArraydynamic=cscf.getJSONArray("dynamic");
				for(int i=0;i<jsonArraydynamic.size();i++){			
					JSONObject one=(JSONObject)jsonArraydynamic.get(i);
					interator(reports,one);
				}
        /***************************华丽的分割线(cscf标签下static信息)**************************/	
				//对象数组
		JSONArray jsonArraystatic=cscf.getJSONArray("static");
				for(int i=0;i<jsonArraystatic.size();i++){			
					JSONObject one=(JSONObject)jsonArraystatic.get(i);
					interator(reports,one);
				}
        /***************************华丽的分割线**********************************/	
        //增加信息到列表中
		reportArray.setResult(reports);
		//List对象转XML
		return XmlJsonParseUtil.object2XmlOfVolte(reportArray);		
	}
	/**
	 * 获取enumdns
	 * @param enumdnsJson
	 * @return
	 */
	public String jsonToXmlOfENUMDNS(String enumdnsJson) {
		//根节点
		Report reportArray=new Report();
		List<Result> reports=new ArrayList<Result>();
		//JSON转对象
		JSONObject object = (JSONObject) FastJsonUtil.json2Object(enumdnsJson);
		JSONObject envlope=(JSONObject) object.get("Envelope");
		JSONObject body=(JSONObject) envlope.get("Body");
		JSONObject message=(JSONObject)body.get("签约信息");								
		//HSS标签下CSCF对象
		JSONObject enumdns=(JSONObject)message.get("ENUMDNS");
/***************************华丽的分割线(cscf标签下static信息)**************************/		
		 //对象数组
		JSONArray jsonArrayEnumdns=enumdns.getJSONArray("static");
		if(jsonArrayEnumdns.size()>0) {
				for(int i=0;i<jsonArrayEnumdns.size();i++){			
					JSONObject one=(JSONObject)jsonArrayEnumdns.get(i);
					interator(reports,one);
				}
		}else {
			reports.add(new Result("cscf","不存在该信息","0"));
		}
/***************************华丽的分割线**********************************/	
        //增加信息到列表中
		reportArray.setResult(reports);
		//List对象转XML
		return XmlJsonParseUtil.object2XmlOfVolte(reportArray);	
	}
	/**
	 * 
	 * @param asJson
	 * @return
	 */
	public String jsonToXmlOfAS(String asJson) {
		//根节点
		Report reportArray=new Report();
		List<Result> reports=new ArrayList<Result>();
		//JSON转对象
		JSONObject object = (JSONObject) FastJsonUtil.json2Object(asJson);
		JSONObject envlope=(JSONObject) object.get("Envelope");
		JSONObject body=(JSONObject) envlope.get("Body");
		JSONObject message=(JSONObject)body.get("签约信息");
		//获取AS对象
		JSONObject as=(JSONObject)message.get("AS");
        //判断是否有节点值
        if(as.size()>0) {
        	interatorMult(reports,as);
        }
      /***************************华丽的分割线**********************************/	
        //增加hss对象到hssArray数组中
		reportArray.setResult(reports);
		//List对象转xml
		return XmlJsonParseUtil.object2XmlOfVolte(reportArray);
	}
	//替换AS标签下编码key含义
   private String objectParamTranslateKey(String key) {
	 //新建map集合用来存放对应关系
	  Map<String,String> map=getMapData();
	  String keys=map.get(key);
	  if(keys!=null) {
		  return keys; 
	  }
	  return key;	
	}
 //替换AS标签下编码value含义
   private String objectParamTranslateValue(String key,String value) {
	   if(value.equals("1")) {
			 value="是";
		 }else {
			 value="否";
		 }
	 if(key.equals("m:CSREROUTE")) {
		 if(value.equals("1")) {
			 value="是";
		 }else {
			 value="否";
		 }
	 }
	 if(key.equals("m:US")) {
		 if(value.equals("0")) {
			 value="用户可以正常呼出呼入";
		 }else if(value.equals("1")){
			 value="用户只允许呼入，不允许呼出";
		 }else if(value.equals("2")){
			 value="用户只允许呼出，不允许呼入";
		 }else {
			 value="用户既不允许呼出，也不允许呼入";
		 }
	 }
	 if(key.equals("m:CHT")) {
		 if(value.equals("0")) {
			 value="离线计费用户";
		 }else {
			 value="在线计费用户";
		 }
	 }
	 if(key.equals("m:ODBBICTYPE") ||key.equals("m:ODBBOCTYPE")) {
		 if(value.equals("0")) {
			 value="不限制呼出";
		 }else if(value.equals("1")){
			 value="限制所有呼出";
		 }else {
			 value="限制国际呼出";
		 }
	 }
	 if(key.contains("m:NS")||key.equals("m:MASTERMULTIDEV")||key.equals("m:SLAVEMULTIDEV")) {
		 if(value.equals("0")) {
			 value="无业务权限";
		 }else {
			 value="有业务权限";
		 }
	 }
	
	 return value;
		
	}
  /**
   * 初始化map集合数据保存在内存由于此信息变化非常小 因此将数据硬编码在代码中
   * @return
   */
   public Map<String,String> getMapData(){
	   Map<String,String> map = null;
	   if(map==null) {
		   map=new HashMap<String,String>();
		   map.put("MessageID", "消息标识");
		   map.put("m:CHT", "计费类别");
		   map.put("m:US", "用户状态");
		   map.put("m:USERTYPE", "用户类型");
		   map.put("m:CSREROUTE", "CS域重路由");
		   map.put("m:IMPU", "用户公有标识");
		   map.put("m:ResultCode", "响应码");
		   map.put("m:DSP_BAIC", "限制所有呼入业务激活状态");
		   map.put("m:DSP_BAOC", "限制所有呼出业务激活状态");
		   map.put("m:DSP_BICROM", "限制漫游出归属国呼入业务激活状态");
		   map.put("m:DSP_BOIC", "限制所有国际呼出业务激活状态");
		   map.put("m:DSP_BOICEXHC", "限制所有除归属国的国际呼出业务激活状态");
		   map.put("m:DSP_CFB", "遇用户忙呼叫前转业务激活状态");
		   map.put("m:DSP_CFD", "默认呼叫前转业务激活状态");
		   map.put("m:DSP_CFNL", "未注册呼叫前转业务激活状态");
		   map.put("m:DSP_CFNR", "无应答呼叫前转业务激活状态");
		   map.put("m:DSP_CFNRC", "不可及呼叫前转业务激活状态");
		   map.put("m:DSP_CFU", "无条件呼叫前转业务状态");
		   map.put("m:DSP_GOIR", "主叫号码显示限制业务激活状态");
		   map.put("m:NSGOIR"	,	"主叫标识显示限制");
		   map.put("m:M,TERMULTIDEV"	,	"主卡一号多终端");
		   map.put("m:SLAVEMULTIDEV"	,	"副卡一号多终端");
		   map.put("m:NSCFD"	,	"默认前转");
		   map.put("m:NSCFU"	,	"无条件呼叫前转");
		   map.put("m:NSCFNR"	,	"无应答前转");
		   map.put("m:NSCFB"	,	"遇忙前转");
		   map.put("m:NSCFNRC"	,	"不可及前转");
		   map.put("m:NSCFNL"	,	"未注册前转");
		   map.put("m:NSDDS"	,	"免打扰业务");
		   map.put("m:NSCW"	    ,	"呼叫等待");
		   map.put("m:NSCCBS"	,	"遇忙回叫");
		   map.put("m:NSBAOC"	,	"限制所有呼出业务权限");
		   map.put("m:NSBOIC"	,	"限制所有国际呼出业务权限");
		   map.put("m:NSBOICEXHC"	,	"限制所有除归属国的国际呼出业务权限");
		   map.put("m:NSBAIC"	,	"限制所有呼入业务权限");
		   map.put("m:NSBICROM"	,	"漫游出归属国限制呼入业务权限");
		   map.put("m:NSMPTY"	,	"多方通话");
		   map.put("m:NSACS"	,	"闹钟业务");
		   map.put("m:ODBENTAIN"	,	"运营商决定闭锁娱乐台呼叫");
		   map.put("m:NSOIP"	,	"主叫标识显示");
		   map.put("m:ODBINFORM"	,	"运营商决定闭锁信息台呼叫");
		   map.put("m:ODBBOCTYPE"	,	"运营商决定闭锁呼出类型");
		   map.put("m:ODBBICTYPE"	,	"运营商决定闭锁呼入类型");
		   map.put("m:ODBSS"	,	"运营商决定闭锁补充业务");
	   }
	
	  return map;
   }
//解析接口入参数据和调用接口
	public String parseParamAndGetDataInfo(String xml) {
		InterFace inter = iPortDao.getPortById(201804);
		Element rootElt = null;
		String retStr = null;
		try {
			rootElt = parseXML(xml);
			String busivalue = rootElt.elementTextTrim("busivalue");
			String type = rootElt.elementTextTrim("type");
			String sender = rootElt.elementTextTrim("sender");
			String transid = rootElt.elementTextTrim("transid");
			String servcode = rootElt.elementTextTrim("servcode");
			String msgid = rootElt.elementTextTrim("msgid");
			String version = rootElt.elementTextTrim("version");
			String time = DateFormatUtil.getFormatDateMill();
			/*
			 * 2018.7.26 14:58 此处拼接报文为了提高接口查询速度 故而没有保存在数据库， 将所有变量抽离出来防止以后有变,如果报文有变修改以下报文即可
			 */
			
			String reqStr="{\"Envelope\":{\"Header\":{\"Esb\":{\"Router\":{\"Sender\":\"" +sender+"\",\"AuthCode\":\"\",\"Time\":\"" + time + "\",\"ServTestFlag\":\"\",\"CarryType\":\"\",\"TransId\":\"" + transid + "\",\"MsgId\":\"" + msgid + "\",\"MsgType\":\"\",\"EsbId\":\"\",\"AuthType\":\"\",\"ServCode\":\"" + servcode +"\",\"Version\":\"" + version + "\"}}},\"Body\":{\"mdn\":\"" + busivalue +"\",\"type\":\"" + type + "\"}}}";
			long startTime = System.currentTimeMillis();
			retStr = HttpClientUtil.execute(inter.getUrl(), reqStr, inter.getType(), inter.getTimeout());
			this.logger.info("volte信息查询：号码：{},开始时间：{},结束时间：{},请求报文：{},返回报文：{},调用时间:{},接口编号:{}", new Object[] { busivalue, startTime, System.currentTimeMillis(),reqStr,retStr,(System.currentTimeMillis() - startTime),"201804"});

			if (retStr == "" || "".equals(retStr)) {
				retStr = "<response><code>-1<code><msg>返回值为空<msg></response>";

			}else if(retStr.contains("Fault")){
				retStr = "<response><code>-1<code><msg>其他异常<msg></response>";

			}else {
				if (HSS.equals(type)) {
					retStr=	jsonToXmlOfHss(retStr);
				} else if (AS.equals(type)) {
					retStr=	jsonToXmlOfAS(retStr);
				} else if (CSCF.equals(type)) {
					retStr=	jsonToXmlOfCSCF(retStr);
				} else if (ENUMDNS.equals(type)) {
					retStr=	jsonToXmlOfENUMDNS(retStr);
				}

			}
		} catch (DocumentException e) {
			retStr = "<response><code>-1<code><msg>入参格式非法<msg></response>";
		}
		return retStr;
	}
	
}