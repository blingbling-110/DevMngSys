package com.qzj;

public class Item {// Item�����ࣺ��id��name���Խ��з�װ
	private String id;
	private String name;
	
	public Item() {// Ĭ�Ϲ��췽��
	}
	
	public Item(String id, String name) {// �����������Գ�ʼ���Ĺ��췽��
		this.id = id;
		this.name = name;
	}
	
	// ʹ��Getters��Setters������Item�������˽�����Է�װ����
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {// ��дtoString()������ֻ�����name����
		return getName();
	}
}
