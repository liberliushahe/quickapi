package com.vitea.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vitea.domain.Userlog;


/**
 * 
 * @author liushahe
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-db.xml")
public class UserLogDaoTest {
	@Autowired
	private IUserLogDao iUserLogDao;
	/**
	 * 根据编号查询用户日志信息
	 */
	@Test
	public void getUserLogByUsername(){
		Userlog u=iUserLogDao.selectUserLogByUsername("admin");
		System.out.println(u.toString());
		
	}	
}
