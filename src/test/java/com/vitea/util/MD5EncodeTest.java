package com.vitea.util;

import org.junit.Test;

public class MD5EncodeTest {
	@Test
	public void encode() {
		String pass=MD5PasswordEncoder.getMD5PasswordEncoder().encode("admin");
		System.out.println(pass);
	}

}
