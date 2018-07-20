package com.vitea.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public abstract interface ISendMessage
{
  @WebMethod(operationName="sendOrderMsg")
  @WebResult(name="response")
  public abstract String sendMessage(@WebParam(name="cmd") String paramString);
  
  @WebMethod(operationName="queryMsgLog")
  @WebResult(name="response")
  public abstract String queryMessageLog(@WebParam(name="cmd") String paramString);
  
  @WebMethod(operationName="sendRealTimeMsg")
  @WebResult(name="response")
  public abstract String sendRealTimeMsg(@WebParam(name="cmd") String paramString);
}
