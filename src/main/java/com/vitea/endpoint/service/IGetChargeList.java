package com.vitea.endpoint.service;

import java.io.IOException;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.dom4j.DocumentException;
/**
 * 获取计费清单
 * @author liushahe
 *
 */
@WebService
public abstract interface IGetChargeList
{
  @WebMethod(operationName="getChargeList")
  @WebResult(name="response")
  /**
   * webservice
   * @param paramString
   * @return
   * @throws IOException
   * @throws DocumentException
   */
  public abstract String endpointReset(@WebParam(name="cmd") String paramString)
  throws IOException, DocumentException;
}
