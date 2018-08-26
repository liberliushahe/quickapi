package com.vitea.endpoint.service.impl;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.vitea.dao.InterfaceDao;
import com.vitea.domain.InterFace;
import com.vitea.endpoint.dto.RequestParams;
import com.vitea.endpoint.service.ICrmInfoQuery;
import com.vitea.util.XmlJsonParseUtil;
/**
 * ICrmBusiness实现类
 * @author liushahe
* 此接口主要负责将核心接口转为webservice服务返回xml格式数据
 * 1.查询客户信息--------qryCustInfo()
 * 2.查询用户信息--------qryUserInfo()
 * 3.查询用户订购的套餐信息
 * 4.查询用户订购主产品信息
 * 5.查询用户订购的附属产品信息
 * 6.查询分局信息
 * 7.查询账号状态
 * 8.套餐详情查询
 * 9.订单查询
 * 10.先装后付查询
 * 11.用户套餐是否可结转详情
 * 12.主副卡查询
 * 13.手机参数查询
 * 14.MSCI信息查询
 * 15.积分变更日志查询
 * 16.CRM基本信息查询
 * 17.装机单进度查询
 * 18.客户产品信息查询
 * 19.用户套餐变更记录查询
 * 20.亲情号码查询
 * 21.积分查询
 * 22.信用额度查询
 * 23.积分来源查询
 * 24.积分兑换记录（按账期）
 * 接口入参,通过Code接口编码来决定具体调用哪个接口
 * <pre>
 * <![CDATA[
   <head>
     <ExchangeId>jxwskf201303150933359110640112</ExchangeId>
     <BizCode>Query</BizCode>
     <ClientId>jxwskf</ClientId>
     <Password>jxwskf</Password>
     <Code>INF_ObsPreQuery</Code>
   </head>
   <body>
     <accnum>18919817223</accnum>
     <lanid>931</lanid>
     <productid>1</productid>
     <definedid>INF_UserInfo_Query</definedid>
   </body>
    ]]>
   </pre>
  */
