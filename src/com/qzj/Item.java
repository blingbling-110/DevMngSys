package com.qzj;

public class Item {// Item公共类：对id和name属性进行封装
	private String id;
	private String name;
	
	public Item() {// 默认构造方法
	}
	
	public Item(String id, String name) {// 包含所有属性初始化的构造方法
		this.id = id;
		this.name = name;
	}
	
	// 使用Getters和Setters方法将Item公共类的私有属性封装起来
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
	
	public String toString() {// 重写toString()方法，只能输出name属性
		return getName();
	}
}
