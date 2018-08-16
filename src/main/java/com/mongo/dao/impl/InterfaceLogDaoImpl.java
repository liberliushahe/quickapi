package com.mongo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
    private static String ISNULL="null";
	
	@Override
	public List<InterfaceLog> findForRequeryByParam(String start, String end, String accnum, int index, int size) {
        //设置查询条件
        Query query = new Query();
        
        if(!ISNULL.equals(accnum)) {
            query.addCriteria(Criteria.where("accnum").is(accnum));
        }
        if(!ISNULL.equals(start)&& !ISNULL.equals(end)) {
            query.addCriteria(Criteria.where("timestamp").gte(start).lt(end));
        }
        Pageable pageable = new PageRequest(index-1, size);
        query.with(pageable);
        // 排序
        query.with(new Sort(Direction.ASC,"timestamp"));
        List<InterfaceLog> logList=mongoTemplate.find(query,InterfaceLog.class, "logback");
        return logList;
	}

	@Override
	public long findAllCount(String start,String end,String accnum) {
		Query query = new Query();
		 if(!ISNULL.equals(accnum)) {
	            query.addCriteria(Criteria.where("accnum").is(accnum));
	     }
	    if(!ISNULL.equals(start)&& !ISNULL.equals(end)) {
	            query.addCriteria(Criteria.where("timestamp").gte(start).lt(end));
	     }
		return mongoTemplate.count(query, InterfaceLog.class,"logback");
	}

	@Override
	public long successCount() {
		Query query = new Query();
		query.addCriteria(Criteria.where("retstr").ne("retstr"));
		return mongoTemplate.count(query, InterfaceLog.class,"logback");
	}

	@Override
	public long failCount() {
		Query query = new Query();
		query.addCriteria(Criteria.where("retstr").is(""));
		return mongoTemplate.count(query, InterfaceLog.class,"logback");
	}
	
}