public class CrmInfoQueryImpl implements ICrmInfoQuery{
	@Autowired
	private InterfaceDao iPortDao;
	private Logger logger = LoggerFactory.getLogger("MONGODB");
	private static String INT_CUST="INF_CustInfo_Query";
	private static String INT_USER="INF_UserInfo_Query";
	private static String INT_PKGS="INF_UserPackages_Query";
	private static String INT_MAIN="INF_MainProduct_Query";
	private static String INT_AUX="INF_AuxProduct_Query";
	private static String INT_INSTALL="INF_InstallPay_Query";
	/**
	 * 拼接请求报文并请求数据返回XML格式数据
	 * 此类主要处理客户相关信息 原有接口为1.0版本 新版本3.0接口参数变化较大 ，故单独处理
	 * @param inter
	 * @param params
	 * @return
	 */
	public String requestCrmBusinessDataAndFormat(InterFace inter,RequestParams params) {
		String retStr=null;
		String reqStr = "{\"Root\":{\"Header\":{\"ExchangeId\":\""+params.getExChangeId()+"\",\"BizCode\":\""+params.getBizCode()+"\",\"ClientId\":\""+params.getClientId()+"\",\"Password\":\""+params.getPassword()+"\"},\"Query\":{\"Code\":\" "+params.getCode()+" \",\"QueryParam\":{\"acc_num\":\""+params.getAccNum()+"\",\"lan_id\":\""+params.getLanId()+"\",\"para_type\":\""+params.getProductId()+"\"}}}}";
		
		//判断接口编码 此处为临时代码 后期生产环境删除即可
		if(INT_CUST.equals(params.getDefinedId())) {
		    retStr="{\"Message\":\"成功\",\"Code\":\"0000\",\"ExchangeId\":\" fwqzjk201806111006586681522662\",\"QueryResults\":{\"Cust\":[{\"custBrand\":\"我的E家\",\"linkPhone\":\"100\",\"cardType\":\"户口本/居住证\",\"accountManger\":\"\",\"custName\":\"平凉电信分公司\",\"custLevel\":\"312\",\"cardNo\":\"13321306888\",\"areaCode\":\"0931\",\"vipName\":\"钻\",\"cardId\":\"2BI\",\"custId\":\"3432432423342\",\"accMngContactNo\":\"\",\"channelTypeId\":\"2\",\"channelTypeName\":\"家庭客户\",\"vipCode\":\"4\",\"sumUsableScore\":\"\"}]}}";
		}else if(INT_USER.equals(params.getDefinedId())) {
		    retStr="{\"Message\":\"成功\",\"Code\":\"0000\",\"ExchangeId\":\" fwqzjk201806111006586681522672\",\"QueryResults\":{\"User\":[{\"areaCode\":\"0937\",\"areaName\":\"酒泉市本地网\",\"openTime\":\"2014-03-02 15:41:15\",\"userType\":\"住宅用户\",\"installAddr\":\"瓜州县三道沟镇三道沟村八组\",\"prdInstState\":\"0\",\"prdInstStateName\":\"在用\",\"payType\":\"现金\",\"userName\":\"马晓燕\",\"availableScore\":\"3960\",\"isPrePay\":\"2\",\"payName\":\"预付费\",\"prdInstId\":\"937417998393\",\"serviceNbr\":\"13321371899\",\"accId\":\"937223553607\",\"feeType\":\"1201\",\"g_imsi\":\"204043146846022\",\"iccid\":\"89860314109372773128\",\"prdCode\":\"900000001\",\"prdName\":\"移动电话\",\"expDate\":\"20300101 00:00:00\",\"certiType\":\"身份证\",\"certiNumber\":\"622126197402120629\"}]}}";
		}else if(INT_PKGS.equals(params.getDefinedId())) {
			retStr="{\"Message\":\"成功\",\"Code\":\"0000\",\"ExchangeId\":\" fwqzjk201806111006586681522682\",\"QueryResults\":{\"OfferList\":[{\"pkg\":[{\"pkgName\":\"乐享3G易通卡（全能版）-129元\",\"pkgID\":\"11107745\",\"pkgGrade\":\"129\",\"GroupId\":\"129\",\"pkgInstID\":\"200076267\",\"ProdOfferName\":\"乐享3G易通卡（全能版）-129元\",\"ProdOfferId\":\"11107745\",\"effectiveTime\":\"2012-07-01 00:00:00\",\"expireTime\":\"2030-01-01 00:00:00\",\"pkgDetails\":{\"pkgDetail\":{}}},{\"pkgName\":\"乐享3G易通卡（全能版）-通话时长300分钟\",\"pkgID\":\"11107752\",\"pkgGrade\":\"0\",\"GroupId\":\"0\",\"pkgInstID\":\"200076268\",\"ProdOfferName\":\"乐享3G易通卡（全能版）-通话时长300分钟\",\"ProdOfferId\":\"11107752\",\"effectiveTime\":\"2012-07-01 00:00:00\",\"expireTime\":\"2030-01-01 00:00:00\",\"pkgDetails\":{\"pkgDetail\":{}}},{\"pkgName\":\"赠送30小时省内Wlan免费时长\",\"pkgID\":\"11105677\",\"pkgGrade\":\"0\",\"GroupId\":\"0\",\"pkgInstID\":\"15058747882\",\"ProdOfferName\":\"赠送30小时省内Wlan免费时长\",\"ProdOfferId\":\"11105677\",\"effectiveTime\":\"2010-02-01 00:00:00\",\"expireTime\":\"2010-06-30 23:59:59\",\"pkgDetails\":{\"pkgDetail\":{}}}]}]}}";
		}else if(INT_MAIN.equals(params.getDefinedId())) {
		    retStr="{\"Message\":\"成功\",\"Code\":\"0000\",\"ExchangeId\":\"HNBSS3201806111006586621624482\",\"QueryResults\":{\"ProdList\":[{\"prdCode\":\"80000045\",\"prdName\":\"移动电话\",\"prdcharacters\":{\"prdcharacter\":[{\"characterName\":\"是否预开通\",\"characterValue\":\"0\",\"characterValueName\":\"非预开通\",\"characterCode\":\"992036601\"},{\"characterName\":\"业务号码\",\"characterValue\":\"\",\"characterValueName\":\"\",\"characterCode\":\"990000017\"}]}}]}}";
		}else if(INT_AUX.equals(params.getDefinedId())) {
			retStr="{\"Message\":\"成功\",\"Code\":\"0000\",\"ExchangeId\":\"HNBSS3201806111006586622524482\",\"QueryResults\":{\"subProducts\":[{\"subProduct\":[{\"receiveTime\":\"receiveTime\",\"finishTime\":\"finishTime\",\"subPrdName\":\"subPrdName\",\"subPrdStateName\":\"subPrdStateName\",\"subPrdCharacters\":{\"subPrdCharacter\":[{\"characterName\":\"上行速率\",\"characterValue\":\"1100\",\"characterValueName\":\"5M\",\"characterCode\":\"800027684\"},{\"characterName\":\"下行速率\",\"characterValue\":\"1200\",\"characterValueName\":\"10M\",\"characterCode\":\"800027685\"},{\"characterName\":\"QOS等级\",\"characterValue\":\"1300\",\"characterValueName\":\"普通\",\"characterCode\":\"800027686\"}]},\"subPrdCode\":\"subPrdCode\",\"subPrdState\":\"subPrdState\"},{\"receiveTime\":\"receiveTime\",\"finishTime\":\"finishTime\",\"subPrdCode\":\"subPrdCode\",\"subPrdName\":\"subPrdName\",\"subPrdStateName\":\"subPrdStateName\",\"subPrdState\":\"subPrdState\"},{\"receiveTime\":\"receiveTime\",\"finishTime\":\"finishTime\",\"subPrdCode\":\"subPrdCode\",\"subPrdName\":\"subPrdName\",\"subPrdStateName\":\"subPrdStateName\",\"subPrdState\":\"subPrdState\"},{\"receiveTime\":\"receiveTime\",\"finishTime\":\"finishTime\",\"subPrdCode\":\"subPrdCode\",\"subPrdName\":\"subPrdName\",\"subPrdStateName\":\"subPrdStateName\",\"subPrdState\":\"subPrdState\"},{\"receiveTime\":\"receiveTime\",\"finishTime\":\"finishTime\",\"subPrdName\":\"subPrdName\",\"subPrdStateName\":\"subPrdStateName\",\"subPrdCharacters\":{\"subPrdCharacter\":[{\"characterName\":\"使用状态\",\"characterValue\":\"1\",\"characterValueName\":\"正常\",\"characterCode\":\"600002471\"}]},\"subPrdCode\":\"subPrdCode\",\"subPrdState\":\"subPrdState\"},{\"receiveTime\":\"receiveTime\",\"finishTime\":\"finishTime\",\"subPrdCode\":\"subPrdCode\",\"subPrdName\":\"subPrdName\",\"subPrdStateName\":\"subPrdStateName\",\"subPrdState\":\"subPrdState\"},{\"receiveTime\":\"receiveTime\",\"finishTime\":\"finishTime\",\"subPrdCode\":\"subPrdCode\",\"subPrdName\":\"subPrdName\",\"subPrdStateName\":\"subPrdStateName\",\"subPrdState\":\"subPrdState\"}]}]}}";
		}
		//long startTime = System.currentTimeMillis();
		//retStr = HttpClientUtil.execute(inter.getUrl(), reqStr,inter.getType(),inter.getTimeout());
	    //this.logger.info(""+inter.getName()+"：号码：{},开始时间：{},结束时间：{},请求报文：{},返回报文：{},调用时间:{},接口编号:{}", new Object[] { params.getAccNum(), startTime, System.currentTimeMillis(),reqStr,retStr,(System.currentTimeMillis() - startTime),inter.getId()});
		//格式化数据XML并返回
		return XmlJsonParseUtil.jsonToXml(retStr);
	}
	/**
	 * 拼接请求报文并请求数据返回XML格式数据
	 * 此类主要处理客户相关信息 原有接口为2.0版本 新版本3.0接口参数变化不大 ，故单独处理
	 * @param inter
	 * @param params
	 * @return
	 */
	public String requestCrmExchangeDataAndFormat(InterFace inter,RequestParams params) {
		String retStr=null;
		String reqStr = "{\"Root\":{\"Header\":{\"ExchangeId\":\""+params.getExChangeId()+"\",\"BizCode\":\""+params.getBizCode()+"\",\"ClientId\":\""+params.getClientId()+"\",\"Password\":\""+params.getPassword()+"\"},\"Query\":{\"Code\":\" "+params.getCode()+" \",\"QueryParam\":{\"acc_num\":\""+params.getAccNum()+"\",\"lan_id\":\""+params.getLanId()+"\",\"para_type\":\""+params.getProductId()+"\"}}}}";
		
		//判断接口编码 此处为临时代码 后期生产环境删除即可
		if(INT_INSTALL.equals(params.getDefinedId())) {
			retStr="{\"Message\":\"成功\",\"Code\":\"0000\",\"ExchangeId\":\" fwqzjk201806111006586681522682\",\"QueryResults\":{\"Result\":[{\"link_address\":\"中国电信\",\"address_desc\":\"兰州市城关区榆中街88号东岗房管所10层1001\",\"state\":\"00C\",\"staff_id\":\"1316666\",\"installed_phone\":\"18919818283\",\"id\":\"20160223226\",\"cust_name\":\"小王测试\",\"create_date\":\"2016-02-23 17:23:52\",\"order_time\":\"2016-02-23 10:53:07\",\"offer_idea\":\"1\",\"link_num\":\"18919887267\",\"lan_id\":\"931\",\"ns_order_no\":\"931131666616030114626\",\"scene_type\":\"1\",\"county_code\":\"000102140931800000002227\"},{\"link_address\":\"中国电信测试\",\"state\":\"00X\",\"id\":\"20160323230\",\"cust_name\":\"测试地址\",\"create_date\":\"2016-05-25 10:19:18\",\"order_time\":\"2016-03-23 17:53:07\",\"offer_idea\":\"1\",\"link_num\":\"18919887267\",\"lan_id\":\"931\",\"intention_staffno\":\"1\",\"scene_type\":\"1\",\"county_code\":\"000102140931800000002227\"}]}}";
		}
		long startTime = System.currentTimeMillis();
		//retStr = HttpClientUtil.execute(inter.getUrl(), reqStr,inter.getType(),inter.getTimeout());
		this.logger.info(""+inter.getName()+"：号码：{},开始时间：{},结束时间：{},请求报文：{},返回报文：{},调用时间:{},接口编号:{}", new Object[] { params.getAccNum(), startTime, System.currentTimeMillis(),reqStr,retStr,(System.currentTimeMillis() - startTime),inter.getId()});
		//格式化数据XML并返回
		return XmlJsonParseUtil.jsonToXml(retStr);
	}
	
	
	/**
	 * 将接口入参获取的xml数据解析
	 * @param xml
	 * @return
	 */
	public RequestParams parseXML(String xml) throws DocumentException {
		Document doc = null;
		doc = DocumentHelper.parseText(xml.trim());
		Element rootElt = doc.getRootElement();
		Element head=rootElt.element("head");
		Element body=rootElt.element("body");
		String exChangeId = head.elementTextTrim("ExchangeId");
		String bizCode = head.elementTextTrim("BizCode");
		String clientId = head.elementTextTrim("ClientId");
		String password = head.elementTextTrim("Password");
		String code = head.elementTextTrim("Code");
		String accNum = body.elementTextTrim("accnum");
		String lanId = body.elementTextTrim("lanid");
		String productId = body.elementTextTrim("productid");
		String definedId=body.elementTextTrim("definedid");
		RequestParams params=new RequestParams(exChangeId,bizCode,clientId,password,code,accNum,lanId,productId,definedId);
		return params;
	}


