package com.vitea.model;

public class CPUInfo {
	private double userUsed;
	private double systUsed;
	private double wait;
	private double nice;
	private double idle;
	private double Combined;
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
		return Combined;
	}
	public void setCombined(double combined) {
		Combined = combined;
	}

	
}
