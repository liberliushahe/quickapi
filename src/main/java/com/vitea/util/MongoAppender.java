package com.vitea.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
/**
 * 自定义日志记录
 * @author liushahe
 *
 * @param <ILoggingEvent>
 */

public  class MongoAppender extends UnsynchronizedAppenderBase<ILoggingEvent>{
	@Override  
    protected void append(ILoggingEvent eventObject) {
		MongoTemplate mongoTemplate = (MongoTemplate) SpringUtil.getApplicationContext().getBean("mongoTemplate");
		SimpleDateFormat format =  new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		final Document doc = new Document();
		Object[] obj=eventObject.getArgumentArray();
		doc.append("timestamp",format.format(new Date(eventObject.getTimeStamp())));
		doc.append("level", eventObject.getLevel().toString());
		doc.append("logger", eventObject.getLoggerName());
		doc.append("thread", eventObject.getThreadName());
		doc.append("accnum", obj[0].toString());
		doc.append("starttime", obj[1].toString());
		doc.append("endtime", obj[2].toString());
		doc.append("reqstr", obj[3].toString());
		doc.append("retstr", obj[4].toString());
		doc.append("cost", obj[5].toString());
		mongoTemplate.insert(doc,"logback");
    }  
 
}



