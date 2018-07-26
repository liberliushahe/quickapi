package com.vitea.service;

import com.vitea.domain.ApiUser;
/**
 * user api
 * @author liushahe
 *
 */
public interface IApiUserInfo {
	/**
	 * 查询用户信息
	 * @param type
	 * @return
	 */
	public ApiUser getUserByPrimary(String type);

}
