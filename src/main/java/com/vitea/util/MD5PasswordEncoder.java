package com.vitea.util;

import java.io.UnsupportedEncodingException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

public class MD5PasswordEncoder extends BCryptPasswordEncoder{
	private MD5PasswordEncoder() {
		
	}
  private static MD5PasswordEncoder encode=new MD5PasswordEncoder();
	
  public static MD5PasswordEncoder getMD5PasswordEncoder() {
		if(encode!=null) {
			return new MD5PasswordEncoder();
		}else {
			return encode;
		}
	}
	@Override
    public String encode(CharSequence rawPassword) {
        try {
            // MD5加密密码
            return DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
   
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
