package com.vitea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitea.dao.IApiUserDao;
import com.vitea.domain.ApiUser;
import com.vitea.service.IApiUserInfo;
@Service
public class ApiUserInfoImpl implements IApiUserInfo {
	@Autowired
    IApiUserDao iApiUserDao;
	@Override
	public ApiUser getUserByPrimary(String type) {
		return iApiUserDao.selectByPrimaryKey(type);
	}
	

}
