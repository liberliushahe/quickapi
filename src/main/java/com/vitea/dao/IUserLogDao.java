package com.vitea.dao;

import com.vitea.domain.Userlog;

/**
 * 用户登录日志接口
 * @author liushahe
 *
 */
public interface IUserLogDao {
	/**
	 * 通过主键删除
	 * @param id
	 * @return
	 */
	 int deleteByPrimaryKey(Integer id);
	 /**
	  * 插入日志
	  * @param record
	  * @return
	  */
	 int insert(Userlog record);
	
	 /**
	  * 通过用户名查询日志
	  * @param username
	  * @return
	  */
	 Userlog selectUserLogByUsername(String username);

}
