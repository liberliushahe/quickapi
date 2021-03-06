package com.vitea.controller;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

/**
 * 将ContextPath写入application中，给静态文件引用时用、及URL链接地址用
 * @author liushahe
 *
 */
@Component
public class ApplicationContext implements ServletContextAware {

    @Override
    public void setServletContext(ServletContext context) {
        String ctx = context.getContextPath();
        context.setAttribute("ctx", ctx);
    }
 
}