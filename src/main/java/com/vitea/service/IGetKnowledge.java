package com.vitea.service;

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

}
