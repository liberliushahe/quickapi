package com.vitea.model;

public class MemoryInfo {
	private double usedpercent;
	private long total;
	private long used;
	private long free;
	private long swaptotal;
	private long swapused;
	private long swapfree;
	public double getUsedpercent() {
		return usedpercent;
	}
	public void setUsedpercent(double usedpercent) {
		this.usedpercent = usedpercent;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public long getUsed() {
		return used;
	}
	public void setUsed(long used) {
		this.used = used;
	}
	public long getFree() {
		return free;
	}
	public void setFree(long free) {
		this.free = free;
	}
	public long getSwaptotal() {
		return swaptotal;
	}
	public void setSwaptotal(long swaptotal) {
		this.swaptotal = swaptotal;
	}
	public long getSwapused() {
		return swapused;
	}
	public void setSwapused(long swapused) {
		this.swapused = swapused;
	}
	public long getSwapfree() {
		return swapfree;
	}
	public void setSwapfree(long swapfree) {
		this.swapfree = swapfree;
	}
	

}
