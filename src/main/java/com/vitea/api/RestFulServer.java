package com.vitea.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vitea.domain.User;

/**
 * 对外提供API服务
 * @author liushahe
 *
 */
@Controller
public class RestFulServer {
	Map<String,String> map=new HashMap<String,String>();
	
	@RequestMapping(value = "/getsheet/{id}", method = RequestMethod.GET)
    public @ResponseBody User sayHello(@PathVariable String id) {
		
		User u=new User();
		u.setEmail("11111");
		u.setUrl("http:kkkkk");
        return u;
    }


}
