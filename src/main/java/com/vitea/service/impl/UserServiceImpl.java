package com.vitea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vitea.dao.IUserDao;
import com.vitea.domain.User;
@Service
public class UserServiceImpl implements UserDetailsService {
	@Autowired
	private IUserDao iUserDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=iUserDao.getUserByUsername(username);
		  if(user == null) {
	            throw new UsernameNotFoundException("User not found for name:"+username);
	        }
	        return user;
	}
}
