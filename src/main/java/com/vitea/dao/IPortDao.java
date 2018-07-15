package com.vitea.dao;

import java.util.List;

import com.vitea.domain.InterFace;







public interface IPortDao {
	/**
	 * 
	 * @param id
	 * @return
	 */
	public InterFace getPortById(int id);
	/**
	 * 
	 * @param inter
	 */
	void insertSelective(InterFace inter);
	/**
	 * 
	 * @param inter
	 */
	void save(InterFace inter);

	/**
	 * 
	 * @param inter
	 * @return
	 */
	boolean update(InterFace inter);

	/**
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(String id);

	/**
	 * 
	 * @return
	 */
	List<InterFace> findAll();
	/**
	 * 
	 * @param inter
	 * @return
	 */
	boolean updateByPrimaryKeySelective(InterFace inter);
}
