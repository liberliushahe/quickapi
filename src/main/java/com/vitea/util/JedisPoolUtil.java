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
	/** 
     * 初始化Redis连接池 
     */  
    private static void initialPool() {  
    	int MAX_ACTIVE=Integer.parseInt(PropertiesUtil.getRedisProperties("MAX_ACTIVE"));
    	int MAX_IDLE=Integer.parseInt(PropertiesUtil.getRedisProperties("MAX_IDLE"));
    	long MAX_WAIT=Long.parseLong(PropertiesUtil.getRedisProperties("MAX_WAIT"));
    	String HOST=PropertiesUtil.getRedisProperties("redis.host");
    	int PORT=Integer.parseInt(PropertiesUtil.getRedisProperties("redis.port"));
    	String PASS=PropertiesUtil.getRedisProperties("redis.pass");
    	int TIMEOUT=Integer.parseInt(PropertiesUtil.getRedisProperties("TIMEOUT"));
        try {  
            JedisPoolConfig config = new JedisPoolConfig();  
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);   
            config.setTestOnBorrow(true);
            jedisPool = new JedisPool(config,HOST, PORT, TIMEOUT,PASS);  
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
