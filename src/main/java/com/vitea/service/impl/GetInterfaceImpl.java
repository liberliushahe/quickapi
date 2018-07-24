package com.vitea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vitea.dao.InterfaceDao;
import com.vitea.domain.InterFace;
import com.vitea.service.IGetInterface;
@Service
public class GetInterfaceImpl implements IGetInterface {
    @Autowired
    InterfaceDao interfaceDao;
	@Override
	public PageInfo<InterFace> getInterfaceByPage(int index, int size) {
		  PageHelper.startPage(index, size);
	      List<InterFace> list = interfaceDao.findAll();
	      PageInfo<InterFace> pageInfo = new PageInfo<>(list);
		  return pageInfo;
	}

}
