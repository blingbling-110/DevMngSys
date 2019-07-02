package com.qzj.sqlOpr;

/**
 * 	���ݱ����ģ��
 * @author qinzijun
 *
 */
public class Columns {
	/**
	 * 	�ֶ�����
	 */
	private String name;
	
	/**
	 * 	�ֶ�����
	 */
	private String type;
	
	/**
	 * 	�Ƿ��Ϊ��
	 */
	private boolean isNull;
	
	/**
	 * 	�Ƿ�Ϊ����
	 */
	private boolean isKey;
	
	/**
	 * 	�Ƿ�����
	 */
	private boolean isIncrement;
	
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
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type Ҫ���õ� type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return isNull
	 */
	public boolean isNull() {
		return isNull;
	}

	/**
	 * @param isNull Ҫ���õ� isNull
	 */
	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}

	/**
	 * @return isKey
	 */
	public boolean isKey() {
		return isKey;
	}

	/**
	 * @param isKey Ҫ���õ� isKey
	 */
	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}

	/**
	 * @return isIncrement
	 */
	public boolean isIncrement() {
		return isIncrement;
	}

	/**
	 * @param isIncrement Ҫ���õ� isIncrement
	 */
	public void setIncrement(boolean isIncrement) {
		this.isIncrement = isIncrement;
	}

	/**
	 * 	��дtoString()����
	 */
	public String toString() {
		return "Columns [name=" + name + ", type=" + type
				+ ", isNull=" + isNull + ", isKey=" + isKey
				+ ", isIncrement=" + isIncrement + "]";
	}
}
