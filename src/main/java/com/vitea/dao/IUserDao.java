package com.vitea.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import com.vitea.domain.User;

/**
 * 
 * @author liushahe {@docRoot}
 */
@MapperScan
@Repository
public interface IUserDao {
	/**
	 * 获取用户信息
	 * @param username
	 * @return
	 */
	public User getUserByUsername(String username);
	/**
	 * 通过id获取用户信息
	 * 
	 * @param id
	 * @return
	 * 
	 */
	public User getUserById(int id);

	/**
	 * 保存用户
	 * 
	 * @param user
	 */
	void save(User user);

	/**
	 * 更新用户
	 * 
	 * @param user
	 * @return
	 */
	boolean update(User user);

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(int id);

	/**
	 * {@docRoot}
	 * 
	 * @return
	 */
	List<User> findAll();
}
