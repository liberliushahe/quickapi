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
      * @param start
      * @param end
      * @param accnum
      * @param index
      * @param size
      * @return
      */
	public List<InterfaceLog> getInterfaceLogByKey(String start, String end, String accnum, int index, int size);
     /**
      * 查询总记录
      * @param start
      * @param end
      * @param accnum
      * @return
      */
	public long findAllCount(String start,String end,String accnum);
}
