package com.vitea.dao;

import com.vitea.domain.InterfaceLog;
/**
 * 接口日志dao
 * @author liushahe
 *
 */
public interface InterfaceLogDao {

    /**
     * 插入记录
     * @param record
     * @return
     */
    int insert(InterfaceLog record);
}