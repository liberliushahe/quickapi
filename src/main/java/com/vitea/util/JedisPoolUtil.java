package com.vitea.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * jedis client
 * @author liushahe
 *
 */
public class JedisPoolUtil {

	private static Logger logger = LoggerFactory.getLogger(JedisPoolUtil.class);
	private static JedisPool jedisPool = null;
	private static String PASS="null";
	/** 
     * 初始化Redis连接池 
     */  
    private static void initialPool() {  
    	int maxActive=Integer.parseInt(PropertiesUtil.getRedisProperties("MAX_ACTIVE"));
    	int maxIdle=Integer.parseInt(PropertiesUtil.getRedisProperties("MAX_IDLE"));
    	long maxWait=Long.parseLong(PropertiesUtil.getRedisProperties("MAX_WAIT"));
    	String host=PropertiesUtil.getRedisProperties("redis.host");
    	int port=Integer.parseInt(PropertiesUtil.getRedisProperties("redis.port"));
    	String pass=PropertiesUtil.getRedisProperties("redis.pass");
    	int timeOut=Integer.parseInt(PropertiesUtil.getRedisProperties("TIMEOUT"));
        try {  
            JedisPoolConfig config = new JedisPoolConfig();  
            config.setMaxTotal(maxActive);
            config.setMaxIdle(maxIdle);
            config.setMaxWaitMillis(maxWait);   
            config.setTestOnBorrow(true);
            if(PASS.equals(pass)) {
                jedisPool = new JedisPool(config,host,port,timeOut);  

            }else {
                jedisPool = new JedisPool(config,host,port,timeOut,pass);  

            }
        } catch (Exception e) {  
           e.printStackTrace();
        }  
    }  
	
    /** 
     * 在多线程环境同步初始化 
     */  
    private static synchronized void poolInit() {  
        if (jedisPool == null) {    
            initialPool();  
        }  
    }      
    /** 
     * 同步获取Jedis实例 
     * @return Jedis 
     */  
    public synchronized static Jedis getJedis() {   
        if (jedisPool == null) {    
            poolInit();  
        }  
        Jedis jedis = null;  
        try {    
            if (jedisPool != null) {   
                jedis = jedisPool.getResource(); 
            }  
        } catch (Exception e) {
        	e.printStackTrace();
        	returnResource(jedis);
            logger.error("Get jedis error : "+e);  
        }
        return jedis;  
    }    
	
    /** 
     * 释放jedis资源 
     * 
     * @param jedis 
     */  
    public static void returnResource(Jedis jedis) { 
        if (jedis != null) { 	
            jedis.close();
        }  
    }  
}
