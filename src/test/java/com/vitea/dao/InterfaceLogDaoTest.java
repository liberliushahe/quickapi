package com.vitea.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vitea.domain.InterfaceLog;
import com.vitea.util.DateFormatUtil;

/**
 * 
 * @author liushahe
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-db.xml")
public class InterfaceLogDaoTest {
	@Autowired
	private InterfaceLogDao interfaceLogDao;
	@Test
	public void addInterfaceLog(){
		InterfaceLog record=new InterfaceLog();
		record.setUrl("http:192.168.0.1");
		record.setInterfaceId("1");
		record.setInParam("name='libai'");
		record.setOutParam("age:12");
		record.setTransferTime(DateFormatUtil.getFormatDate());
		record.setUsedTime("20");
		int i=interfaceLogDao.insert(record);
		System.out.println(i);
		
	}

}
