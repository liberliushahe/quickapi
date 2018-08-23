package com.vitea.endpoint.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * 此接口主要负责将核心接口转为webservice服务返回xml格式数据
 * 1.查询客户信息
 * 2.查询用户信息
 * 3.查询用户订购的套餐信息
 * 4.查询用户订购主产品信息
 * 5.查询用户订购的附属产品信息

 * @author liushahe
 *
 */
@WebService
public interface IQueryInfoWsdl {

	  /**
	   * 查询客户信息 
	   * @param paramString
	   * @return
	   */
	  @WebMethod(operationName="qryCustInfo")
	  @WebResult(name="response")
	  public abstract String qryCustInfo(@WebParam(name="params") String paramString);
	
	  /**
	   * 查询用户信息 
	   * @param paramString
	   * @return
	   */
	  @WebMethod(operationName="qryUserInfo")
	  @WebResult(name="response")
	  public abstract String qryUserInfo(@WebParam(name="params") String paramString);
	  
	  /**
	   * 查询用户订购的套餐信息 
	   * @param paramString
	   * @return
	   */
	  @WebMethod(operationName="qryUserPackageInfo")
	  @WebResult(name="response")
	  public abstract String qryUserPackageInfo(@WebParam(name="params") String paramString);
	  
	  /**
	   * 查询用户订购主产品信息 
	   * @param paramString
	   * @return
	   */
	  @WebMethod(operationName="qryMainProductInfo")
	  @WebResult(name="response")
	  public abstract String qryMainProductInfo(@WebParam(name="params") String paramString);
	  
	  /**
	   * 查询用户订购的附属产品信息
	   * @param paramString
	   * @return
	   */
	  @WebMethod(operationName="qryAuxiliaryProductInfo")
	  @WebResult(name="response")
	  public abstract String qryAuxiliaryProductInfo(@WebParam(name="params") String paramString);
	  
}
