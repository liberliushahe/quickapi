package com.vitea.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vitea.domain.User;


/**
 * 
 * @author liushahe
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-db.xml")
public class UserDaoTest {
	@Autowired
	private IUserDao iUserDao;
	/**
	 * 根据编号查询用户信息
	 */
	@Test
	public void getUserById(){
		//User u=iUserDao.getUserById(1);
		//System.out.println(u.toString());
		
	}
	/**
	 * 根据用户username 查询用户信息
	 */
	@Test
	public void getUserByUsername(){
		//User u=iUserDao.getUserByUsername("admin");
		//System.out.println(u.toString());
		
	}
}
