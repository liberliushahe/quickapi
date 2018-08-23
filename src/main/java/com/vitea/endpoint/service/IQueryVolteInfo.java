package com.vitea.endpoint.service;

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
 
 
  /**
   * HSS信息返回
   * @param paramString
   * @return
   */
  @WebResult(name="platformQryReturn")
  public abstract String platformQryVolteHss(@WebParam(name="cmd") String paramString);
  /**
   * CSCF信息返回
   * @param paramString
   * @return
   */
  @WebResult(name="platformQryReturn")
  public abstract String platformQryVolteCsCf(@WebParam(name="cmd") String paramString);
  /**
   * AS信息返回
   * @param paramString
   * @return
   */
  @WebResult(name="platformQryReturn")
  public abstract String platformQryVolteAs(@WebParam(name="cmd") String paramString);
  /**
   * DNS信息返回
   * @param paramString
   * @return
   */
  @WebResult(name="platformQryReturn")
  public abstract String platformQryVolteEnumDns(@WebParam(name="cmd") String paramString);
}
