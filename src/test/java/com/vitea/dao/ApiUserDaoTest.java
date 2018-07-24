package com.vitea.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vitea.domain.ApiUser;

/**
 * 
 * @author liushahe
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-db.xml")
public class ApiUserDaoTest {
	@Autowired
	private IApiUserDao Dao;
	@Test
	public void getApiUser(){
	
		ApiUser a=Dao.selectByPrimaryKey("ycl");
		System.out.println(a);
		
	}

}
