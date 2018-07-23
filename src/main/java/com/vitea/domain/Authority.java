package com.vitea.domain;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

	/**
	 * 用户权限实体
	 */
	private static final long serialVersionUID = 574536843601341920L;
	private String authority;

    public Authority() { }
    public Authority(String authority) {
        this.setAuthority(authority);
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }
	

}
