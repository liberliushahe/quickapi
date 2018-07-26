package com.vitea.util;

import java.io.UnsupportedEncodingException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;
/**
 * MD5加密
 * @author liushahe
 *
 */
public class Md5PassWordEncoder extends BCryptPasswordEncoder{
	private Md5PassWordEncoder() {
		
	}
  private static Md5PassWordEncoder encode=new Md5PassWordEncoder();
	
  public static Md5PassWordEncoder getMD5PasswordEncoder() {
		if(encode!=null) {
			return new Md5PassWordEncoder();
		}else {
			return encode;
		}
	}
   /**
    * 加密
    */
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
    /**
     * 密码匹配
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
