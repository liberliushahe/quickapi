package com.vitea.dao;


import com.vitea.domain.ApiUser;


public interface IApiUserDao {
   
  
    int deleteByPrimaryKey(String type);

    int insert(ApiUser record);
    ApiUser selectByPrimaryKey(String type);

    int updateByPrimaryKey(ApiUser record);
    
}