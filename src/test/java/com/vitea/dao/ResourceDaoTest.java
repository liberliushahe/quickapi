package com.vitea.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.vitea.domain.Resource;

/**
 * 
 * @author liushahe
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-db.xml")
public class ResourceDaoTest {
	@Autowired
	private IResourceDao iResourceDao;
	@Test
	public void getPort(){
		//通过编号获取接口
		Resource res=iResourceDao.selectByPrimaryKey(new Short("1"));
		System.out.println(res.toString());
		
	}

}
