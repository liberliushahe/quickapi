package com.vitea.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public abstract interface ISendMessage
{
  @WebMethod(operationName="sendMsg")
  @WebResult(name="response")
  public abstract String sendMessage(@WebParam(name="cmd") String paramString);
}
