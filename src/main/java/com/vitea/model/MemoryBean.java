package com.vitea.model;

import java.lang.management.MemoryType;

/**
 * JVM堆和非堆内存信息
 * @author liushahe
 *
 */
public class MemoryBean {
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 最大值
	 */
	private long max;
	/**
	 * 初始化
	 */
	private long init;
	/**
	 * 虚拟机能使用的内存量
	 */
	private long commit;
	/**
	 * 被使用量
	 */
	private long used;

	/**
	 * 类型
	 */
	private MemoryType type;
	
	public MemoryType getType() {
		return type;
	}
	public void setType(MemoryType type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMax() {
		return max;
	}
	public void setMax(long max) {
		this.max = max;
	}
	public long getInit() {
		return init;
	}
	public void setInit(long init) {
		this.init = init;
	}
	public long getCommit() {
		return commit;
	}
	public void setCommit(long commit) {
		this.commit = commit;
	}
	public long getUsed() {
		return used;
	}
	public void setUsed(long used) {
		this.used = used;
	}
	

}
