package com.vitea.model;
/**
 * CPU info
 * @author liushahe
 *
 */
public class ServerCpuInfo {
	private double userUsed;
	private double systUsed;
	private double wait;
	private double nice;
	private double idle;
	private double combined;
	public double getUserUsed() {
		return userUsed;
	}
	public void setUserUsed(double userUsed) {
		this.userUsed = userUsed;
	}
	public double getSystUsed() {
		return systUsed;
	}
	public void setSystUsed(double systUsed) {
		this.systUsed = systUsed;
	}
	public double getWait() {
		return wait;
	}
	public void setWait(double wait) {
		this.wait = wait;
	}
	public double getNice() {
		return nice;
	}
	public void setNice(double nice) {
		this.nice = nice;
	}
	public double getIdle() {
		return idle;
	}
	public void setIdle(double idle) {
		this.idle = idle;
	}
	public double getCombined() {
		return combined;
	}
	public void setCombined(double combined) {
		this.combined = combined;
	}
	

	
}
