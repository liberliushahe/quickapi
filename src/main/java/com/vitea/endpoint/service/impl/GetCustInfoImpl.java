package com.vitea.endpoint.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

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
	public CustInfo queryDetailCustInfoGet(String paramString) {
		System.out.println(paramString);
		CustInfo cust=new CustInfo();
		return cust;
	}

	@Override
	public CustInfo queryDetailCustInfoPost(String paramString) {
		System.out.println(paramString);
		CustInfo cust=new CustInfo();
		return cust;
	}

}
