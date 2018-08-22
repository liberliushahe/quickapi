package com.vitea.controller;

import java.util.List;

import org.hyperic.sigar.SigarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vitea.model.ServerCpuInfo;
import com.vitea.service.IGetInterface;
import com.vitea.service.IGetInterfaceLog;
import com.alibaba.fastjson.JSONObject;
import com.vitea.model.Ethernet;
import com.vitea.model.FileSystemInfo;
import com.vitea.model.HostInfo;
import com.vitea.model.MemoryBean;
import com.vitea.model.MemoryInfo;
import com.vitea.model.NetInfo;
import com.vitea.util.SystemInfoUtil;
/**
 * 管理员首页
 * @author liushahe
 *
 */
@Controller
public class AdminController {
	@Autowired
	IGetInterface iGetInterface;
	@Autowired
	IGetInterfaceLog iGetInterfaceLog;
	@RequestMapping(value= {"/","login.do"})
	public String login() {
		return "admin/login";
	}
	
	
	@RequestMapping("interfaceStatis.do")
	@ResponseBody
	public String interfaceStatis(){
		long success=iGetInterfaceLog.successCount();
		long fail=iGetInterfaceLog.failCount();
        Integer count=iGetInterface.getAllInterfaceCount();
        JSONObject json=new JSONObject();
        json.put("count", count);
        json.put("success", success); 
        json.put("fail", fail);
		return json.toJSONString();
		
	}
	@RequestMapping("admin.do")
	public ModelAndView getIndex(Model model){
		HostInfo hostInfo=null;
		List<FileSystemInfo> fileSystemInfo=null;
		List<MemoryBean> memoryBean=null;
		List<MemoryBean> memoryPoolBean=null;
		List<Ethernet> ethernet=null;
		long success=iGetInterfaceLog.successCount();
		long fail=iGetInterfaceLog.failCount();
        Integer count=iGetInterface.getAllInterfaceCount();
		try {
			hostInfo=SystemInfoUtil.getProperty();
			fileSystemInfo=SystemInfoUtil.getFileInfo();
			//获取JVM内存信息和内存池信息
			memoryBean=SystemInfoUtil.getJvmMemoryInfo();
			memoryPoolBean=SystemInfoUtil.getJvmMemoryPoolInfo();
			ethernet=SystemInfoUtil.getEthernet();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("hostInfo", hostInfo);
		model.addAttribute("fileInfoList", fileSystemInfo);
		model.addAttribute("memoryBean", memoryBean);
		model.addAttribute("memoryPoolBean", memoryPoolBean);
		model.addAttribute("ethernet", ethernet);
		model.addAttribute("success", success);
		model.addAttribute("fail", fail);
		model.addAttribute("count", count);
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
