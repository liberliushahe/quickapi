package com.vitea.endpoint.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
/**
 * 查询集团用户volte信息
 * @author liushahe
 *
 */
@WebService
public abstract interface IQueryVolteInfo
{
  @WebMethod(operationName="queryVolteInfo")
  @WebResult(name="response")
  /**
   * webservice
   * @param paramString
   * @return
   */
  public abstract String queryVolteInfo(@WebParam(name="cmd") String paramString);
  
}
