package com.vitea.model;
/**
 * 主机信息model
 * @author liushahe
 *
 */
public class HostInfo {
	private String hostname;
	private String computerName;
	private String userDomain;
	private String ip;
	private long totalMemory;
	private long freeMemory;
	private int availableProcessors;
	private String javaversion;
	private String javavendor;
	private String javahome;
	private String javaclassversion;
	private String osname;
	private String osarch;
	private String osversion;
	private String jvmname;
	
	public String getJvmname() {
		return jvmname;
	}
	public void setJvmname(String jvmname) {
		this.jvmname = jvmname;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getComputerName() {
		return computerName;
	}
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
	public String getUserDomain() {
		return userDomain;
	}
	public void setUserDomain(String userDomain) {
		this.userDomain = userDomain;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public long getTotalMemory() {
		return totalMemory;
	}
	public void setTotalMemory(long totalMemory) {
		this.totalMemory = totalMemory;
	}
	public long getFreeMemory() {
		return freeMemory;
	}
	public void setFreeMemory(long freeMemory) {
		this.freeMemory = freeMemory;
	}
	public int getAvailableProcessors() {
		return availableProcessors;
	}
	public void setAvailableProcessors(int availableProcessors) {
		this.availableProcessors = availableProcessors;
	}
	public String getJavaversion() {
		return javaversion;
	}
	public void setJavaversion(String javaversion) {
		this.javaversion = javaversion;
	}
	public String getJavavendor() {
		return javavendor;
	}
	public void setJavavendor(String javavendor) {
		this.javavendor = javavendor;
	}
	public String getJavahome() {
		return javahome;
	}
	public void setJavahome(String javahome) {
		this.javahome = javahome;
	}
	public String getJavaclassversion() {
		return javaclassversion;
	}
	public void setJavaclassversion(String javaclassversion) {
		this.javaclassversion = javaclassversion;
	}
	public String getOsname() {
		return osname;
	}
	public void setOsname(String osname) {
		this.osname = osname;
	}
	public String getOsarch() {
		return osarch;
	}
	public void setOsarch(String osarch) {
		this.osarch = osarch;
	}
	public String getOsversion() {
		return osversion;
	}
	public void setOsversion(String osversion) {
		this.osversion = osversion;
	}



}
