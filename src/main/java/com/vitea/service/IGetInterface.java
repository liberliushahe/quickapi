package com.vitea.service;

import com.github.pagehelper.PageInfo;
import com.vitea.domain.InterFace;

public interface IGetInterface {
	/**
	 * 分页查询接口
	 * @param index
	 * @param size
	 * @return
	 */
	public PageInfo<InterFace> getInterfaceByPage(int index,int size);
	

}
