package com.qzj.sqlOpr;

import java.util.ArrayList;

/**
 * 	���ݱ�ģ��
 * @author qinzijun
 *
 */
public class Tables {
	/**
	 * 	���ݱ�����
	 */
	private String name;
	
	/**
	 * 	���ݱ���е�ArrayList����
	 */
	private ArrayList<Columns> columns = new ArrayList<>();
	
	public Tables() {
	}
	
	public Tables(String name, ArrayList<Columns> columns) {
		this.name = name;
		this.columns = columns;
	}
	
	//	ʹ��Getters��Setters�������������˽�����Է�װ����
	
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name Ҫ���õ� name
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
	 * @param columns Ҫ���õ� columns
	 */
	public void setColumns(ArrayList<Columns> columns) {
		this.columns = columns;
	}
}
