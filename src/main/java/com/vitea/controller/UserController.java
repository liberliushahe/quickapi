package com.vitea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.vitea.domain.User;
import com.vitea.service.IGetUserList;
/**
 * 接口控制器
 * @author liushahe
 *
 */
@Controller
public class UserController {
	@Autowired
	IGetUserList iGetUserList;
	@RequestMapping("getUserList.do")
	public ModelAndView getIndex(Model model,@RequestParam(required = false) Integer id,@RequestParam(required = false) String url, @RequestParam(defaultValue = "1") Integer index, @RequestParam(defaultValue = "10") Integer size){		
		PageInfo<User> userlist=iGetUserList.getPageOfUserList(index, size);
		model.addAttribute("pagelist", userlist);
		return new ModelAndView("admin/userinfo","model",model);

	}
	
	
}
