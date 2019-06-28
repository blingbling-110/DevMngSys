package com.qzj.sqlOpr;

public class Columns {//	数据表的列模型
	private String name;//	字段名称
	private String type;//	字段类型
	private boolean isNull;//	是否可为空
	private boolean isKey;//	是否为主键
	private boolean isIncrement;//	是否自增
	
	public Columns() {
	}
	
	public Columns(String name, String type, boolean isNull,
			boolean isKey, boolean isIncrement) {
		this.name = name;
		this.type = type;
		this.isNull = isNull;
		this.isKey = isKey;
		this.isIncrement = isIncrement;
	}
	
	//	使用Getters和Setters方法将公共类的私有属性封装起来
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public boolean getIsNull() {
		return isNull;
	}
	
	public void setIsNull(boolean isNull) {
		this.isNull = isNull;
	}
	
	public boolean getIsKey() {
		return isKey;
	}
	
	public void setIsKey(boolean isKey) {
		this.isKey = isKey;
	}
	
	public boolean getIsIncrement() {
		return isIncrement;
	}
	
	public void setIsIncrement(boolean isIncrement) {
		this.isIncrement = isIncrement;
	}
	
	public String toString() {//	重写toString()方法
		return "Columns [name=" + name + ", type=" + type
				+ ", isNull=" + isNull + ", isKey=" + isKey
				+ ", isIncrement=" + isIncrement + "]";
	}
}
