package com.mongo.dao;

import java.util.Iterator;
import java.util.List;

import com.mongo.domain.InterfaceLog;
import com.mongodb.BasicDBObject;
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
	/**
	 * 统计调用成功数量
	 * @return
	 */
	public long successCount();
	/**
	 * 统计调用失败数量
	 * @return
	 */
	public long failCount();
	/**
	 * 通过区域编码分组统计
	 */
	public Iterator<BasicDBObject> groupByMapReduceOfAreaCode();
	/**
	 * 通过产品编码分组统计
	 */
	public Iterator<BasicDBObject> groupByMapReduceOfProduct();
	/**
	 * 近期调用成功量
	 * @return
	 */
	public Iterator<BasicDBObject> daySuccessTrans();
	/**
	 * 近期调用失败量
	 * @return
	 */
	public Iterator<BasicDBObject> dayFailTrans();
	/**
	 * 当月30天内接口调用情况
	 * @return
	 */
	public Iterator<BasicDBObject> currentTrans(String flag);
	
}
