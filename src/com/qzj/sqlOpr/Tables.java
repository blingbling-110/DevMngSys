package com.qzj.sqlOpr;

import java.util.ArrayList;

/**
 * 	数据表模型
 * @author qinzijun
 *
 */
public class Tables {
	/**
	 * 	数据表名称
	 */
	private String name;
	
	/**
	 * 	数据表的列的ArrayList集合
	 */
	private ArrayList<Columns> columns = new ArrayList<>();
	
	public Tables() {
	}
	
	public Tables(String name, ArrayList<Columns> columns) {
		this.name = name;
		this.columns = columns;
	}
	
	//	使用Getters和Setters方法将公共类的私有属性封装起来
	
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return columns
	 */
	public ArrayList<Columns> getColumns() {
		return columns;
	}

	/**
	 * @param columns 要设置的 columns
	 */
	public void setColumns(ArrayList<Columns> columns) {
		this.columns = columns;
	}
}
