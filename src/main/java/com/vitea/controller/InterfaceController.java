package com.vitea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.vitea.domain.InterFace;
import com.vitea.model.Response;
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
	
	/**
	 * 获取接口列表
	 * @param model
	 * @param id
	 * @param url
	 * @param index
	 * @param size
	 * @return
	 */
	@RequestMapping("getInterfaceList")
	public ModelAndView getIndex(Model model,@RequestParam(required = false) Integer id,@RequestParam(required = false) String url, @RequestParam(defaultValue = "1") Integer index, @RequestParam(defaultValue = "10") Integer size){		
		PageInfo<InterFace> pagelist=iGetInterface.getInterfaceByPage(index, size);
		model.addAttribute("pagelist", pagelist);
		return new ModelAndView("admin/interface/list","model",model);

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
	@RequestMapping("/addInterface")
	public String addInterface(Model model,@RequestParam(required = false) Integer id,@RequestParam(required = false) String url, @RequestParam(defaultValue = "1") Integer index, @RequestParam(defaultValue = "10") Integer size){		
		PageInfo<InterFace> pagelist=iGetInterface.getInterfaceByPage(index, size);
		model.addAttribute("pagelist", pagelist);
		return "admin/interface/add";

	}
	/**
	 * 编辑接口
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/editInterface")
	public ModelAndView editInterface(@RequestParam(required = false) Integer id,Model model){	
		InterFace inter=iGetInterface.getInterfaceById(id);
		model.addAttribute("interface", inter);
		return new ModelAndView("admin/interface/edit","interfaceModel",model);
       
	}
	
	@RequestMapping("/updateInterface")
	public ResponseEntity<Response> updateInterface(InterFace interFace){	
		
		return ResponseEntity.ok().body(new Response(true, "处理成功", "ok"));
	}
}
