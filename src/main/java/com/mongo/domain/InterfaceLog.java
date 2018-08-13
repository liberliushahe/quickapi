package com.mongo.domain;
/**
 * mongo接口实体
 * @author liushahe
 *
 */
public class InterfaceLog {
	/**
	 * 接口编号
	 */
	private String interid;
	/**
	 * ip
	 */
	private String ip;
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
	private String reqstr;
	/**
	 * 响应报文
	 */
	private String retstr;
	/**
	 * 花费时间
	 */
	private String cost;
	/**
	 * 调用时间
	 */
	private String timestamp;
	
	public String getInterid() {
		return interid;
	}
	public void setInterid(String interid) {
		this.interid = interid;
	}
	public String getRetstr() {
		return retstr;
	}
	public void setRetstr(String retstr) {
		this.retstr = retstr;
	}
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
	public String getReqstr() {
		return reqstr;
	}
	public void setReqstr(String reqstr) {
		this.reqstr = reqstr;
	}
	public String getRetsqr() {
		return retstr;
	}
	public void setRetsqr(String retsqr) {
		this.retstr = retsqr;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Override
	public String toString() {
		return "InterfaceLog [accnum=" + accnum + ", starttime=" + starttime + ", endtime=" + endtime + ", reqstr="
				+ reqstr + ", retsqr=" + retstr + ", cost=" + cost + ", timestamp=" + timestamp + "]";
	}
	
}
