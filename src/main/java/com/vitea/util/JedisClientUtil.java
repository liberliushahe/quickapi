package com.vitea.util;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;
/**
 * 操作redis客户端
 * @author liushahe
 *
 */
public class JedisClientUtil {
	/**
	 * 判断是否成功
	 */
	public static String STATUS="ok";

	/**
	 * 默认过期时间一小时
	 */
	public static int DEFAULT_SETEX_TIMEOUT=60 * 60;
	/**
	 * 判断字符串为空
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean isValueNull(String key,Object value) {
		if(key==""||key==null) {
			return false;
		}
		if(value==null) {
			return false;
		}
		return true;
	}
	/**
	 * 设置字符串
	 * @param key
	 * @param value
	 * @return
	 */
	public static int setString(String key, String value) {
		if (isValueNull(key, value)) {
			return 0;
		}
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtil.getJedis();
			if (jedis.set(key, value).equalsIgnoreCase(STATUS)) {
				return 1;
			} else {
				return 0;
			}
		} finally {
			JedisPoolUtil.returnResource(jedis);
		}
 
	}
    /**
     * 获取字符串
     * @param key
     * @return
     */
	public static String getString(String key) {
		if(key=="" || key==null) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtil.getJedis();
             return jedis.get(key);
		} finally {
			JedisPoolUtil.returnResource(jedis);
		}
 
	}
	/**
	 * 缓存字符串
	 * @param key
	 * @param value
	 * @return
	 */
	public static int setEx(String key, String value) {
		if (isValueNull(key, value)) {
			return 0;
		}
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtil.getJedis();
			if (jedis.setex(key, DEFAULT_SETEX_TIMEOUT, value).equalsIgnoreCase(STATUS)){
				return 1;
			} else {
				return 0;
			}
		} finally {
			JedisPoolUtil.returnResource(jedis);
		}
	}
	/**
	 * 缓存字符串自定义过期时间
	 * @param key
	 * @param value
	 * @return
	 */
	public static int setEx(String key, String value,int time) {
		if (isValueNull(key, value)) {
			return 0;
		}
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtil.getJedis();
			if (jedis.setex(key, time, value).equalsIgnoreCase(STATUS)){
				return 1;
			} else {
				return 0;
			}
		} finally {
			JedisPoolUtil.returnResource(jedis);
		}
	}
	/**
	 * 保存对象
	 * @param key
	 * @param value
	 * @return
	 */
	public static <T> int setObject(String key, T value) {
		if (isValueNull(key, value)) {
			return 0;
		}
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtil.getJedis();

			if (jedis.set(key,JSON.toJSONString(value)).equalsIgnoreCase(STATUS)) {
				return 1;
			} else {
				return 0;
			}
		} finally {
			JedisPoolUtil.returnResource(jedis);
		}
	}
	/**
	 * 缓存对象
	 * @param key
	 * @param value
	 * @return
	 */
	public static <T> int setObjectEx(String key, T value) {
		if (isValueNull(key, value)) {
			return 0;
		}
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtil.getJedis();

			if (jedis.setex(key,DEFAULT_SETEX_TIMEOUT, JSON.toJSONString(value)).equalsIgnoreCase(STATUS)) {
				return 1;
			} else {
				return 0;
			}
		} finally {
			JedisPoolUtil.returnResource(jedis);
		}
	}
	/**
	 * 缓存对象自定义超时时间
	 * @param key
	 * @param value
	 * @return
	 */
	public static <T> int setObjectEx(String key, T value,int time) {
		if (isValueNull(key, value)) {
			return 0;
		}
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtil.getJedis();

			if (jedis.setex(key,time, JSON.toJSONString(value)).equalsIgnoreCase(STATUS)) {
				return 1;
			} else {
				return 0;
			}
		} finally {
			JedisPoolUtil.returnResource(jedis);
		}
	}
	/**
	 * 获取对象
	 * @param key
	 * @return
	 */
	public static Object getObject(String key) {

		if(key=="" || key==null) {
			return null;
		}
		Jedis jedis = null;
		try {
			 jedis = JedisPoolUtil.getJedis();
             return JSON.parseObject(jedis.get(key));
		} finally {
			JedisPoolUtil.returnResource(jedis);
		}
	}
	/**
	 * 将一个数据自增1
	 * @param key
	 * @return
	 * @throws JedisDataException
	 */
	public static Long increase(String key) throws JedisDataException {
		if (key=="" || key==null) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtil.getJedis();
			return jedis.incr(key);
		} finally {
			JedisPoolUtil.returnResource(jedis);
		}
	}
 
	/**
	 * 将一个数值-1,成功返回-后的结果,失败返回null
	 * 
	 * @param key
	 * @return
	 * @throws JedisDataException
	 */
	public static Long decr(String key) throws JedisDataException {
		if (key=="" || key==null) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtil.getJedis();
			return jedis.decr(key);
		} finally {
			JedisPoolUtil.returnResource(jedis);
		}
	}
	/**
	 * 存放字符串到列表
	 * @param key
	 * @param value
	 * @return
	 */
	public static int setList(String key, String... value) {
		if (isValueNull(key, value)) {
			return 0;
		}
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtil.getJedis();
			Long result = jedis.rpush(key, value);
			if (result != null && result != 0) {
				return 1;
			} else {
				return 0;
			}
		} finally {
			JedisPoolUtil.returnResource(jedis);
		}
	}
	/**
	 * 缓存字符串到列表 设置过期时间
	 * @param key
	 * @param value
	 * @return
	 */
	public static int setExList(String key, String... value) {
		if (isValueNull(key, value)) {
			return 0;
		}
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtil.getJedis();
			Long result = jedis.rpush(key, value);
			jedis.expire(key, DEFAULT_SETEX_TIMEOUT);
			if (result != null && result != 0) {
				return 1;
			} else {
				return 0;
			}
		} finally {
			JedisPoolUtil.returnResource(jedis);
		}
	}
	/**
	 * 缓存数据到列表 自定义时间
	 * @param key
	 * @param value
	 * @return
	 */
	public static int setExList(String key,int time, String... value) {
		if (isValueNull(key, value)) {
			return 0;
		}
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtil.getJedis();
			Long result = jedis.rpush(key, value);
			jedis.expire(key, time);
			if (result != null && result != 0) {
				return 1;
			} else {
				return 0;
			}
 
		} finally {
			JedisPoolUtil.returnResource(jedis);
		}
	}
	/**
	 * 保存对象到列表
	 * @param key
	 * @param value
	 * @return
	 */
	@SafeVarargs
	public static <T> int setList(String key, T... value) {
		if (isValueNull(key, value)) {
			return 0;
		}
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtil.getJedis();
			int res = 0;
			for (T t : value) {
				Long result = jedis.rpush(key.getBytes(), JSON.toJSONString(t).getBytes());
				if (result != null && result != 0) {
					res++;
				}
			}
			if (res != 0) {
				return 1;
			} else {
				return 0;
			}
		} finally {
			JedisPoolUtil.returnResource(jedis);
		}
	}



}
