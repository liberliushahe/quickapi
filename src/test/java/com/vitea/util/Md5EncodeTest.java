package com.vitea.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vitea.endpoint.service.impl.GetChargeListImpl;


/**
 * 
 * @author liushahe
 *
 */
public class Md5EncodeTest {
	private Logger logger = LoggerFactory.getLogger(GetChargeListImpl.class);
	@Test
	public void encode() {
		logger.info("11111");
		String pass=Md5PassWordEncoder.getMD5PasswordEncoder().encode("admin");
		System.out.println(pass);
	}

}
