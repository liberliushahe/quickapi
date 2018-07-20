package com.vitea.util;

import org.junit.Test;

public class JedisClientTest {
	@Test
	public void ClientTest() {
		 int loop = 1;
	        while (loop < 1000) {
	                long start = System.currentTimeMillis();
	                JedisClientUtil.setString("k1", "v1");
	                String ret=JedisClientUtil.getString("k1");
	                long end = System.currentTimeMillis();
	                System.out.printf("Get ret from redis: %s with %d millis\n", ret, end-start);
	            loop++;
	        }
	}
	  

}
