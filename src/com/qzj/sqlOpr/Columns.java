package com.qzj.sqlOpr;

/**
 * 	数据表的列模型
 * @author qinzijun
 *
 */
public class Columns {
	/**
	 * 	字段名称
	 */
	private String name;
	
	/**
	 * 	字段类型
	 */
	private String type;
	
	/**
	 * 	是否可为空
	 */
	private boolean isNull;
	
	/**
	 * 	是否为主键
	 */
	private boolean isKey;
	
	/**
	 * 	是否自增
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
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type 要设置的 type
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
	 * @param isNull 要设置的 isNull
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
	 * @param isKey 要设置的 isKey
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
	 * @param isIncrement 要设置的 isIncrement
	 */
	public void setIncrement(boolean isIncrement) {
		this.isIncrement = isIncrement;
	}

	/**
	 * 	重写toString()方法
	 */
	public String toString() {
		return "Columns [name=" + name + ", type=" + type
				+ ", isNull=" + isNull + ", isKey=" + isKey
				+ ", isIncrement=" + isIncrement + "]";
	}
}
