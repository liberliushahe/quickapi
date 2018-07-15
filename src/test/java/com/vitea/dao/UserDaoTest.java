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
	@Test
	public void getUser(){
		User u=iUserDao.getUserById("1");
		System.out.println(u.getUsername());
		
	}

}
