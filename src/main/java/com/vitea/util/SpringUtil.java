package com.vitea.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * get bean from applicatincontext
 * @author liushahe
 *
 */
public class SpringUtil implements ApplicationContextAware {  
	  
    private static ApplicationContext applicationContext;  
  
    public static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    }  
  @Override
    public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {  
        SpringUtil.applicationContext = applicationContext;  
    }  
}  