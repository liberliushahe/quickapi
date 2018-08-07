package com.vitea.service;

import java.util.List;

import com.mongo.domain.InterfaceLog;

/**
 * 获取接口日志
 * @author liushahe
 *
 */
public interface IGetInterfaceLog {
	/**
	 * 通过主键获取日志信息
	 * @param key
	 * @return
	 */
	public List<InterfaceLog> getInterfaceLogByKey(String key);

}
