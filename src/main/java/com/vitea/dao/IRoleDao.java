package com.vitea.dao;

import com.vitea.domain.Role;
/**
 * 角色操作
 * @author liushahe
 *
 */
public interface IRoleDao {
 
    int deleteByPrimaryKey(Short rid);
    int insert(Role record);
    Role selectByPrimaryKey(Short rid);
    int updateByPrimaryKey(Role record);
}