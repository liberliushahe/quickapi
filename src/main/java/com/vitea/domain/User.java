package com.vitea.domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
public class User implements UserDetails{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3205189290904933541L;
	private String username;
    private String password;
    private String email;
    private String phone;
    private String url;
    private int enabled;
    private int islocked;
    private Date register;
    private List<Authority> authorities;



	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public Date getRegister() {
		return register;
	}

	public void setRegister(Date register) {
		this.register = register;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	


	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public int getIslocked() {
		return islocked;
	}

	public void setIslocked(int islocked) {
		this.islocked = islocked;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", email=" + email + ", phone=" + phone
				+ ", url=" + url + ", enabled=" + enabled + ", islocked=" + islocked + ", register=" + register
				+ ", authorities=" + authorities + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}	
}
