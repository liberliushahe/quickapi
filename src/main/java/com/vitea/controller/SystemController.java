package com.vitea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 系统控制器
 * @author liushahe
 *
 */
@Controller
public class SystemController {
	@RequestMapping("systeminfo.do")
	@ResponseBody
	public Object getSystemInfo(Model model){
	
	return  null;

	}
}
