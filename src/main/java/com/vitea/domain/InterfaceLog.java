package com.vitea.domain;

import java.util.Date;

public class InterfaceLog {
	/**
	 * 接口编码
	 */
	private Short id;

	/**
	 * 接口代号
	 */
	private String interfaceId;

	/**
	 * 地址
	 */
	private String url;

	/**
	 * 入参
	 */
	private String inParam;

	/**
	 * 出参
	 */
	private String outParam;

	/**
	 * 调用时间
	 */
	private Date transferTime;

	/**
	 * 所用时间
	 */
	private String usedTime;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId == null ? null : interfaceId.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getInParam() {
		return inParam;
	}

	public void setInParam(String inParam) {
		this.inParam = inParam == null ? null : inParam.trim();
	}

	public String getOutParam() {
		return outParam;
	}

	public void setOutParam(String outParam) {
		this.outParam = outParam == null ? null : outParam.trim();
	}

	public Date getTransferTime() {
		return transferTime;
	}

	public void setTransferTime(Date transferTime) {
		this.transferTime = transferTime;
	}

	public String getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(String usedTime) {
		this.usedTime = usedTime == null ? null : usedTime.trim();
	}
}