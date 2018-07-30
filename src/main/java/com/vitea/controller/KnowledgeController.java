package com.vitea.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.vitea.domain.Knowledge;
import com.vitea.model.Response;
import com.vitea.service.IGetKnowledge;
/**
 * 接口控制器
 * @author liushahe
 *
 */
@Controller
public class KnowledgeController {
	@Autowired
	IGetKnowledge iGetKnowledge;
	
	/**
	 * 获取接口列表
	 * @param model
	 * @param id
	 * @param url
	 * @param index
	 * @param size
	 * @return
	 */
	@RequestMapping("getKnowledgeList")
	public ModelAndView getKnowledgeList(Model model,@RequestParam(required = false) Integer id,@RequestParam(required = false) String title, @RequestParam(defaultValue = "1") Integer index, @RequestParam(defaultValue = "10") Integer size){		
		PageInfo<Knowledge> pagelist=iGetKnowledge.getKnowledgeByPage(index, size);
		model.addAttribute("pagelist", pagelist);
		return new ModelAndView("admin/knowledge/list","model",model);

	}
	/**
	 * 菜单按钮
	 * @param model
	 * @return
	 */
	@RequestMapping("addKnowledge")
	public ModelAndView addKnowledge(Model model){		
		model.addAttribute("pagelist", "1");
		return new ModelAndView("admin/knowledge/add","model",model);

	}
	/**
	 * 提交表单信息
	 * @param model
	 * @param title
	 * @param author
	 * @param copyfrom
	 * @param content
	 * @return
	 */
	@RequestMapping("addFormKnowledge")
	public ResponseEntity<Response> addFormKnowledge(Model model,@RequestParam(required = false) String title,@RequestParam(required = false) String author,@RequestParam(required = false) String copyfrom,@RequestParam(required = false) String content){
		Knowledge knowledge=new Knowledge();
		knowledge.setTitle(title);
		knowledge.setAuthor(author);
		knowledge.setCopyfrom(copyfrom);
		knowledge.setContent(content);
		knowledge.setTime(new Date());
		int i=iGetKnowledge.insert(knowledge);
		if(i==1) {
			return ResponseEntity.ok().body(new Response(true, "处理成功", "ok"));
		}else {
			return ResponseEntity.ok().body(new Response(false, "处理失败", "error"));
		}
		

	}

	/**
	 * 开放界面知识库列表
	 * @param model
	 * @return
	 */
	@RequestMapping("knowledge/list")
	public ModelAndView getKnowledge(Model model,@RequestParam(defaultValue = "1") Integer index, @RequestParam(defaultValue = "10") Integer size){		
		PageInfo<Knowledge> pagelist=iGetKnowledge.getKnowledgeByPage(index, size);
		model.addAttribute("pagelist", pagelist);
		return new ModelAndView("admin/knowledge/knowledge","model",model);

	}
	/**
	 * 开放界面知识库详情
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("knowledge/detail")
	public ModelAndView getDetail(Model model,@RequestParam(required = true) String id){		
		Knowledge knowledge=iGetKnowledge.getKnowledgeById(Short.valueOf(id));
		model.addAttribute("knowledge", knowledge);
		return new ModelAndView("admin/knowledge/detail","model",model);

	}
}
