package com.vitea.service;

import com.github.pagehelper.PageInfo;
import com.vitea.domain.User;
/**
 * user
 * @author liushahe
 *
 */
public interface IGetUserList {
	/**
	 * 获取分页用户
	 * @param index
	 * @param size
	 * @return
	 */
	public PageInfo<User> getPageOfUserList(int index,int size);

}
