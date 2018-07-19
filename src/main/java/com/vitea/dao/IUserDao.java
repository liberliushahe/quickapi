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
	 * 
	 * @param username
	 * @return
	 */
	public User getUserByUsername(String username);
	/**
	 * {@docRoot}
	 * 
	 * @param id
	 * @return
	 * 
	 */
	public User getUserById(int id);

	/**
	 * {@docRoot}
	 * 
	 * @param user
	 */
	void save(User user);

	/**
	 * {@docRoot}
	 * 
	 * @param user
	 * @return
	 */
	boolean update(User user);

	/**
	 * {@docRoot}
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
