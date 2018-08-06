package com.mongo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mongo.dao.InterfaceLogDao;
import com.mongo.domain.InterfaceLog;
/**
 * mongodb数据库操作
 * @author liushahe
 *
 */
@Component("InterfaceLogDaoImpl") 
public class InterfaceLogDaoImpl  implements InterfaceLogDao {
    @Autowired
    MongoTemplate mongoTemplate;
	@Override
	public List<InterfaceLog> findForRequery(String accnum) {	
		System.out.println(mongoTemplate);
		    Query query = new Query();  
	        Criteria criteria = new Criteria("accnum");  
	        criteria.is(accnum);  
	        query.addCriteria(criteria);  
	        // 查询条件，集合对应的实体类，集合名  
	        List<InterfaceLog> logList = mongoTemplate.find(query, InterfaceLog.class,  
	                "logback");  
	        return logList;  
	}

	@Override
	public List<InterfaceLog> findAll() {

		return null;
	}
}


