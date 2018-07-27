package com.vitea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.vitea.domain.InterFace;
import com.vitea.service.IGetInterface;
/**
 * 接口控制器
 * @author liushahe
 *
 */
@Controller
public class InterfaceController {
	@Autowired
	IGetInterface iGetInterface;
	@RequestMapping("getInterfaceInfo.do")
	public ModelAndView getIndex(Model model,@RequestParam(required = false) Integer id,@RequestParam(required = false) String url, @RequestParam(defaultValue = "1") Integer index, @RequestParam(defaultValue = "10") Integer size){		
		PageInfo<InterFace> pagelist=iGetInterface.getInterfaceByPage(index, size);
		model.addAttribute("pagelist", pagelist);
		return new ModelAndView("admin/interface","model",model);

	}
	/**
	 * 增加接口
	 * @param model
	 * @param id
	 * @param url
	 * @param index
	 * @param size
	 * @return
	 */
	@RequestMapping("addInterface.do")
	public ModelAndView addInterface(Model model,@RequestParam(required = false) Integer id,@RequestParam(required = false) String url, @RequestParam(defaultValue = "1") Integer index, @RequestParam(defaultValue = "10") Integer size){		
		PageInfo<InterFace> pagelist=iGetInterface.getInterfaceByPage(index, size);
		model.addAttribute("pagelist", pagelist);
		return new ModelAndView("admin/interface/addinterface","model",model);

	}
	
}
