package com.vitea.model;
/**
 * 网卡model
 * @author liushahe
 *
 */
public class Ethernet {
	/**
	 * ip地址
	 */
	private String address;
	/**
	 * 广播地址
	 */
	private String broadcast;
	/**
	 * 物理地址
	 */
	private String mac;
	/**
	 * 子网掩码
	 */
	private String netmask;
	/**
	 * 网卡描述
	 */
	private String desc;
	/**
	 * 网卡类型
	 */
	private String type;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBroadcast() {
		return broadcast;
	}
	public void setBroadcast(String broadcast) {
		this.broadcast = broadcast;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getNetmask() {
		return netmask;
	}
	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
