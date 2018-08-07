package com.vitea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vitea.service.IGetInterfaceLog;
/**
 * 日志查询控制器
 * @author liushahe
 *
 */
@Controller
public class InterfaceLogController {
	@Autowired
	IGetInterfaceLog iGetInterfaceLog;
	@RequestMapping("/getInterfaceLog")
	public String getInterfaceLog() {
		return "admin/interfacelog/logger";
	}
}
