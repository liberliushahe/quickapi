package com.vitea.endpoint.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
/**
 * 发送短信
 * @author liushahe
 *
 */
@WebService
public abstract interface ISendMessage
{
  @WebMethod(operationName="sendOrderMsg")
  @WebResult(name="response")
  /**
   * 发送短信
   * @param paramString
   * @return
   */
  public abstract String sendMessage(@WebParam(name="cmd") String paramString);
  
  @WebMethod(operationName="queryMsgLog")
  @WebResult(name="response")
  /**
   * 查询短信日志
   * @param paramString
   * @return
   */
  public abstract String queryMessageLog(@WebParam(name="cmd") String paramString);
  
  @WebMethod(operationName="sendRealTimeMsg")
  @WebResult(name="response")
  /**
   * 发送自定义短信
   * @param paramString
   * @return
   */
  public abstract String sendRealTimeMsg(@WebParam(name="cmd") String paramString);
}
