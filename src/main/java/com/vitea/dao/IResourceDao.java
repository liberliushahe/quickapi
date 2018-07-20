package com.vitea.dao;

import com.vitea.domain.Resource;

public interface IResourceDao {
    /**
     * 通过主键删除
     * @param resid
     * @return
     */
    int deleteByPrimaryKey(Short resid);
    /**
     * 增加记录
     * @param record
     * @return
     */
    int insert(Resource record);
    /**
     * 通过主键查询
     * @param resid
     * @return
     */
    Resource selectByPrimaryKey(Short resid);
    /**
     * 修改记录
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Resource record);

}