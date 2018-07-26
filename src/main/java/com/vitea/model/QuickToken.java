package com.vitea.model;
/**
 * token model
 * @author liushahe
 *
 */
public class QuickToken {
	private String accessToken;
	private String expiresIn;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	
	
}
