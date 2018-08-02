package com.vitea.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vitea.dao.IKnowledgeDao;
import com.vitea.domain.Knowledge;
import com.vitea.service.IGetKnowledge;
/**
 * 
 * @author liushahe
 *
 */
@Service
public class GetKnowledgeImpl implements IGetKnowledge {
    @Autowired
	IKnowledgeDao iKnowledgeDao;
	@Override
	public Knowledge getKnowledgeById(Short id) {
		return iKnowledgeDao.getKnowledgeById(id);
	}
	@Override
	public PageInfo<Knowledge> getKnowledgeByPage(int index, int size) {
		  PageHelper.startPage(index, size);
	      List<Knowledge> list = iKnowledgeDao.findAllKnowledge();
	      PageInfo<Knowledge> pageInfo = new PageInfo<>(list);
		  return pageInfo;
	}
	@Override
	public int insert(Knowledge knowledge) {
		return iKnowledgeDao.insert(knowledge);
	}
	@Override
	public void hits(short id) {
		iKnowledgeDao.hits(id);
	}

}
