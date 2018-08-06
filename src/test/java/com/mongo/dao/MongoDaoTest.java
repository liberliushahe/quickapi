package com.mongo.dao;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.mongo.domain.InterfaceLog;

/**
 * 
 * @author liushahe
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mongo.xml")
public class MongoDaoTest {
	@Autowired
	protected InterfaceLogDao interfaceLogDao;
	@Test
	public void getCollectionSize(){
		List<InterfaceLog> list=interfaceLogDao.findForRequery("18919927184");
		System.out.println(list.size());
	 
     
		
	}
}
