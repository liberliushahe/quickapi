package com.vitea.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vitea.domain.Knowledge;

/**
 * 
 * @author liushahe
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-db.xml")
public class KnowledgeDaoTest {
    @Autowired
	IKnowledgeDao iKnowledgeDao;
	@Test
	public void getKnowledge(){
		Knowledge know=iKnowledgeDao.getKnowledgeById(new Short("1"));
		System.out.print(know.toString());
	}

}
