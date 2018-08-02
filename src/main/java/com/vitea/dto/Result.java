package com.vitea.dto;
/**
 * result对象
 * @author liushahe
 * @version 1.0.0
 */
public class Result {
	/**
	 * 编码
	 */
	private String resultCode;
	/**
	 * 键
	 */
	private String resultName;
	/**
	 * 值
	 */
	private String resultValue;
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultName() {
		return resultName;
	}
	public void setResultName(String resultName) {
		this.resultName = resultName;
	}
	public String getResultValue() {
		return resultValue;
	}
	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}
	public Result(String resultCode, String resultName, String resultValue) {
		super();
		this.resultCode = resultCode;
		this.resultName = resultName;
		this.resultValue = resultValue;
	}
	public Result() {
		super();
	}
	
	
    
}
