package com.vitea.service;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.vitea.domain.Knowledge;
/**
 * 
 * @author liushahe
 *
 */
public interface IGetKnowledge {
    /**
     * 通过编号查询
     * @param id
     * @return
     */
    Knowledge getKnowledgeById(Short id);
    /**
     * 分页知识库
     * @param index
     * @param size
     * @return
     */
    PageInfo<Knowledge> getKnowledgeByPage(int index,int size);
    /**
     * 插入数据
     * @param knowledge
     * @return
     */
    int insert(Knowledge knowledge);
    /**
     * 点击量
     * @param id
     */
    void hits(short id);
    /**
     * 删除知识库
     * @param id
     * @return
     */
   int  deleteKnowledgeById(short id);
  /**
   * 更新知识库
   * @param knowledge
   * @return
   */
   int  editKnowledgeById(Knowledge knowledge);
   /**
    * 根据条件查询
    * @param starttime
    * @param endtime
    * @param title
    * @param index
    * @param size
    * @return
    */
   PageInfo<Knowledge> findAllKnowledgeByParam(@Param("starttime")String starttime,@Param("endtime")String endtime,@Param("title")String title,int index,int size);

}
