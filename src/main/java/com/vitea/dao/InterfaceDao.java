package com.vitea.dao;

import java.util.List;

import com.vitea.domain.InterFace;





/**
 * 接口dao
 * @author liushahe
 *
 */

public interface InterfaceDao {
	/**
	 * 返回总记录
	 * @return
	 */
	public Integer getAllInterfaceCount();
	
	/**
	 * 通过id获取接口
	 * @param id
	 * @return
	 */
	public InterFace getPortById(int id);
	/**
	 *  插入接口信息
	 * @param inter
	 */
	void insertSelective(InterFace inter);
	/**
	 * 保存接口
	 * @param inter
	 */
	void save(InterFace inter);

	/**
	 * 更新接口
	 * @param inter
	 * @return
	 */
	boolean update(InterFace inter);

	/**
	 * 删除接口
	 * @param id
	 * @return
	 */
	boolean delete(String id);

	/**
	 * 查询所有接口
	 * @return
	 */
	List<InterFace> findAll();
	/**
	 * 更新接口
	 * @param inter
	 * @return
	 */
	boolean updateByPrimaryKeySelective(InterFace inter);
}
