package com.vitea.util;

import java.net.UnknownHostException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import ch.qos.logback.core.UnsynchronizedAppenderBase;
/**
 * 连接mongodb
 * @author liushahe
 *
 * @param <E>
 */
public abstract class MongoDBAppenderBase<E> extends UnsynchronizedAppenderBase<E>{

	private MongoClient mongoClient;  
    private MongoCollection<Document> eventsCollection;  
  
    private String host;  
    private int port;  
    private String dbName;  
    private String collectionName;  
    private String username;  
    private String password;  
    protected String source;  
  
    private int connectionsPerHost = 10;  
    private int threadsAllowedToBlockForConnectionMultiplier = 5;  
    private int maxWaitTime = 1000 * 60 * 2;  
    private int connectTimeout;  
    private int socketTimeout; 
    protected MongoDBAppenderBase(String collectionName) {  
        this.collectionName = collectionName;  
    }     
private void connectToMongoDB() throws UnknownHostException {
    	//用户名密码验证方式，暂时不用
    	//List<MongoCredential> mongoCredentialList = new ArrayList<MongoCredential>();
    	//mongoCredentialList.add(MongoCredential.createMongoCRCredential(username, dbName, password.toCharArray()));
    	//mongoClient = new MongoClient(new ServerAddress(host, port), mongoCredentialList, buildOptions());
    	//集群连接
	    //Arrays.asList(new ServerAddress("10.111.129.11", 8021),
        //new ServerAddress("10.111.129.12", 8021),
        //new ServerAddress("10.111.129.13", 8021))
    	mongoClient = new MongoClient(new ServerAddress(host, port), buildOptions());
    	mongoClient.setWriteConcern(new WriteConcern(0));//写策略不需要确认，效率更高
    	
    	MongoDatabase database = mongoClient.getDatabase(this.dbName);
    	eventsCollection = database.getCollection(collectionName);
    }  
    private MongoClientOptions buildOptions() {
   	final MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
    	builder.connectionsPerHost(connectionsPerHost);
    	builder.threadsAllowedToBlockForConnectionMultiplier(threadsAllowedToBlockForConnectionMultiplier);
   	    builder.maxWaitTime(maxWaitTime);
    	builder.connectTimeout(connectTimeout);
    	builder.socketTimeout(socketTimeout);
    	final MongoClientOptions options = builder.build();
		return options;
    	
    }
    @Override  
    public void start() {  
        try {  
            connectToMongoDB();  
            super.start();  
        } catch (UnknownHostException e) {  
            addError("Error connecting to MongoDB server: " + host + ":" + port, e);  
        }  
    }  
    
    protected abstract Document toMongoDocument(E event);  
  
    @Override  
    protected void append(E eventObject) {
      eventsCollection.insertOne(toMongoDocument(eventObject)); 
    }  
  
    @Override  
    public void stop() {  
        if (mongoClient != null) {
        	mongoClient.close();
        }
        super.stop();  
    }  
  
    public void setHost(String host) {  
        this.host = host;  
    }  
  
    public void setPort(int port) {  
        this.port = port;  
    }  
  
    public void setDbName(String dbName) {  
        this.dbName = dbName;  
    }  
  
    public void setCollectionName(String collectionName) {  
        this.collectionName = collectionName;  
    }  
  
    public void setUsername(String username) {  
        this.username = username;  
    }  
  
    public void setPassword(String password) {  
        this.password = password;  
    }  
  
    public void setConnectionsPerHost(int connectionsPerHost) {  
        this.connectionsPerHost = connectionsPerHost;  
    }  
  
    public void setThreadsAllowedToBlockForConnectionMultiplier(  
            int threadsAllowedToBlockForConnectionMultiplier) {  
        this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;  
    }  
  
    public void setMaxWaitTime(int maxWaitTime) {  
        this.maxWaitTime = maxWaitTime;  
    }  
  
    public void setConnectTimeout(int connectTimeout) {  
        this.connectTimeout = connectTimeout;  
    }  
  
    public void setSocketTimeout(int socketTimeout) {  
        this.socketTimeout = socketTimeout;  
    }  
  
    public String getSource() {  
        return source;  
    }  
  
    public void setSource(String source) {  
        this.source = source;  
    }  
 
}