	@Override
	public String queryCrmBusinessInfo(String xml) {
		RequestParams params;
		String retStr=null;
		try {
			params=parseXML(xml);
		    //判断决定分支
			switch(params.getDefinedId()) {
			case "INF_CustInfo_Query":
				retStr=qryCustInfo(xml,params);
			    break;
			case "INF_UserInfo_Query":
				retStr=qryUserInfo(xml,params);
			    break;
			case "INF_UserPackages_Query":
				retStr=qryUserPackageInfo(xml,params);
			    break;
			case "INF_MainProduct_Query":
				retStr=qryMainProductInfo(xml,params);
			    break;
			case "INF_AuxProduct_Query":
				retStr=qryAuxiliaryProductInfo(xml,params);
			    break;
			 default:
				retStr= defaultQuery(); 
			}
				
		} catch (DocumentException e) {
			retStr = "<response><code>-1<code><msg>入参格式非法<msg></response>";
		}
		return retStr;
	}
	@Override
	public String queryCrmExchangeInfo(String xml) {
		RequestParams params;
		String retStr=null;
		try {
			params=parseXML(xml);
		    //判断决定分支
			switch(params.getDefinedId()) {
			case "INF_InstallPay_Query":
				retStr=qryPreInstallLaterPayInfo(xml,params);
			    break;
			 default:
				retStr= defaultQuery(); 
			}
				
		} catch (DocumentException e) {
			retStr = "<response><code>-1<code><msg>入参格式非法<msg></response>";
		}
		return retStr;
	}
	/**
	 * 查询客户信息 
	 * @param paramString
	 * @return
	 */
	public String qryCustInfo(String xml,RequestParams params) {
		InterFace inter = iPortDao.getInterfaceById(201804);
		return requestCrmBusinessDataAndFormat(inter,params);
	}
	
