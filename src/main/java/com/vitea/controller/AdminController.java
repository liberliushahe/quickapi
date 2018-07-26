package com.vitea.controller;

import java.util.List;

import org.hyperic.sigar.SigarException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vitea.model.ServerCpuInfo;
import com.vitea.model.FileSystemInfo;
import com.vitea.model.HostInfo;
import com.vitea.model.MemoryInfo;
import com.vitea.model.NetInfo;
import com.vitea.util.SystemInfoUtil;
/**
 * 
 * @author liushahe
 *
 */
@Controller
public class AdminController {
	@RequestMapping("admin.do")
	public ModelAndView getIndex(Model model){
		HostInfo hostInfo=null;
		List<FileSystemInfo> fileSystemInfo=null;
		try {
			hostInfo=SystemInfoUtil.getProperty();
			fileSystemInfo=SystemInfoUtil.getFileInfo();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("hostInfo", hostInfo);
		model.addAttribute("fileInfoList", fileSystemInfo);
		return new ModelAndView("admin/admin","model",model);

	}
	@RequestMapping("currentmemory.do")
	@ResponseBody
	public Object getCurrentMemory(){
		MemoryInfo memoeyInfo=null;
		try {
			memoeyInfo=SystemInfoUtil.getMemory();
		} catch (SigarException e) {
			e.printStackTrace();
		}
		return memoeyInfo;

	}
	@RequestMapping("currentcpu.do")
	@ResponseBody
	public Object getCurrentCpu(){
		ServerCpuInfo cpuInfo=null;
		try {
			cpuInfo=SystemInfoUtil.getCpuInfo();
		} catch (SigarException e) {
			e.printStackTrace();
		}
		return cpuInfo;

	}
	@RequestMapping("currentnet.do")
	@ResponseBody
	public List<NetInfo> getCurrentNet(){
		List<NetInfo> netInfo=null;
		try {
			netInfo=SystemInfoUtil.getNetInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return netInfo;

	}
}
