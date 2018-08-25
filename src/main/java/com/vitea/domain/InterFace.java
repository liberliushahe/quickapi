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
	private int timeout;
	private String inparam;
	private String description;
	private int istemp;
	private String template;
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	public String getInparam() {
		return inparam;
	}
	public void setInparam(String inparam) {
		this.inparam = inparam;
	}
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
	
	public int getIstemp() {
		return istemp;
	}
	public void setIstemp(int istemp) {
		this.istemp = istemp;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	@Override
	public String toString() {
		return "InterFace [id=" + id + ", sysid=" + sysid + ", name=" + name + ", stat=" + stat + ", url=" + url
				+ ", port=" + port + ", method=" + method + ", type=" + type + ", timeout=" + timeout + ", inparam="
				+ inparam + ", description=" + description + ", istemp=" + istemp + ", template=" + template + "]";
	}
	
	

}
