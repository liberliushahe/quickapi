package com.vitea.dao;

import com.vitea.domain.Knowledge;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 知识库操作接口
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
     * 删除知识库
     * @param id
     * @return
     */
    int deleteKnowledgeById(Short id);
   /**
    * 更新知识库
    * @param knowledge
    * @return
    */
    int updateKnowledgeById(Knowledge knowledge);
    /**
     * 查询所有记录
     * @return
     */
    List<Knowledge> findAllKnowledge();
    
    /**
     * 根据条件查询记录
     * @param starttime
     * @param endtime
     * @param title
     * @return
     */
    List<Knowledge> findAllKnowledgeByParam(@Param("starttime")String starttime,@Param("endtime")String endtime,@Param("title")String title);
}