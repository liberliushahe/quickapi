package com.vitea.endpoint.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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


					retStr=	jsonToXmlOfHss(retStr);

					//retStr=	jsonToXmlOfAS(retStr);

					//retStr=	jsonToXmlOfCSCF(retStr);

					//retStr=	jsonToXmlOfENUMDNS(retStr);

			}
		} catch (DocumentException e) {
			retStr = "<response><code>-1<code><msg>入参格式非法<msg></response>";
		}
		return retStr;
	}
	@Override
	public String platformQryVolteCsCf(String xml) {
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
				retStr=	jsonToXmlOfCSCF(retStr);
			}
		} catch (DocumentException e) {
			retStr = "<response><code>-1<code><msg>入参格式非法<msg></response>";
		}
		return retStr;
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
			Result res = new Result("volte", key, object.getString(key));
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
		List<Object> platformQryReturn=new ArrayList<Object>();
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
		platformQryReturn.add(reportArray);
		//List对象转XML
		String s=XmlJsonParseUtil.object2XmlOfVolte(platformQryReturn);
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
		List<Object> platformQryReturn=new ArrayList<Object>();
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
		platformQryReturn.add(reportArray);
		//List对象转XML
		return XmlJsonParseUtil.object2XmlOfVolte(platformQryReturn);		
	}
	/**
	 * 获取enumdns
	 * @param enumdnsJson
	 * @return
	 */
	public String jsonToXmlOfENUMDNS(String enumdnsJson) {
		//根节点
		Report reportArray=new Report();
		List<Object> platformQryReturn=new ArrayList<Object>();

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
		platformQryReturn.add(reportArray);
		//List对象转XML
		return XmlJsonParseUtil.object2XmlOfVolte(platformQryReturn);	
	}
	/**
	 * 
	 * @param asJson
	 * @return
	 */
	public String jsonToXmlOfAS(String asJson) {
		//根节点
		Report reportArray=new Report();
		List<Object> platformQryReturn=new ArrayList<Object>();

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
		platformQryReturn.add(reportArray);
		//List对象转xml
		return XmlJsonParseUtil.object2XmlOfVolte(platformQryReturn);
	}
	@Override
	public String platformQryVolteAs(String xml) {
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
					retStr=	jsonToXmlOfAS(retStr);
			}
		} catch (DocumentException e) {
			retStr = "<response><code>-1<code><msg>入参格式非法<msg></response>";
		}
		return retStr;
	}
	@Override
	public String platformQryVolteEnumDns(String xml) {
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
				retStr=	jsonToXmlOfENUMDNS(retStr);

			}
		} catch (DocumentException e) {
			retStr = "<response><code>-1<code><msg>入参格式非法<msg></response>";
		}
		return retStr;
	}

	
}