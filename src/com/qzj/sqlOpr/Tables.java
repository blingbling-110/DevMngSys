package com.qzj.sqlOpr;

import java.util.ArrayList;

public class Tables {//	���ݱ�ģ��
	private String name;//	���ݱ�����
	private ArrayList<Columns> columns = new ArrayList<>();//	���ݱ���м���
	
	public Tables() {
	}
	
	public Tables(String name, ArrayList<Columns> columns) {
		this.name = name;
		this.columns = columns;
	}
	
	//	ʹ��Getters��Setters�������������˽�����Է�װ����
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
