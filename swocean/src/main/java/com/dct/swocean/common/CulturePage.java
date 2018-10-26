package com.dct.swocean.common;

import java.io.Serializable;
import java.util.List;

public class CulturePage implements Serializable{
	/**
	 * 分页所用的类
	 */
	private static final long serialVersionUID = 1L;

	private long total; // 数据的总条数

	private List<?> rows; // 一页信息的列表

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	
}
