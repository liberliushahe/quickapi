package com.vitea.dao;

import com.vitea.domain.Role;

/**
 * 角色操作
 * 
 * @author liushahe
 *
 */
public interface IRoleDao {
	/**
	 * 通过主键删除
	 * 
	 * @param rid
	 * @return
	 */
	int deleteByPrimaryKey(Short rid);

	/**
	 * 插入角色
	 * 
	 * @param record
	 * @return
	 */
	int insert(Role record);

	/**
	 * 通过主键查询
	 * 
	 * @param rid
	 * @return
	 */
	Role selectByPrimaryKey(Short rid);

	/**
	 * 更新记录
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(Role record);
}