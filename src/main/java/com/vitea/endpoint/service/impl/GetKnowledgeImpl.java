package com.vitea.endpoint.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.vitea.dao.IKnowledgeDao;
import com.vitea.domain.Knowledge;
import com.vitea.endpoint.service.IGetKnowledge;
/**
 * 
 * @author liushahe
 *
 */
public class GetKnowledgeImpl implements IGetKnowledge {
    @Autowired
    IKnowledgeDao iKnowledgeDao;
	@Override
	public Knowledge queryKnowledge(String id) {
		System.out.print(id);
		Knowledge knowledge=iKnowledgeDao.getKnowledgeById(new Short("1"));
		if(knowledge==null) {
			return null;
		}
		return knowledge;
	}

}
