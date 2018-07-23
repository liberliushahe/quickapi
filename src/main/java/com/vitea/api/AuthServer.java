package com.vitea.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.vitea.model.ResultMsg;
import com.vitea.service.IApiUserInfo;
import com.vitea.util.IpAddressUtil;
import com.vitea.domain.ApiUser;
import com.vitea.model.QToken;

/**
 * 对外提供API服务token获取
 * @author liushahe
 *
 */
@Controller
public class AuthServer {
	@Autowired
	IApiUserInfo iApiUserInfo;
	Map<String,String> map=new HashMap<String,String>();
	/**
	 * 客户端获取token
	 * @param grant_type
	 * @param appId
	 * @param appSecret
	 * @return
	 */
	@RequestMapping(value = "/token", method = RequestMethod.GET)
	@ResponseBody
    public  Object GetToken(HttpServletRequest request) {
		String grant_type=request.getParameter("grant_type");
		String appId=request.getParameter("appId");
		String appSecret=request.getParameter("appSecret");
	    ResultMsg msg=new ResultMsg();
	    QToken token=new QToken();
	    //判断用户请求数据是否为空
		if(StringUtils.isEmpty(grant_type)) {
			msg.setErrcode("100");
			msg.setErrmsg("不能为空");
	    	return msg;
	    }
		if(StringUtils.isEmpty(appId)) {
			msg.setErrcode("101");
			msg.setErrmsg("不能为空");
			return msg;
	    }
		if(StringUtils.isEmpty(appSecret)) {
			msg.setErrcode("102");
			msg.setErrmsg("不能为空");
			return msg;
	    }
        //判断用户是否在认证服务器中注册用户名和密码用户请求IP是否在白名单
		int code=checkUser(request, grant_type, appId,appSecret);
		if(code==1) {
			token.setAccess_token("HYHIJIUHYGGTFSTSFSTG");
			token.setExpires_in("7200");
	        return token;
		}else {
			msg.setErrcode("1");
			msg.setErrmsg("认证失败");
			return msg;
		}
		
    }
    /**
     * 校验用户是否认证
     * @param appid
     * @param appsecret
     * @return
     */
	public int checkUser(HttpServletRequest request,String grant_type,String appid,String appsecret) {
		//查询用户是否注册IP
		ApiUser apiUser=iApiUserInfo.getUserByPrimary(grant_type);
		if(apiUser==null) {
			return 0;
		}else {
		//判断用户名和密码是否正确
			if(!matchUserAndPass(apiUser,appid,appsecret)) {
				return 3;
			}
		}
		//获取用户设置白名单
		String ip=IpAddressUtil.getIpAddress(request);
		String whitelist=apiUser.getWhitelist();
		
		String[] list=whitelist.split(",");
		if(!checkWhiteList( list,ip)) {
			return 2;
		}
		
		return 1;
	}
	/**
	 * 通过用户信息生成token并返回
	 * @param appid
	 * @param appsecret
	 * @param token
	 * @return
	 */
	public String createToken(String appid,String appsecret,String token) {
		return token;
		
	}
	/**
	 * 检查是否是白名单
	 * @param list
	 * @param ip
	 * @return
	 */
	public boolean checkWhiteList(String[] list,String ip) {
		for(String requestip :list) {
			if(requestip.equals(ip)) {
				return true;
			}
		}
		return false;		
	}
	/**
	 * 匹配用户appid and secret
	 * @param user
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	public boolean matchUserAndPass(ApiUser user,String appid,String appsecret) {
		if(user.getAppid().equals(appid) && user.getAppsecret().equals(appsecret)) {
			return true;
		}
		return false;	
	}

}
