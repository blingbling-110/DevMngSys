package com.qzj;

public class Item {//	Item�����ࣺ��id��name���Խ��з�װ
	private String id;
	private String name;
	private Integer jobNum;
	
	public Item() {//	Ĭ�Ϲ��췽��
	}
	
	//	�����������Գ�ʼ���Ĺ��췽��
	public Item(String id, String name, Integer jobNum) {
		this.id = id;
		this.name = name;
		this.jobNum = jobNum;
	}
	
	//	ʹ��Getters��Setters������Item�������˽�����Է�װ����
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
	
	public Integer getJobNum() {
		return jobNum;
	}
	
	public void setJobNum(Integer jobNum) {
		this.jobNum = jobNum;
	}
	
	public String toString() {//	��дtoString()������ֻ�����name����
		return getName();
	}
}
