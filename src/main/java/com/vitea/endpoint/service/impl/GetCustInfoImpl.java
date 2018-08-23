package com.vitea.endpoint.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.vitea.endpoint.dto.CustInfo;
import com.vitea.endpoint.service.IGetCustInfo;
/**
 * restful接口get方法返回知识库数据格式json
 * @author liushahe
 *
 */
public class GetCustInfoImpl  implements IGetCustInfo {

	@Override
	public CustInfo queryCustInfo(String id) {
		CustInfo cust=new CustInfo();
		cust.setName("李文");
		cust.setAddress("西安市高新区");
		return cust;
	}
	@Override
	public JSONObject queryDetailCustInfoPost(String paramString) {
		String json="{\"Message\":\"成功\",\"Code\":\"0000\",\"ExchangeId\":\" fwqzjk201806111006586681522662\",\"QueryResults\":{\"Cust\":[{\"custBrand\":\"我的E家\",\"linkPhone\":\"100\",\"cardType\":\"户口本/居住证\",\"accountManger\":\"\",\"custName\":\"平凉电信分公司\",\"custLevel\":\"312\",\"cardNo\":\"13321306888\",\"areaCode\":\"0931\",\"vipName\":\"钻\",\"cardId\":\"2BI\",\"custId\":\"3432432423342\",\"accMngContactNo\":\"\",\"channelTypeId\":\"2\",\"channelTypeName\":\"家庭客户\",\"vipCode\":\"4\",\"sumUsableScore\":\"\"}]}}";
		JSONObject object=JSONObject.parseObject(json);
		return object;
	}

}
