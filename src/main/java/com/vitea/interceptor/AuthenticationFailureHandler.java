package com.vitea.interceptor;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.vitea.util.IpAddressUtil;
/**
 * 拦截失败请求
 * @author liushahe
 *
 */
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	private static Logger logger = LoggerFactory.getLogger(UserAuthenticationSuccessHandler.class);

	public AuthenticationFailureHandler(){
    	this.setDefaultFailureUrl("/login.do?error=true");
    }
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		    logger.info("认证失败信息:{},时间:{},ip:{}", new Object[] {exception.getMessage(),new Date(),IpAddressUtil.getIpAddress(request)});
		    super.onAuthenticationFailure(request, response, exception);
	}
	
	
	

}
