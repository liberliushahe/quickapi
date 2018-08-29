package com.vitea.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.vitea.domain.InterFace;
import com.vitea.endpoint.dto.PageResult;
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
	 * 获取接口界面
	 * @return
	 */
	@RequestMapping("getInterfaceList")
	public String getIndex(){		
		return "admin/interface/list";

	}
	/**
	 * 获取分页接口数据
	 * @param id
	 * @param method
	 * @param name
	 * @param index
	 * @param size
	 * @return
	 */
	@RequestMapping("getInterfaceListByPage")
	@ResponseBody()
	public PageResult<InterFace> getInterfaceListByPage(@RequestParam(defaultValue = "0",required = false) int interfaceid,@RequestParam(defaultValue = "0",required = false) String interfacemethod,@RequestParam(defaultValue = "0",required = false) String interfacename, @RequestParam(defaultValue = "1") Integer index, @RequestParam(defaultValue = "10") Integer size){		
		PageInfo<InterFace> pagelist=iGetInterface.findAllInterfaceByParam(interfaceid, interfacemethod, interfacename,index,size);		
		PageResult<InterFace> pageResult=new PageResult<InterFace>();
		Long total=pagelist.getTotal();
		pageResult.setTotal(total.intValue());
		pageResult.setRows(pagelist.getList());
		return pageResult;
	}
	/**
	 * 通过编号查询接口信息
	 * @param id
	 * @return
	 */
	@RequestMapping("getInterfaceById")
	@ResponseBody()
	public InterFace getInterfaceById(@RequestParam(required = true) int id){
		return iGetInterface.getInterfaceById(id);
	}
	/**
	 * 增加接口
	 * @param interFace
	 * @return
	 */
	@RequestMapping("addInterface")
	public ResponseEntity<Response> addInterface(InterFace interFace){
		int i=iGetInterface.addInterface(interFace);
		if(i==1) {
			 return ResponseEntity.ok().body(new Response(true, "处理成功", "ok"));
		}else {
			 return ResponseEntity.ok().body(new Response(false, "处理失败", "no"));
		}
	}
	/**
	 * 更新接口
	 * @param interFace
	 * @return
	 */
	@RequestMapping("updateInterface")
	public ResponseEntity<Response> updateInterface(InterFace interFace){
		boolean b=iGetInterface.updateInterface(interFace);
		if(b) {
			  return ResponseEntity.ok().body(new Response(true, "处理成功", "ok"));
		}else {
			  return ResponseEntity.ok().body(new Response(false, "处理失败", "ok"));
		}
	}
	/**
	 * 删除接口
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteInterface")
	public ResponseEntity<Response> deleteInterface(@RequestParam(required = true) int id){	
	boolean b=	iGetInterface.deleteInterface(id);
	if(b) {
		  return ResponseEntity.ok().body(new Response(true, "处理成功", "ok"));

	}else {
		  return ResponseEntity.ok().body(new Response(false, "处理失败", "ok"));

	}
	}
	@RequestMapping("interfaceScatter")
	@ResponseBody()
	public Object interfaceScatter() {
		List<Map<String,String>> list=iGetInterface.queryInterfaceScatter();
		return JSONObject.toJSON(list);
		
	}
	
}
