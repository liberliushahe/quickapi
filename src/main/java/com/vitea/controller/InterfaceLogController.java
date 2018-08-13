package com.vitea.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongo.domain.InterfaceLog;
import com.vitea.endpoint.dto.PageResult;
import com.vitea.service.IGetInterfaceLog;
/**
 * 日志查询控制器
 * @author liushahe
 *
 */
@Controller
public class InterfaceLogController {
	@Autowired
	IGetInterfaceLog iGetInterfaceLog;
	/**
	 * 管理界面首页
	 * @return
	 */
	@RequestMapping("/getInterfaceLog")
	public String getInterfaceLog() {
		return "admin/interfacelog/logger";
	}
	@ResponseBody
	@RequestMapping("/getInterfaceLogByPage")
	public PageResult<InterfaceLog> getInterfaceLogByPage(@RequestParam(defaultValue = "null") String starttime,@RequestParam(defaultValue = "null") String endtime,@RequestParam(defaultValue = "null") String number,@RequestParam(defaultValue = "0") int pageNumber,@RequestParam(defaultValue = "10") int pageSize) {      
		Long count=iGetInterfaceLog.findAllCount(starttime, endtime, number);
		List<InterfaceLog> list=iGetInterfaceLog.getInterfaceLogByKey(starttime, endtime, number, pageNumber, pageSize);
		PageResult<InterfaceLog> pageResult=new PageResult<InterfaceLog>();
		pageResult.setTotal(count.intValue());
		pageResult.setRows(list);
	    return pageResult;
	}
}