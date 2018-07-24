package com.vitea.util;

import java.net.UnknownHostException;

import org.hyperic.sigar.SigarException;
import org.junit.Test;

public class SystemInfoTest {
	@Test
	public void getMemory() {
		try {
			SystemInfoUtil.getMemory();
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void getSystem() {
		try {
			SystemInfoUtil.getProperty();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void getCpu() {
		try {
			SystemInfoUtil.getCpuInfo();
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void getNet() {
		try {
			//SystemInfoUtil.ethernet();
			SystemInfoUtil.net();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
