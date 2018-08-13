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
 * 根据时间周期和号码查询分页展示
 * @param start
 * @param end
 * @param accnum
 * @param index
 * @param size
 * @return
 */
	public List<InterfaceLog> findForRequeryByParam(String start,String end,String accnum,int index,int size);
 /**
  * 返回记录总数
  * @param start
  * @param end
  * @param accnum
  * @return
  */
	public long findAllCount(String start,String end,String accnum);

}