	/**
	   * 查询用户信息 
	   * @param paramString
	   * @return
	   */
	  public  String qryUserInfo(String xml,RequestParams params) {
		  InterFace inter = iPortDao.getInterfaceById(201804);
		  return requestCrmBusinessDataAndFormat(inter,params);
	  };
	  
	  /**
	   * 查询用户订购的套餐信息 
	   * @param paramString
	   * @return
	   */

	  public  String qryUserPackageInfo(String xml,RequestParams params) {
		  InterFace inter = iPortDao.getInterfaceById(201804);
		  return requestCrmBusinessDataAndFormat(inter,params);
	  };
	  
	  /**
	   * 查询用户订购主产品信息 
	   * @param paramString
	   * @return
	   */

	  public  String qryMainProductInfo(String xml,RequestParams params) {
		  InterFace inter = iPortDao.getInterfaceById(201804);
		  return requestCrmBusinessDataAndFormat(inter,params);
	  };
	  
	  /**
	   * 查询用户订购的附属产品信息
	   * @param paramString
	   * @return
	   */

	  public  String qryAuxiliaryProductInfo(String xml,RequestParams params) {
		  InterFace inter = iPortDao.getInterfaceById(201804);
		  return requestCrmBusinessDataAndFormat(inter,params);
	  };
	  
