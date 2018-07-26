package com.vitea.dao;

import com.vitea.domain.ApiUser;

/**
 * 接口用户dao
 * 
 * @author liushahe
 *
 */
public interface IApiUserDao {

	/**
	 * 通过主键删除
	 * 
	 * @param type
	 * @return
	 */
	int deleteByPrimaryKey(String type);

	/**
	 * 增加日志
	 * 
	 * @param record
	 * @return
	 */
	int insert(ApiUser record);

	/**
	 * 通过主键查询
	 * 
	 * @param type
	 * @return
	 */
	ApiUser selectByPrimaryKey(String type);

	/**
	 * 通过主键更新
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(ApiUser record);

}