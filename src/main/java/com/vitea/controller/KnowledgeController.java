package com.vitea.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageInfo;
import com.vitea.domain.Knowledge;
import com.vitea.domain.User;
import com.vitea.endpoint.dto.PageResult;
import com.vitea.model.Response;
import com.vitea.service.IGetKnowledge;
/**
 * 接口控制器
 * @author liushahe
 *
 */
@Controller
public class KnowledgeController {
	private Logger logger = LoggerFactory.getLogger(KnowledgeController.class);
	@Autowired
	IGetKnowledge iGetKnowledge;
	/**
	 * 获取知识库界面
	 * @return
	 */
	@RequestMapping("getKnowledgePage")
	public String getKnowledgeList(){		
	  return "admin/knowledge/list";
	}
	
	
	/**
	 * 获取分页数据
	 * @param starttime
	 * @param endtime
	 * @param number
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("getKnowledgeListByPage")
	@ResponseBody
	public PageResult<Knowledge> getKnowledgeList(@RequestParam(defaultValue ="1") String starttime,@RequestParam(defaultValue = "1") String endtime,@RequestParam(defaultValue ="1") String title,@RequestParam(defaultValue = "0") int pageNumber,@RequestParam(defaultValue = "10") int pageSize){		
		PageInfo<Knowledge> pagelist=iGetKnowledge.findAllKnowledgeByParam(starttime, endtime, title,pageNumber,pageSize);		
		PageResult<Knowledge> pageResult=new PageResult<Knowledge>();
		Long total=pagelist.getTotal();
		pageResult.setTotal(total.intValue());
		pageResult.setRows(pagelist.getList());
		return pageResult;
		
	}
	/**
	 * 通过编号查询数据
	 * @param id
	 * @return
	 */
	@RequestMapping("getKnowledgeListById")
	@ResponseBody
	public Knowledge getKnowledgeById(@RequestParam(required = true) Short id){		
		return iGetKnowledge.getKnowledgeById(id);

	}
	/**
	 * 菜单增加知识库界面
	 * @param model
	 * @return
	 */
	@RequestMapping("addKnowledge")
	public ModelAndView addKnowledge(Model model){		
		model.addAttribute("pagelist", "1");
		return new ModelAndView("admin/knowledge/add","model",model);

	}
	/**
	 * 增加知识库信息
	 * @param model
	 * @param title
	 * @param author
	 * @param copyfrom
	 * @param content
	 * @return
	 */
	@RequestMapping("addFormKnowledge")
	public ResponseEntity<Response> addFormKnowledge(@RequestParam(required = true) String title,@RequestParam(required = true) String titledesc,@RequestParam(required = false) String copyfrom,@RequestParam(required = true) String content){
		Knowledge knowledge=new Knowledge();
		knowledge.setTitle(title);
		//获取用户信息
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		knowledge.setAuthor(user.getRealname());
		knowledge.setCopyfrom(copyfrom);
		knowledge.setTitledesc(titledesc);
		knowledge.setContent(content);
		knowledge.setTime(new Date());
		int i=iGetKnowledge.insert(knowledge);
		if(i==1) {
			return ResponseEntity.ok().body(new Response(true, "处理成功", "ok"));
		}else {
			logger.error("知识库增加出错,标题:"+knowledge.getTitle());
			return ResponseEntity.ok().body(new Response(false, "处理失败", "error"));
		}		
	}
	/**
	 * 删除数据
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteKnowledge")
	public ResponseEntity<Response> deleteKnowledge(@RequestParam(required = true) short id){
		int i=iGetKnowledge.deleteKnowledgeById(id);
		if(i==1) {
			return ResponseEntity.ok().body(new Response(true, "处理成功", "ok"));
		}else {
			logger.error("知识库删除出错,编号:"+id);
			return ResponseEntity.ok().body(new Response(false, "处理失败", "error"));
		}	

	}
	/**
	 * 编辑数据
	 * @param id
	 * @param articletitle
	 * @param titledesc
	 * @param copyfrom
	 * @param content
	 * @return
	 */
	@RequestMapping("editKnowledge")
	public ResponseEntity<Response> editKnowledge(@RequestParam(required = true) short id,@RequestParam(required = false) String articletitle,@RequestParam(required = false) String titledesc,@RequestParam(required = false) String copyfrom,@RequestParam(required = true) String content){		
		Knowledge knowledge=new Knowledge();
		knowledge.setId(id);
		knowledge.setTitle(articletitle);
		knowledge.setTitledesc(titledesc);
		knowledge.setCopyfrom(copyfrom);
		knowledge.setContent(content);
		int i=iGetKnowledge.editKnowledgeById(knowledge);
		if(i==1) {
			return ResponseEntity.ok().body(new Response(true, "处理成功", "ok"));
		}else {
			logger.error("知识库编辑出错,标题:"+knowledge.getTitle());
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

	@RequestMapping(value = "knowledge/detail", method = RequestMethod.GET)
	public ModelAndView getDetail(Model model,@RequestParam(required = true) String id){
		//文章点击量
		iGetKnowledge.hits(Short.parseShort(id));
		Knowledge knowledge=iGetKnowledge.getKnowledgeById(Short.valueOf(id));
		model.addAttribute("knowledge", knowledge);
		return new ModelAndView("admin/knowledge/detail","model",model);

	}
}
