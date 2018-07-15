package com.vitea.service;

import java.io.IOException;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import org.dom4j.DocumentException;

@WebService
public abstract interface IGetChargeList
{
  @WebMethod(operationName="endpointReset")
  @WebResult(name="response")
  public abstract String endpointReset(@WebParam(name="cmd") String paramString)
  throws IOException, DocumentException;
}
