package com.vitea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 接口监控
 * @author liushahe
 *
 */
@Controller
public class MonitorController {
	@RequestMapping("monitorInterface")
	public String getMonitorPage() {
		return "admin/interface/monitor/monitor";
	}

}
