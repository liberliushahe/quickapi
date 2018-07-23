package com.vitea.dao;

import java.util.List;

import com.vitea.domain.UserRole;
/**
 * 用户角色操作接口
 * @author liushahe
 *
 */
public interface IUserRoleDao {
   
    int deleteByPrimaryKey(Short urid);

    int insert(UserRole record);

    List<UserRole> selectAllUserRoleByPage();

    UserRole selectByPrimaryKey(Short urid);
    UserRole selectByUsername(String urid);
   
    int updateByPrimaryKey(UserRole record);
}