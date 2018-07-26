package com.vitea.model;
/**
 * 网络信息model
 * @author liushahe
 *
 */
public class NetInfo {
	/**
	 * 设备名称
	 */
	private String name;
	/**
	 * ip
	 */
	private String ip;
	/**
	 * 发送字节
	 */
	private long send;
	/**
	 * 接收字节
	 */
	private long receive;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public long getSend() {
		return send;
	}
	public void setSend(long send) {
		this.send = send;
	}
	public long getReceive() {
		return receive;
	}
	public void setReceive(long receive) {
		this.receive = receive;
	}
	

}
