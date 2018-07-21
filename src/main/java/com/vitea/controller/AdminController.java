package com.vitea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	@RequestMapping("index.do")
	public ModelAndView getIndex(Model model){
		
		model.addAttribute("name", "admintest");
		return new ModelAndView("admin/admin","model",model);

	}
}
