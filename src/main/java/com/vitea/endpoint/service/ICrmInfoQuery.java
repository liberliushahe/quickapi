package com.vitea.endpoint.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * 此接口查询客户CRM相关信息
 * @author liushahe
 *
 */
@WebService
public interface ICrmInfoQuery {
	/**
	 * CRMBusiness相关信息接口
	 * @param paramString
	 * @return
	 */
	  @WebMethod(operationName="CrmBusiness")
	  @WebResult(name="out")
	  public abstract String queryCrmBusinessInfo(@WebParam(name="params") String paramString);
	  
	  /**
	    * CRMExchange相关信息接口
		* @param paramString
		* @return
		*/
	  @WebMethod(operationName="exchange")
	  @WebResult(name="out")
      public abstract String queryCrmExchangeInfo(@WebParam(name="params") String paramString);
}
