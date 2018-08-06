package com.vitea.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vitea.dto.Report;
import com.vitea.dto.Result;
/**
 * json转xml
 * @author liushahe
 *
 */
public class FastJsonTest {
	/**
	 * 迭代JSONObject对象属性 返回列表
	 * @param results
	 * @param object
	 */
	public   void interator(List<Result> results,JSONObject object) {
		Set<String> keySet=object.keySet();
		Iterator<String> iterator=keySet.iterator();
		while(iterator.hasNext()){
			String key=iterator.next();
			Result res=new Result("hss",key,object.getString(key));
			results.add(res);
		}

	}
	/**
	 * AS迭代JSONObject对象+属性 返回列表
	 * @param results
	 * @param object
	 */
		public  void interatorMult(List<Result> results,JSONObject object) {
			Set<String> keySet=object.keySet();
			Iterator<String> iterator=keySet.iterator();
			while(iterator.hasNext()){
				String key=iterator.next();
				//判断是键值是否为对象
				if("m:DSP_CFU".equals(key)) {
					JSONObject jsonObj=(JSONObject)object.get(key);
					interator(results,jsonObj);
				}
                if("m:DSP_CFNR".equals(key)) {
                	JSONObject jsonObj=(JSONObject)object.get(key);
					interator(results,jsonObj);
				}
                if("m:DSP_CFNRC".equals(key)) {
                	JSONObject jsonObj=(JSONObject)object.get(key);
					interator(results,jsonObj);
				}
                if("m:DSP_CFB".equals(key)) {
                	JSONObject jsonObj=(JSONObject)object.get(key);
					interator(results,jsonObj);
				}
                if("EPS动态APN信息".equals(key)) {
                	JSONArray jsonArray=(JSONArray)object.get(key);
            		System.out.println(jsonArray.size());
            		for(int i=0;i<jsonArray.size();i++){			
            			JSONObject one=(JSONObject)jsonArray.get(i);
            			interator(results,one);
            		}
                }
				Result res=new Result("hss",key,object.getString(key));
				results.add(res);
			}

		}
	@Test
	public void jsonToObjectOfHss() {
		//根节点
		Report reportArray=new Report();
		List<Result> results=new ArrayList<Result>();
		String str = "{\"Envelope\":{\"Body\":{\"签约信息\":{\"HSS\":{\"漫游省\":\"\",\"volte信息\":{\"用户漫游限制\":[\"\"],\"是否VoLTE用户\":\"否\",\"SIFC模板标识列表\":[\"\"],\"ResultCode\":\"13005\",\"ResultDescr\":\"ASSOCIATION NOT DEFINED\"},\"签约信息\":{\"全局计费特性\":\"2\",\"IMEI\":\"8638390202192700\",\"漫游权限\":\"无\",\"用户最大下行带宽\":\"100Mbps\",\"EPS模板号\":\"97\",\"用户最大上行带宽\":\"40Mbps\"},\"动态信息\":{\"APN-OI\":\"\",\"默认承载APN\":\"\",\"MME实体\":\"\",\"EPS位置更新时间\":\"20180423T064017284\",\"EPS本地状态\":\"UNKNOWN\",\"用户状态\":\"\"},\"查询状态\":0,\"异常原因\":\"\"}}},\"Header\":{\"Esb\":{\"Router\":{\"EsbId\":\"EsbServer_GAS_51_6212-1533196290531\",\"Time\":\"2018-08-02 15:51:33.001\"}}}}}";
		//JSON转对象
		JSONObject object = (JSONObject) FastJsonUtil.json2Object(str);
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
/***************************华丽的分割线(hss标签下volte信息)**************************/		
		interator(results,volte);
/***************************华丽的分割线(hss标签下签约信息)**************************/	
		interator(results,sign);

/***************************华丽的分割线(hss标签下动态信息)**************************/	
		//对象数组
        	interatorMult(results,dynamic);
/***************************华丽的分割线**********************************/	
        //增加hss对象到hssArray数组中
		reportArray.setResult(results);
		//List对象转xml
		String s=XmlJsonParseUtil.object2XmlOfVolte(reportArray);
		System.out.println(s);
	}
	
