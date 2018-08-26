package com.vitea.endpoint.dto;
/**
 * 接口请求参数封装类
 * @author liushahe
 *
 */
public class RequestParams {
	/**
	 * 接口调用编码
	 */
	private String exChangeId; 
	/**
	 * 操作类型
	 */
	private String bizCode ;
	/**
	 * 客户端编码
	 */
	private String clientId;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 接口编码
	 */
	private String code;
	/**
	 * 障碍号码
	 */
	private String accNum;
	/**
	 * 区域编码
	 */
	private String lanId ;
	/**
	 * 产品编码
	 */
	private String productId;
	
	/**
	 * 预处理自定义接口编码
	 */
	private String definedId;
	
	public String getDefinedId() {
		return definedId;
	}
	public void setDefinedId(String definedId) {
		this.definedId = definedId;
	}
	public String getExChangeId() {
		return exChangeId;
	}
	public void setExChangeId(String exChangeId) {
		this.exChangeId = exChangeId;
	}
	public String getBizCode() {
		return bizCode;
	}
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAccNum() {
		return accNum;
	}
	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}
	public String getLanId() {
		return lanId;
	}
	public void setLanId(String lanId) {
		this.lanId = lanId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public RequestParams(String exChangeId, String bizCode, String clientId, String password, String code,
			String accNum, String lanId, String productId,String definedId) {
		super();
		this.exChangeId = exChangeId;
		this.bizCode = bizCode;
		this.clientId = clientId;
		this.password = password;
		this.code = code;
		this.accNum = accNum;
		this.lanId = lanId;
		this.productId = productId;
		this.definedId=definedId;
	}
	public RequestParams() {
		super();
	}
	
	
}
