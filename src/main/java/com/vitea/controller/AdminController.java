package com.vitea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	@RequestMapping("index.do")
	public String getIndex(){
		return "admin/index";
	}
}
