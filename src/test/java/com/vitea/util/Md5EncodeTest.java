package com.vitea.util;

import org.junit.Test;
/**
 * 
 * @author liushahe
 *
 */
public class Md5EncodeTest {
	@Test
	public void encode() {
		String pass=Md5PassWordEncoder.getMD5PasswordEncoder().encode("admin");
		System.out.println(pass);
	}

}
