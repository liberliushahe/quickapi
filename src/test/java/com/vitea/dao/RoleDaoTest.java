package com.vitea.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.vitea.domain.Role;

/**
 * 
 * @author liushahe
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-db.xml")
public class RoleDaoTest {
	@Autowired
	private IRoleDao iRoleDao;
	@Test
	public void getRole(){
		//通过编号资源
		Role role=iRoleDao.selectByPrimaryKey(new Short("1"));
		System.out.println(role.toString());
		
	}

}
