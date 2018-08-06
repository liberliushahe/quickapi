package com.mongo.dao;

import java.util.List;

import com.mongo.domain.InterfaceLog;
/**
 * 接口日志查询 FROM MONGO
 * @author liushahe
 *
 */
public interface InterfaceLogDao {
	/**
	 * 条件查询
	 * @param accnum
	 * @return
	 */
	public List<InterfaceLog> findForRequery(String accnum); 
	/**
	 * 查询所有记录
	 * @return
	 */
	public List<InterfaceLog> findAll(); 
}