	  /**
	   * 查询分局信息
	   * @param paramString
	   * @return
	   */
	  public  String qryBranchBureauInfo(String xml) {
		  return null;
	  };
	  
	  /**
	   * 查询账户状态
	   * @param paramString
	   * @return
	   */
	  public  String qryAccountStateInfo(String xml) {
		  return null;
	  };
	  
	  /**
	   * 套餐详情查询
	   * @param paramString
	   * @return
	   */
	  public  String qryPackagesDetailInfo(String xml) {
		  return null;
	  };
	  
	  /**
	   * 订单查询
	   * @param paramString
	   * @return
	   */
	  public  String qryOrderInfo(String xml) {
		  return null;
	  };
	  
	  /**
	   * 先装后付查询
	   * @param paramString
	   * @return
	   */
	  public  String qryPreInstallLaterPayInfo(String xml,RequestParams params) {
			InterFace inter = iPortDao.getInterfaceById(201804);
			return requestCrmExchangeDataAndFormat(inter,params);
	  };
	  
	  /**
	   * 用户套餐是否可结转详情
	   * @param paramString
	   * @return
	   */
	  public  String qryPakgsCarryOverDetailInfo(String xml) {
		  return null;
	  };
	  
	  /**
	   * 主副卡查询
	   * @param paramString
	   * @return
	   */
	  public  String qryMainViceCardInfo(String xml) {
		  return null;
	  };
	  
	  /**
	   * 手机参数查询
	   * @param paramString
	   * @return
	   */

	  public  String qryMobileParamInfo(String xml) {
		  return null;
	  };
	  
	  /**
	   * MSCI信息查询
	   * @param paramString
	   * @return
	   */

	  public  String qryMsCiParamInfo(String xml) {
		  return null;
	  }; 
	  
	  /**
	   * 积分变更日志查询
	   * @param paramString
	   * @return
	   */

	  public  String qryPointsModifyLogInfo(String xml) {
		  return null;
	  }; 
	  
	  /**
	   * CRM基本信息查询
	   * @param paramString
	   * @return
	   */

	  public  String qryCrmBasicInfo(String xml) {
		  return null;
	  }; 
	 
	  /**
	   * 装机单进度查询
	   * @param paramString
	   * @return
	   */
	  public  String qryInstallSheetProgressInfo(String xml) {
		  return null;
	  }; 
	  
	  /**
	   * 客户产品信息查询
	   * @param paramString
	   * @return
	   */
	  public  String qryCustProductInfo(String xml) {
		  return null;
	  }; 
	  
	  /**
	   * 用户套餐变更记录查询
	   * @param paramString
	   * @return
	   */
	  public  String qryUserPkgsChangeInfo(String xml) {
		  return null;
	  }; 
	  
	  
	  /**
	   * 亲情号码查询
	   * @param paramString
	   * @return
	   */
	  public  String qryFamilyNUmberInfo(String xml) {
		  return null;
	  }; 
	    
	  /**
	   * 积分查询
	   * @param paramString
	   * @return
	   */

	  public  String qryPointsInfo(String xml) {
		  return null;
	  };
	  
	  /**
	   * 信用额度查询
	   * @param paramString
	   * @return
	   */

	  public  String qryLineOfCreditInfo(String xml) {
		  return null;
	  };
	  
	  /**
	   * 积分来源查询
	   * @param paramString
	   * @return
	   */
	  public  String qryPointsCopyFromInfo(String xml) {
		  return null;
	  };
	  
	  /**
	   * 积分兑换记录（按账期）
	   * @param paramString
	   * @return
	   */
	  public  String qryPointsExchangeRecordInfo(String xml) {
		  return null;
	  }
	  /**
	   * 积分兑换记录（按账期）
	   * @param paramString
	   * @return
	   */
	  public  String defaultQuery() {
		  return "<response><code>-1<code><msg>未找到该此服务,请联系管理员<msg></response>";
	  }
	
}
