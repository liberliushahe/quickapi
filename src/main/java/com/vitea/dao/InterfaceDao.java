package com.vitea.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	public InterFace getInterfaceById(int id);

	/**
	 * 保存接口
	 * @param inter
	 */
	int insert(InterFace inter);

	/**
	 * 删除接口
	 * @param id
	 * @return
	 */
	boolean delete(int id);

	/**
	 * 查询所有接口
	 * @return
	 */
    List<InterFace> findAllInterfaceByParam(@Param("id")int id,@Param("method")String method,@Param("name")String name);
	/**
	 * 更新接口
	 * @param inter
	 * @return
	 */
	boolean updateByPrimaryKeySelective(InterFace inter);
}
