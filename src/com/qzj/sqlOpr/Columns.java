package com.qzj.sqlOpr;

public class Columns {//	���ݱ����ģ��
	private String name;//	�ֶ�����
	private String type;//	�ֶ�����
	private boolean isNull;//	�Ƿ��Ϊ��
	private boolean isKey;//	�Ƿ�Ϊ����
	private boolean isIncrement;//	�Ƿ�����
	
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
	
	//	ʹ��Getters��Setters�������������˽�����Է�װ����
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
	
	public String toString() {//	��дtoString()����
		return "Columns [name=" + name + ", type=" + type
				+ ", isNull=" + isNull + ", isKey=" + isKey
				+ ", isIncrement=" + isIncrement + "]";
	}
}
