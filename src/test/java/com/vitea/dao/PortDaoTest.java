package com.vitea.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vitea.domain.InterFace;

/**
 * 
 * @author liushahe
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-db.xml")
public class PortDaoTest {
	@Autowired
	private InterfaceDao iPortDao;
	@Test
	public void getPort(){
		//通过编号获取接口
		InterFace inter=iPortDao.getPortById(201801);
		System.out.println(inter.toString());
		
	}

}
