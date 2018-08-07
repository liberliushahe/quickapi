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
	public List<InterfaceLog> getInterfaceLogByKey(String key) {
		System.out.println("hrhhrrh"+interfaceLogDao);
		return interfaceLogDao.findForRequery(key);
	}

}
