package com.vitea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongo.dao.InterfaceLogDao;
import com.mongo.domain.InterfaceLog;
import com.vitea.service.IGetInterfaceLog;
/**
 * 接口日志实现类
 * @author liushahe
 *
 */
@Service
public class GetInterfaceLogImpl implements IGetInterfaceLog {
	@Autowired
	protected InterfaceLogDao interfaceLogDao;
	@Override
	public List<InterfaceLog> getInterfaceLogByKey(String start, String end, String accnum, int index, int size) {
		return interfaceLogDao.findForRequeryByParam(start, end, accnum, index, size);
	}
	@Override
	public long findAllCount(String start,String end,String accnum) {
		return interfaceLogDao.findAllCount(start,end,accnum);
	}

}
