package com.vitea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@RequestMapping("login.do")
	public String userLogin(){
		System.out.println("start login");
		return "admin/admin";
	}
	@RequestMapping("logout.do")
	public String logOut(){
		System.out.println("start logout");
		return "index";
	}
	@RequestMapping("fail.do")
	public String lofinFail(){
		return "index";
	}
	
}
