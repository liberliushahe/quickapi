package com.vitea.model;

/**
 * 文件系统信息
 * 
 * @author liushahe
 *
 */
public class FileSystemInfo {
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 路径
	 */
	private String path;
	/**
	 * 读
	 */
	private long read;
	/**
	 * 写
	 */
	private long writes;
	
	/**
	 * 文件系统总大小
	 */
	private long total;
	/**
	 * 文件系统剩余大小
	 */
	private long free;
	/**
	 * 文件系统可用大小
	 */
	private long avail;
	/**
	 * 文件系统被使用大小
	 */
	private long used;
	/**
	 * 文件系统资源利用率
	 */
	private double usage;
	/**
	 * 文件系统类型
	 */
	private String type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public long getRead() {
		return read;
	}
	public void setRead(long read) {
		this.read = read;
	}
	public long getWrites() {
		return writes;
	}
	public void setWrites(long writes) {
		this.writes = writes;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public long getFree() {
		return free;
	}
	public void setFree(long free) {
		this.free = free;
	}
	public long getAvail() {
		return avail;
	}
	public void setAvail(long avail) {
		this.avail = avail;
	}
	public long getUsed() {
		return used;
	}
	public void setUsed(long used) {
		this.used = used;
	}
	public double getUsage() {
		return usage;
	}
	public void setUsage(double usage) {
		this.usage = usage;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
