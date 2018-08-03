package com.vitea.util;

import java.util.Date;

import org.bson.Document;

import ch.qos.logback.classic.spi.ILoggingEvent;

public class MongoDBAppender extends MongoDBAppenderBase<ILoggingEvent>{

	  public MongoDBAppender() {
		    super("loggingEvents");
		  }

    /**
     * 子类重写
     */
	@Override
	protected Document toMongoDocument(ILoggingEvent eventObject) {
		final Document doc = new Document();
		doc.append("timestamp", new Date(eventObject.getTimeStamp()));
		doc.append("level", eventObject.getLevel().toString());
		doc.append("logger", eventObject.getLoggerName());
		doc.append("thread", eventObject.getThreadName());
		doc.append("message", eventObject.getFormattedMessage());
		if (eventObject.getMDCPropertyMap() != null && !eventObject.getMDCPropertyMap().isEmpty()) {
			doc.append("mdc", eventObject.getMDCPropertyMap());
		}
		return doc;
	}


}
