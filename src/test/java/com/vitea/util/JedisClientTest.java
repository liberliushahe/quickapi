package com.vitea.util;

import org.junit.Test;
/**
 * 
 * @author liushahe
 *
 */
public class JedisClientTest {
	public static int COUNT=10;
	@Test
	public void clientTest() {
		 int loop = 1;
	        while (loop < COUNT) {
	                long start = System.currentTimeMillis();
	                JedisClientUtil.setString("k1", "v1");
	                String ret=JedisClientUtil.getString("k1");
	                long end = System.currentTimeMillis();
	                System.out.printf("Get ret from redis: %s with %d millis\n", ret, end-start);
	            loop++;
	        }
	}
	  

}
