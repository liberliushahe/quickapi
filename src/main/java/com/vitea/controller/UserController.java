package com.vitea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageInfo;
import com.vitea.domain.User;
import com.vitea.endpoint.dto.PageResult;
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
	public String getIndex(){		
		return "admin/user/userinfo";
	}
	
	@RequestMapping("getUserListByPage")
	@ResponseBody()
	public PageResult<User> getUserListByPage(@RequestParam(defaultValue = "0",required = false) String username,@RequestParam(defaultValue = "0",required = false) String realname, @RequestParam(defaultValue = "1") Integer index, @RequestParam(defaultValue = "10") Integer size){		
		PageInfo<User> pagelist=iGetUserList.findAllUserByParam(username,realname,index,size);		
		PageResult<User> pageResult=new PageResult<User>();
		Long total=pagelist.getTotal();
		pageResult.setTotal(total.intValue());
		pageResult.setRows(pagelist.getList());
		return pageResult;
	}
	
	
}
