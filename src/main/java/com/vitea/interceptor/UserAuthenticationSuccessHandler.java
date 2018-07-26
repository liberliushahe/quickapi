package com.vitea.interceptor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.vitea.dao.IUserLogDao;
import com.vitea.domain.User;
import com.vitea.domain.Userlog;
import com.vitea.util.DateFormatUtil;
import com.vitea.util.IpAddressUtil;
/**
 * 用户成功登陆后执行操作
 * 记录登录日志
 * @author liushahe
 *
 */
@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
    @Autowired
	IUserLogDao iUserLogDao;
	private static Logger logger = LoggerFactory.getLogger(UserAuthenticationSuccessHandler.class);
    /**
     * 登录成功跳转url
     */
	private String defaultTargetUrl;

	/**
	 * 是否重定向
	 */
	private boolean forwardToDestination = false;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		//保存登录信息
		this.saveUserLoginInfo(request, authentication);
		// 认证成功后，获取用户信息并添加到session中
		//UserDetails user = (UserDetails) authentication.getPrincipal();
		//request.getSession().setAttribute("user", user);

		if(this.forwardToDestination) {
			request.getRequestDispatcher(this.defaultTargetUrl).forward(request, response);
		}else {
			this.redirectStrategy.sendRedirect(request, response, this.defaultTargetUrl);
		}
	}

    public void saveUserLoginInfo(HttpServletRequest request,Authentication authentication) {
    	User user = (User)authentication.getPrincipal();	
		String ip =IpAddressUtil.getIpAddress(request);
		Userlog userlog=new Userlog();
		userlog.setId(2);
		userlog.setIp(ip);
		userlog.setLatesttime(DateFormatUtil.getFormatDate());
		userlog.setUsername(user.getUsername());
		logger.info("登录用户名{},登录ip:{},登录时间:{}", new Object[]{user.getUsername(),ip,DateFormatUtil.getFormatDate()});
		iUserLogDao.insert(userlog);  	
    }
	public void setDefaultTargetUrl(String defaultTargetUrl) {
		this.defaultTargetUrl = defaultTargetUrl;
	}
	public void setForwardToDestination(boolean forwardToDestination) {
		this.forwardToDestination = forwardToDestination;
	}

    
}
