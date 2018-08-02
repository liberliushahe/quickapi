package com.vitea.dao;

import com.vitea.domain.Knowledge;

import java.util.List;

/**
 * 知识库操作
 * @author liushahe
 *
 */
public interface IKnowledgeDao {
   
    /**
     * 点击量加1
     * @param id
     */
    void hits(short id);
    /**
     * 插入记录
     * @param record
     * @return
     */
    int insert(Knowledge record);
    /**
     * 通过编号查询
     * @param id
     * @return
     */
    Knowledge getKnowledgeById(Short id);
    /**
     * 查询所有记录
     * @return
     */
    List<Knowledge> findAllKnowledge();
}