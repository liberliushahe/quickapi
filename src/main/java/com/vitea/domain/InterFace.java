package com.vitea.domain;
/**
 * 接口实体
 * @author liushahe
 *
 */
public class InterFace {
	private int id;
	private int sysid;
	private String name;
	private int stat;
	private String url;
	private String port;
	private String method;
	private String type;
	private String description;
	
	public int getSysid() {
		return sysid;
	}
	public void setSysid(int sysid) {
		this.sysid = sysid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStat() {
		return stat;
	}
	public void setStat(int stat) {
		this.stat = stat;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port =port;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "InterFace [url=" + url + ", requestport=" + port + ", method=" + method + ", type=" + type
				+ ", description=" + description + "]";
	}
	
	

}
