package com.vitea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@RequestMapping("index.do")
	public String getIndex(){
		return "index";
	}
}
