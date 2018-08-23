package com.vitea.endpoint.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.vitea.endpoint.dto.CustInfo;
import com.vitea.endpoint.service.IQueryInfoRest;
/**
 * restful接口get方法返回知识库数据格式json
 * @author liushahe
 *
 */
public class QueryInfoRestImpl  implements IQueryInfoRest {

	@Override
	public CustInfo queryCustInfo(String id) {
		CustInfo cust=new CustInfo();
		cust.setName("李文");
		cust.setAddress("西安市高新区");
		return cust;
	}
	@Override
	public JSONObject queryDetailCustInfo(String paramString) {
		String json="{\"Message\":\"成功\",\"Code\":\"0000\",\"ExchangeId\":\" fwqzjk201806111006586681522662\",\"QueryResults\":{\"Cust\":[{\"custBrand\":\"我的E家\",\"linkPhone\":\"100\",\"cardType\":\"户口本/居住证\",\"accountManger\":\"\",\"custName\":\"平凉电信分公司\",\"custLevel\":\"312\",\"cardNo\":\"13321306888\",\"areaCode\":\"0931\",\"vipName\":\"钻\",\"cardId\":\"2BI\",\"custId\":\"3432432423342\",\"accMngContactNo\":\"\",\"channelTypeId\":\"2\",\"channelTypeName\":\"家庭客户\",\"vipCode\":\"4\",\"sumUsableScore\":\"\"}]}}";
		JSONObject object=JSONObject.parseObject(json);
		return object;
	}
	@Override
	public JSONObject queryDetailUserInfo(String paramString) {
		String json="{\"Message\":\"成功\",\"Code\":\"0000\",\"ExchangeId\":\" fwqzjk201806111006586681522672\",\"QueryResults\":{\"User\":[{\"areaCode\":\"0937\",\"areaName\":\"酒泉市本地网\",\"openTime\":\"2014-03-02 15:41:15\",\"userType\":\"住宅用户\",\"installAddr\":\"瓜州县三道沟镇三道沟村八组\",\"prdInstState\":\"0\",\"prdInstStateName\":\"在用\",\"payType\":\"现金\",\"userName\":\"马晓燕\",\"availableScore\":\"3960\",\"isPrePay\":\"2\",\"payName\":\"预付费\",\"prdInstId\":\"937417998393\",\"serviceNbr\":\"13321371899\",\"accId\":\"937223553607\",\"feeType\":\"1201\",\"g_imsi\":\"204043146846022\",\"iccid\":\"89860314109372773128\",\"prdCode\":\"900000001\",\"prdName\":\"移动电话\",\"expDate\":\"20300101 00:00:00\",\"certiType\":\"身份证\",\"certiNumber\":\"622126197402120629\"}]}}";
		JSONObject object=JSONObject.parseObject(json);
		return object;
	}
	@Override
	public JSONObject queryDetailUserPackages(String paramString) {
		String json="{\"Message\":\"成功\",\"Code\":\"0000\",\"ExchangeId\":\" fwqzjk201806111006586681522682\",\"QueryResults\":{\"OfferList\":[{\"pkg\":[{\"pkgName\":\"乐享3G易通卡（全能版）-129元\",\"pkgID\":\"11107745\",\"pkgGrade\":\"129\",\"GroupId\":\"129\",\"pkgInstID\":\"200076267\",\"ProdOfferName\":\"乐享3G易通卡（全能版）-129元\",\"ProdOfferId\":\"11107745\",\"effectiveTime\":\"2012-07-01 00:00:00\",\"expireTime\":\"2030-01-01 00:00:00\",\"pkgDetails\":{\"pkgDetail\":{}}},{\"pkgName\":\"乐享3G易通卡（全能版）-通话时长300分钟\",\"pkgID\":\"11107752\",\"pkgGrade\":\"0\",\"GroupId\":\"0\",\"pkgInstID\":\"200076268\",\"ProdOfferName\":\"乐享3G易通卡（全能版）-通话时长300分钟\",\"ProdOfferId\":\"11107752\",\"effectiveTime\":\"2012-07-01 00:00:00\",\"expireTime\":\"2030-01-01 00:00:00\",\"pkgDetails\":{\"pkgDetail\":{}}},{\"pkgName\":\"赠送30小时省内Wlan免费时长\",\"pkgID\":\"11105677\",\"pkgGrade\":\"0\",\"GroupId\":\"0\",\"pkgInstID\":\"15058747882\",\"ProdOfferName\":\"赠送30小时省内Wlan免费时长\",\"ProdOfferId\":\"11105677\",\"effectiveTime\":\"2010-02-01 00:00:00\",\"expireTime\":\"2010-06-30 23:59:59\",\"pkgDetails\":{\"pkgDetail\":{}}}]}]}}";
		JSONObject object=JSONObject.parseObject(json);
		return object;
	}
	@Override
	public JSONObject queryDetailProdList(String paramString) {
		String json="{\"Message\":\"成功\",\"Code\":\"0000\",\"ExchangeId\":\"HNBSS3201806111006586621624482\",\"QueryResults\":{\"ProdList\":[{\"prdCode\":\"80000045\",\"prdName\":\"移动电话\",\"prdcharacters\":{\"prdcharacter\":[{\"characterName\":\"是否预开通\",\"characterValue\":\"0\",\"characterValueName\":\"非预开通\",\"characterCode\":\"992036601\"},{\"characterName\":\"业务号码\",\"characterValue\":\"\",\"characterValueName\":\"\",\"characterCode\":\"990000017\"}]}}]}}";
		JSONObject object=JSONObject.parseObject(json);
		return object;
	}
	@Override
	public JSONObject queryDetailSubProducts(String paramString) {
		String json="{\"Message\":\"成功\",\"Code\":\"0000\",\"ExchangeId\":\"HNBSS3201806111006586622524482\",\"QueryResults\":{\"subProducts\":[{\"subProduct\":[{\"receiveTime\":\"receiveTime\",\"finishTime\":\"finishTime\",\"subPrdName\":\"subPrdName\",\"subPrdStateName\":\"subPrdStateName\",\"subPrdCharacters\":{\"subPrdCharacter\":[{\"characterName\":\"上行速率\",\"characterValue\":\"1100\",\"characterValueName\":\"5M\",\"characterCode\":\"800027684\"},{\"characterName\":\"下行速率\",\"characterValue\":\"1200\",\"characterValueName\":\"10M\",\"characterCode\":\"800027685\"},{\"characterName\":\"QOS等级\",\"characterValue\":\"1300\",\"characterValueName\":\"普通\",\"characterCode\":\"800027686\"}]},\"subPrdCode\":\"subPrdCode\",\"subPrdState\":\"subPrdState\"},{\"receiveTime\":\"receiveTime\",\"finishTime\":\"finishTime\",\"subPrdCode\":\"subPrdCode\",\"subPrdName\":\"subPrdName\",\"subPrdStateName\":\"subPrdStateName\",\"subPrdState\":\"subPrdState\"},{\"receiveTime\":\"receiveTime\",\"finishTime\":\"finishTime\",\"subPrdCode\":\"subPrdCode\",\"subPrdName\":\"subPrdName\",\"subPrdStateName\":\"subPrdStateName\",\"subPrdState\":\"subPrdState\"},{\"receiveTime\":\"receiveTime\",\"finishTime\":\"finishTime\",\"subPrdCode\":\"subPrdCode\",\"subPrdName\":\"subPrdName\",\"subPrdStateName\":\"subPrdStateName\",\"subPrdState\":\"subPrdState\"},{\"receiveTime\":\"receiveTime\",\"finishTime\":\"finishTime\",\"subPrdName\":\"subPrdName\",\"subPrdStateName\":\"subPrdStateName\",\"subPrdCharacters\":{\"subPrdCharacter\":[{\"characterName\":\"使用状态\",\"characterValue\":\"1\",\"characterValueName\":\"正常\",\"characterCode\":\"600002471\"}]},\"subPrdCode\":\"subPrdCode\",\"subPrdState\":\"subPrdState\"},{\"receiveTime\":\"receiveTime\",\"finishTime\":\"finishTime\",\"subPrdCode\":\"subPrdCode\",\"subPrdName\":\"subPrdName\",\"subPrdStateName\":\"subPrdStateName\",\"subPrdState\":\"subPrdState\"},{\"receiveTime\":\"receiveTime\",\"finishTime\":\"finishTime\",\"subPrdCode\":\"subPrdCode\",\"subPrdName\":\"subPrdName\",\"subPrdStateName\":\"subPrdStateName\",\"subPrdState\":\"subPrdState\"}]}]}}";
		JSONObject object=JSONObject.parseObject(json);
		return object;
	}

}
