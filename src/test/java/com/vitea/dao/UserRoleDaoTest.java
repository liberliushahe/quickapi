package com.vitea.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vitea.domain.UserRole;

/**
 * 
 * @author liushahe
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-db.xml")
public class UserRoleDaoTest {
	@Autowired
	private IUserRoleDao iUserRoleDao;
	@Test
	public void getUserRole(){
		//通过编号获取接口
		UserRole userRole=iUserRoleDao.selectByPrimaryKey(new Short("1"));
		System.out.println(userRole.toString());
		
	}

}
