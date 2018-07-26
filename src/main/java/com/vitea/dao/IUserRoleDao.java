package com.vitea.dao;

import java.util.List;

import com.vitea.domain.UserRole;

/**
 * 用户角色操作接口
 * 
 * @author liushahe
 *
 */
public interface IUserRoleDao {
	/**
	 * 通过主键删除
	 * 
	 * @param urid
	 * @return
	 */
	int deleteByPrimaryKey(Short urid);

	/**
	 * 插入记录
	 * 
	 * @param record
	 * @return
	 */
	int insert(UserRole record);

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	List<UserRole> selectAllUserRoleByPage();

	/**
	 * 通过主键查询
	 * 
	 * @param urid
	 * @return
	 */
	UserRole selectByPrimaryKey(Short urid);

	/**
	 * 通过姓名查询
	 * 
	 * @param urid
	 * @return
	 */
	UserRole selectByUsername(String urid);

	/**
	 * 更新记录
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(UserRole record);
}