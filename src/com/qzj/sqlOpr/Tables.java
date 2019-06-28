package com.qzj.sqlOpr;

import java.util.ArrayList;

public class Tables {//	数据表模型
	private String name;//	数据表名称
	private ArrayList<Columns> columns = new ArrayList<>();//	数据表的列集合
	
	public Tables() {
	}
	
	public Tables(String name, ArrayList<Columns> columns) {
		this.name = name;
		this.columns = columns;
	}
	
	//	使用Getters和Setters方法将公共类的私有属性封装起来
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Columns> getColumns() {
		return columns;
	}
	
	public void setColumns(ArrayList<Columns> columns) {
		this.columns = columns;
	}
}
