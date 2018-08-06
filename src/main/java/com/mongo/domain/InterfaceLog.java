package com.mongo.domain;
/**
 * mongo接口实体
 * @author liushahe
 *
 */
public class InterfaceLog {
	/**
	 * 号码
	 */
	private String accnum;
	/**
	 * 开始时间
	 */
	private String starttime;
	/**
	 * 结束时间
	 */
	private String endtime;
	/**
	 * 请求报文
	 */
	private String reqStr;
	/**
	 * 响应报文
	 */
	private String retSqr;
	/**
	 * 花费时间
	 */
	private String cost;
	/**
	 * 调用时间
	 */
	private String time;
	public String getAccnum() {
		return accnum;
	}
	public void setAccnum(String accnum) {
		this.accnum = accnum;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getReqStr() {
		return reqStr;
	}
	public void setReqStr(String reqStr) {
		this.reqStr = reqStr;
	}
	public String getRetSqr() {
		return retSqr;
	}
	public void setRetSqr(String retSqr) {
		this.retSqr = retSqr;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	

}
