package com.mongo.dao.impl;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mongo.dao.InterfaceLogDao;
import com.mongo.domain.InterfaceLog;
import com.mongodb.BasicDBObject;
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
	@Override
	public Iterator<BasicDBObject> groupByMapReduceOfAreaCode() {
		String map = "function() { emit(this.areacode, {count:1});}";
	    String reduce = "function(key, values) {"
	    		+ "var total = 0;"
	    		+ "for(var i=0;i<values.length;i++){total += values[i].count;}"
	    		+ "return {count:total};}";
	    MapReduceResults<BasicDBObject> mrr = mongoTemplate.mapReduce("logback", map, reduce, BasicDBObject.class);
	    return mrr.iterator();
	}

	@Override
	public Iterator<BasicDBObject> groupByMapReduceOfProduct() {
		String map = "function() { emit(this.paratype, {count:1});}";
	    String reduce = "function(key, values) {"
	    		+ "var total = 0;"
	    		+ "for(var i=0;i<values.length;i++){total += values[i].count;}"
	    		+ "return {count:total};}";
	    MapReduceResults<BasicDBObject> mrr = mongoTemplate.mapReduce("logback", map, reduce, BasicDBObject.class);
	    return mrr.iterator();
	}

	@Override
	public Iterator<BasicDBObject> daySuccessTrans() {
		String map = "function() { emit(this.interid, {count:1});}";
	    String reduce = "function(key, values) {"
	    		+ "var total = 0;"
	    		+ "for(var i=0;i<values.length;i++){total += values[i].count;}"
	    		+ "return {count:total};}";
	    Query query = new Query();
	    query.addCriteria(Criteria.where("retstr").ne("retstr"));
	    //格式化日期获取当前时间和10分钟前的时间
	    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Calendar c=Calendar.getInstance();
	    Date currentDate=c.getTime();
	    c.add(Calendar.MINUTE, -10);
	    Date beforeDate=c.getTime();
	    String end=format.format(currentDate);
	    String start=format.format(beforeDate);
        query.addCriteria(Criteria.where("timestamp").gte(start).lt(end));
	    MapReduceResults<BasicDBObject> mrr = mongoTemplate.mapReduce(query,"logback", map, reduce, BasicDBObject.class);
	    return mrr.iterator();
	}

	@Override
	public Iterator<BasicDBObject> dayFailTrans() {
		String map = "function() { emit(this.interid, {count:1});}";
	    String reduce = "function(key, values) {"
	    		+ "var total = 0;"
	    		+ "for(var i=0;i<values.length;i++){total += values[i].count;}"
	    		+ "return {count:total};}";
	    Query query = new Query();
	    query.addCriteria(Criteria.where("retstr").is(""));
	    //格式化日期获取当前时间和10分钟前的时间
	    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Calendar c=Calendar.getInstance();
	    Date currentDate=c.getTime();
	    c.add(Calendar.MINUTE, -10);
	    Date beforeDate=c.getTime();
	    String end=format.format(currentDate);
	    String start=format.format(beforeDate);
        query.addCriteria(Criteria.where("timestamp").gte(start).lt(end));
	    MapReduceResults<BasicDBObject> mrr = mongoTemplate.mapReduce(query,"logback", map, reduce, BasicDBObject.class);    
	    return mrr.iterator();
	}

	@Override
	public Iterator<BasicDBObject> currentTrans(String flag) {
		String map = "function() { emit(this.interid, {count:1});}";
	    String reduce = "function(key, values) {"
	    		+ "var total = 0;"
	    		+ "for(var i=0;i<values.length;i++){total += values[i].count;}"
	    		+ "return {count:total};}";
	    Query query = new Query();
	    if("all".equals(flag)) {
	    	
	    }else if("success".equals(flag)) {
		  query.addCriteria(Criteria.where("retstr").ne("retstr"));
	    }
	    else {
		  query.addCriteria(Criteria.where("retstr").is(""));
	    }
	    //格式化日期获取当前时间和10分钟前的时间
	    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Calendar c=Calendar.getInstance();
	    Date currentDate=c.getTime();
	    c.set(Calendar.DATE, 1);
	    c.roll(Calendar.DATE, -1);
	    int maxDate = c.get(Calendar.DATE); 
	    c.add(Calendar.DAY_OF_MONTH,-(maxDate));
	    Date beforeDate=c.getTime();
	    String end=format.format(currentDate);
	    String start=format.format(beforeDate);
        query.addCriteria(Criteria.where("timestamp").gte(start).lt(end));
	    MapReduceResults<BasicDBObject> mrr = mongoTemplate.mapReduce(query,"logback", map, reduce, BasicDBObject.class);    
	    return mrr.iterator();
	}
	
	}



