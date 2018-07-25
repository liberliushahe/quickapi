package com.vitea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vitea.dao.IUserDao;
import com.vitea.domain.User;
import com.vitea.service.IGetUserList;
@Service
@Transactional
public class GetUserListImpl implements IGetUserList {
    @Autowired
    IUserDao iUserDao;
	@Override
	public PageInfo<User> getPageOfUserList(int index, int size) {
		 PageHelper.startPage(index, size);
		 List<User> list=iUserDao.findAll();
	     PageInfo<User> pageUser = new PageInfo<User>(list);
		 return pageUser;
	}

}
