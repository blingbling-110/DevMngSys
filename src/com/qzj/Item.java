package com.qzj;

/**
 * 	Item�����ࣺ��id��name��jobNum���Խ��з�װ
 * @author qinzijun
 *
 */
public class Item {
	/**
	 * 	id�����ݿ���ĳ���idֵ
	 */
	private String id;
	/**
	 * 	name�����ݿ���ĳ���nameֵ
	 */
	private String name;
	/**
	 * 	jobNum�����ݿ���ĳ�û���idֵ�����ţ�
	 */
	private Integer jobNum;
	
	/**
	 * 	Ĭ�Ϲ��췽��
	 */
	public Item() {
	}
	
	/**
	 * 	�����������Գ�ʼ���Ĺ��췽��
	 * @param id ���������ݿ��е�idֵ
	 * @param name ���������ݿ��е�nameֵ
	 * @param jobNum ���û��Ĺ���
	 */
	public Item(String id, String name, Integer jobNum) {
		this.id = id;
		this.name = name;
		this.jobNum = jobNum;
	}
	
	//	ʹ��Getters��Setters������Item�������˽�����Է�װ����
	
	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id Ҫ���õ� id
	 */
	public void setId(String id) {
		this.id = id;
	}

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
	 * @return jobNum
	 */
	public Integer getJobNum() {
		return jobNum;
	}

	/**
	 * @param jobNum Ҫ���õ� jobNum
	 */
	public void setJobNum(Integer jobNum) {
		this.jobNum = jobNum;
	}
	
	/**
	 * 	��дtoString()������ֻ�����name����
	 */
	public String toString() {
		return getName();
	}
}
