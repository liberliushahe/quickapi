package com.vitea.domain;
/**
 * 接口实体
 * @author liushahe
 *
 */
public class InterFace {
	private int id;
	private String url;
	private String requestport;
	private String method;
	private String type;
	private String description;
	
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
	public String getRequestport() {
		return requestport;
	}
	public void setRequestport(String requestport) {
		this.requestport = requestport;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "InterFace [url=" + url + ", requestport=" + requestport + ", method=" + method + ", type=" + type
				+ ", description=" + description + "]";
	}
	
	

}
