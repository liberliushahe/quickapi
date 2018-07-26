package com.vitea.dao;

import java.util.List;

import com.vitea.domain.RoleResource;

/**
 * 角色资源操作
 * 
 * @author liushahe
 *
 */
public interface IRoleResourceDao {
	/**
	 * 通过主键删除
	 * 
	 * @param rrid
	 * @return
	 */
	int deleteByPrimaryKey(Short rrid);

	/**
	 * 插入记录
	 * 
	 * @param record
	 * @return
	 */
	int insert(RoleResource record);

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	List<RoleResource> selectAllRoleResourceByPage();

	/**
	 * 通过主键查询
	 * 
	 * @param rrid
	 * @return
	 */
	RoleResource selectByPrimaryKey(Short rrid);

	/**
	 * 通过主键更新记录
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(RoleResource record);
}