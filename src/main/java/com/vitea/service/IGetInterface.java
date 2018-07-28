package com.vitea.service;

import com.github.pagehelper.PageInfo;
import com.vitea.domain.InterFace;
/**
 * 分页查询
 * @author liushahe
 *
 */
public interface IGetInterface {
	/**
	 * 分页查询接口
	 * @param index
	 * @param size
	 * @return
	 */
	public PageInfo<InterFace> getInterfaceByPage(int index,int size);
	/**
	 * 通过id获取接口信息
	 * @param id
	 * @return
	 */
	public InterFace getInterfaceById(int id);
	

}
