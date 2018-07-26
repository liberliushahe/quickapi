package com.vitea.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitea.dao.IRoleDao;
import com.vitea.dao.IUserDao;
import com.vitea.dao.IUserRoleDao;
import com.vitea.domain.Role;
import com.vitea.domain.User;
import com.vitea.domain.UserRole;
@Service
@Transactional(rollbackFor=Exception.class)
/**
 * user service
 * @author liushahe
 *
 */
public class UserServiceImpl implements UserDetailsService {
	@Autowired
	private IUserDao iUserDao;
	@Autowired
	private IUserRoleDao iUserRoleDao;
	@Autowired
	private IRoleDao iRoleDao;
	private static final String SPLIT=",";
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=iUserDao.getUserByUsername(username);
		  if(user == null) {
	            throw new UsernameNotFoundException("用户名不存在");
	        }
		  List<GrantedAuthority> list = new ArrayList<GrantedAuthority>(); 
		  //查询角色id
		  UserRole userRole=iUserRoleDao.selectByUsername(username);
		  //查询用户角色
		  Role role=iRoleDao.selectByPrimaryKey(userRole.getRid());
		  for (String r:role.getRname().split(SPLIT)) {
			  list.add(new SimpleGrantedAuthority("ROLE_"+r));
		  }
		  user.setAuthorities(list);
	      return user;
	}
}
