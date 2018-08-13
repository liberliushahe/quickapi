package com.vitea.endpoint.dto;

import java.util.List;

/**
 * 分页结果展示
 * @author liushahe
 * @param <T>
 *
 */
public class PageResult<T> {
/**
* 总记录
*/
private int total;
/**
 * 分页结果
 */
private List<T> rows;
public int getTotal() {
	return total;
}
public void setTotal(int total) {
	this.total = total;
}
public List<T> getRows() {
	return rows;
}
public void setRows(List<T> rows) {
	this.rows = rows;
}

}