	@Test
	public void jsonToObjectOfCSCF() {
		//根节点
		Report reportArray=new Report();
		List<Result> results=new ArrayList<Result>();
		String str = "{\"Envelope\":{\"Body\":{\"签约信息\":{\"ALL\":{\"CSCF\":{\"dynamic\":[{\"key\":\"注册MMTEL\"},{\"key\":\"注册SCSCF\"},{\"key\":\"注册PSBC\"}],\"static\":[{\"key\":\"SCSCF用户IFC信息\"}]},\"AS\":{},\"HSS\":{\"漫游省\":\"\",\"volte信息\":{\"用户漫游限制\":[\"\"],\"是否VoLTE用户\":\"否\",\"SIFC模板标识列表\":[\"\"],\"ResultCode\":\"13005\",\"ResultDescr\":\"ASSOCIATION NOT DEFINED\"},\"签约信息\":{\"全局计费特性\":\"8\",\"IMEI\":\"8673680397237227\",\"用户最大下行带宽\":\"100Mbps\",\"漫游权限\":\"无\",\"EPS模板号\":\"100\",\"用户最大上行带宽\":\"40Mbps\"},\"动态信息\":{\"EPS动态APN信息\":[{\"PDN网关实体 \":\"topoff.pgw-s2a.gw02-B-er.lz.gs.node.epc.mnc011.mcc460.3gppnetwork.org\",\"APN\":\"ctnet\",\"PDN网关域\":\"epc.mnc011.mcc460.3gppnetwork.org\"},{\"PDN网关实体 \":\"topoff.pgw-s5s8.gw02-b-er.lz.gs.node.epc.mnc011.mcc460.3gppnetwork.org\",\"APN\":\"ctlte\",\"PDN网关域\":\"epc.mnc011.mcc460.3gppnetwork.org\"}],\"默认承载APN\":\"\",\"APN-OI\":\"\",\"MME实体\":\"mmec03.mmegi6200.mme.epc.mnc011.mcc460.3gppnetwork.org\",\"EPS位置更新时间\":\"20180727T100725824\",\"EPS本地状态\":\"LOCATED\",\"用户状态\":\"NOT_REGISTERED\"},\"查询状态\":0,\"异常原因\":\"\"},\"ENUMDNS\":{\"static\":[]}}}},\"Header\":{\"Esb\":{\"Router\":{\"EsbId\":\"EsbServer_GAS_51_6212-1533110220599\",\"Time\":\"2018-08-01 15:57:05.001\"}}}}}\r\n"
				+ "";
		//JSON转对象
		JSONObject object = (JSONObject) FastJsonUtil.json2Object(str);
		JSONObject envlope=(JSONObject) object.get("Envelope");
		JSONObject body=(JSONObject) envlope.get("Body");
		JSONObject message=(JSONObject)body.get("签约信息");
		//获取All对象
		JSONObject all=(JSONObject)message.get("ALL");								
		//HSS标签下CSCF对象
		JSONObject cscf=(JSONObject)all.get("CSCF");
/***************************华丽的分割线(cscf标签下dynamic信息)**************************/		
		  //对象数组
		JSONArray jsonArraydynamic=cscf.getJSONArray("dynamic");
				for(int i=0;i<jsonArraydynamic.size();i++){			
					JSONObject one=(JSONObject)jsonArraydynamic.get(i);
					interator(results,one);
				}
/***************************华丽的分割线(cscf标签下static信息)**************************/	
				//对象数组
		JSONArray jsonArraystatic=cscf.getJSONArray("dynamic");
				for(int i=0;i<jsonArraystatic.size();i++){			
					JSONObject one=(JSONObject)jsonArraystatic.get(i);
					interator(results,one);
				}
/***************************华丽的分割线**********************************/	
        //增加信息到列表中
		reportArray.setResult(results);
		//List对象转xml
		String s=XmlJsonParseUtil.object2XmlOfVolte(reportArray);
		System.out.println(s);
	}
	@Test
	public void jsonToObjectOfENUMDNS() {
		//根节点
		Report reportArray=new Report();
		List<Result> results=new ArrayList<Result>();
		String str = "{\"Envelope\":{\"Body\":{\"签约信息\":{\"ALL\":{\"CSCF\":{\"dynamic\":[{\"key\":\"注册MMTEL\"},{\"key\":\"注册SCSCF\"},{\"key\":\"注册PSBC\"}],\"static\":[{\"key\":\"SCSCF用户IFC信息\"}]},\"AS\":{},\"HSS\":{\"漫游省\":\"\",\"volte信息\":{\"用户漫游限制\":[\"\"],\"是否VoLTE用户\":\"否\",\"SIFC模板标识列表\":[\"\"],\"ResultCode\":\"13005\",\"ResultDescr\":\"ASSOCIATION NOT DEFINED\"},\"签约信息\":{\"全局计费特性\":\"8\",\"IMEI\":\"8673680397237227\",\"用户最大下行带宽\":\"100Mbps\",\"漫游权限\":\"无\",\"EPS模板号\":\"100\",\"用户最大上行带宽\":\"40Mbps\"},\"动态信息\":{\"EPS动态APN信息\":[{\"PDN网关实体 \":\"topoff.pgw-s2a.gw02-B-er.lz.gs.node.epc.mnc011.mcc460.3gppnetwork.org\",\"APN\":\"ctnet\",\"PDN网关域\":\"epc.mnc011.mcc460.3gppnetwork.org\"},{\"PDN网关实体 \":\"topoff.pgw-s5s8.gw02-b-er.lz.gs.node.epc.mnc011.mcc460.3gppnetwork.org\",\"APN\":\"ctlte\",\"PDN网关域\":\"epc.mnc011.mcc460.3gppnetwork.org\"}],\"默认承载APN\":\"\",\"APN-OI\":\"\",\"MME实体\":\"mmec03.mmegi6200.mme.epc.mnc011.mcc460.3gppnetwork.org\",\"EPS位置更新时间\":\"20180727T100725824\",\"EPS本地状态\":\"LOCATED\",\"用户状态\":\"NOT_REGISTERED\"},\"查询状态\":0,\"异常原因\":\"\"},\"ENUMDNS\":{\"static\":[]}}}},\"Header\":{\"Esb\":{\"Router\":{\"EsbId\":\"EsbServer_GAS_51_6212-1533110220599\",\"Time\":\"2018-08-01 15:57:05.001\"}}}}}\r\n"
				+ "";
		//JSON转对象
		JSONObject object = (JSONObject) FastJsonUtil.json2Object(str);
		JSONObject envlope=(JSONObject) object.get("Envelope");
		JSONObject body=(JSONObject) envlope.get("Body");
		JSONObject message=(JSONObject)body.get("签约信息");
		//获取All对象
		JSONObject all=(JSONObject)message.get("ALL");								
		//HSS标签下CSCF对象
		JSONObject enumdns=(JSONObject)all.get("ENUMDNS");
/***************************华丽的分割线(cscf标签下static信息)**************************/		
		 //对象数组
		JSONArray jsonArrayEnumdns=enumdns.getJSONArray("static");
		if(jsonArrayEnumdns.size()>0) {
				for(int i=0;i<jsonArrayEnumdns.size();i++){			
					JSONObject one=(JSONObject)jsonArrayEnumdns.get(i);
					interator(results,one);
				}
		}else {
			  results.add(new Result("cscf","不存在该信息","0"));
		}
/***************************华丽的分割线**********************************/	
        //增加信息到列表中
		reportArray.setResult(results);
		//List对象转xml
		String s=XmlJsonParseUtil.object2XmlOfVolte(reportArray);
		System.out.println(s);
	}
	@Test
	public void jsonToObjectOfAs() {
		//根节点
		Report reportArray=new Report();
		List<Result> results=new ArrayList<Result>();
		String str = "{\"Envelope\":{\"Body\":{\"签约信息\":{\"AS\":{\"m:SLAVEMULTIDEV\":\"0\",\"m:NSMPTY\":\"1\",\"m:NSBAOC\":\"0\",\"m:CHT\":\"0\",\"m:USERTYPE\":\"0\",\"m:ODBINFORM\":\"0\",\"m:NSCFD\":\"0\",\"m:NSACS\":\"0\",\"m:DSP_CFU\":{\"MessageID\":\"VolteUserQuery1533177762105\",\"m:ResultDesc\":\"网元命令执行失败，详细信息：“ Result/ResultCode=22063; Result/ResultDesc=ATS-指定路径对应的数据不存在。”。\",\"m:ResultCode\":\"116\"},\"m:NSCFB\":\"1\",\"m:DSP_CFNR\":{\"MessageID\":\"VolteUserQuery1533177762105\",\"m:ResultDesc\":\"网元命令执行失败，详细信息：“ Result/ResultCode=22063; Result/ResultDesc=ATS-指定路径对应的数据不存在。”。\",\"m:ResultCode\":\"116\"},\"m:NSTIR\":\"0\",\"m:ODBBICTYPE\":\"0\",\"m:NSTIP\":\"0\",\"m:NSBAIC\":\"0\",\"m:NSCFNR\":\"1\",\"m:NSBICROM\":\"0\",\"m:NSOIP\":\"1\",\"m:NSBOICEXHC\":\"0\",\"m:ODBSS\":\"0\",\"m:NSCFNL\":\"0\",\"m:NSBOIC\":\"0\",\"m:NSCFU\":\"1\",\"m:PROVINCEID\":\"27\",\"m:NSDDS\":\"0\",\"m:CSREROUTERESTRICTION\":\"0\",\"m:DSP_CFNRC\":{\"MessageID\":\"VolteUserQuery1533177762105\",\"m:ResultDesc\":\"网元命令执行失败，详细信息：“ Result/ResultCode=22063; Result/ResultDesc=ATS-指定路径对应的数据不存在。”。\",\"m:ResultCode\":\"116\"},\"m:IMPU\":\"sip:+8615309307510@gs.ims.mnc011.mcc460.3gppnetwork.org\",\"m:ResultCode\":\"0\",\"m:MASTERMULTIDEV\":\"0\",\"m:CSREROUTE\":\"0\",\"m:NSCCBS\":\"0\",\"m:US\":\"0\",\"MessageID\":\"VolteUserQuery1533177761398\",\"m:NSCW\":\"1\",\"m:ResultDesc\":\"操作成功。\",\"m:ODBBOCTYPE\":\"0\",\"m:DSP_CFB\":{\"MessageID\":\"VolteUserQuery1533177762105\",\"m:ResultDesc\":\"网元命令执行失败，详细信息：“ Result/ResultCode=22063; Result/ResultDesc=ATS-指定路径对应的数据不存在。”。\",\"m:ResultCode\":\"116\"},\"m:ODBENTAIN\":\"0\",\"m:NSGOIR\":\"0\",\"m:NSCFNRC\":\"1\"}}},\"Header\":{\"Esb\":{\"Router\":{\"EsbId\":\"EsbServer_GAS_51_6212-1533177757354\",\"Time\":\"2018-08-02 10:42:41.001\"}}}}}";
		//JSON转对象
		JSONObject object = (JSONObject) FastJsonUtil.json2Object(str);
		JSONObject envlope=(JSONObject) object.get("Envelope");
		JSONObject body=(JSONObject) envlope.get("Body");
		JSONObject message=(JSONObject)body.get("签约信息");
		//获取AS对象
		JSONObject as=(JSONObject)message.get("AS");
        //判断是否有节点值
        if(as.size()>0) {
        	interatorMult(results,as);
        }
/***************************华丽的分割线**********************************/	
        //增加hss对象到hssArray数组中
		reportArray.setResult(results);
		//List对象转xml
		String s=XmlJsonParseUtil.object2XmlOfVolte(reportArray);
		System.out.println(s);
	}
}
