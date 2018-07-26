package com.vitea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitea.dao.IApiUserDao;
import com.vitea.domain.ApiUser;
import com.vitea.service.IApiUserInfo;
/**
 * userinfo api
 * @author liushahe
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class ApiUserInfoImpl implements IApiUserInfo {
	@Autowired
    IApiUserDao iApiUserDao;
	@Override
	public ApiUser getUserByPrimary(String type) {
		return iApiUserDao.selectByPrimaryKey(type);
	}
	

}
