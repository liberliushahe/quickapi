package com.vitea.dao;

import java.util.List;

import com.vitea.domain.RoleResource;
/**
 * 角色资源操作
 * @author liushahe
 *
 */
public interface IRoleResourceDao {
    
    int deleteByPrimaryKey(Short rrid);
   
    int insert(RoleResource record);

    List<RoleResource> selectAllRoleResourceByPage();
    
    RoleResource selectByPrimaryKey(Short rrid);

    int updateByPrimaryKey(RoleResource record);
}