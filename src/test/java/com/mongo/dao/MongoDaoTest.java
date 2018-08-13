package com.mongo.dao;


import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
    @Autowired
    MongoTemplate mongoTemplate;
	
	@Test
	public void getCollectionByPage(){
	    String starttime="2018-08-08 12:00:00";
	    String endtime="2018-08-08 18:00:00";
        Query query = new Query();
        query.addCriteria(Criteria.where("accnum").is("18919927184"));
        query.addCriteria(Criteria.where("timestamp").gte(starttime).lt(endtime));
        Pageable pageable = new PageRequest(1, 10); 
        query.with(pageable);
        // 排序
        query.with(new Sort(Direction.ASC,"timestamp"));
        List<InterfaceLog> list=mongoTemplate.find(query,InterfaceLog.class, "logback");
		Iterator<InterfaceLog> it=list.iterator();
	    while(it.hasNext()) {
	    	System.out.println(it.next().getAccnum());
	    }	
	}
	@Test
	public void findAllCount() {
        Query query = new Query();
        query.addCriteria(Criteria.where("accnum").is("18919927184"));
		System.out.println(mongoTemplate.count(query, InterfaceLog.class,"logback"));
	
	}
}
