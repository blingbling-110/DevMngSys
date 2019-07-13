package com.qzj.sqlOpr.model;

/**
 * 	���ù����ࣺ���ڷ�װtb_brw���ݱ�
 * @author qinzijun
 *
 */
public class TbBrw {
	/**
	 * 	���õ����
	 */
	private String id;
	
	/**
	 * 	�����豸���
	 */
	private String devId;
	
	/**
	 * 	�����˹���
	 */
	private Integer brwerId;
	
	/**
	 * 	��������
	 */
	private String date;
	
	/**
	 * 	��ע
	 */
	private String remark;
	
	public TbBrw() {
	}

	public TbBrw(String id) {
		this.id = id;
	}

	public TbBrw(String id, String devId, Integer brwerId, 
			String date, String remark) {
		this.id = id;
		this.devId = devId;
		this.brwerId = brwerId;
		this.date = date;
		this.remark = remark;
	}
	
	//	ʹ��Getters��Setters�������������˽�����Է�װ����

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
	 * @return devId
	 */
	public String getDevId() {
		return devId;
	}

	/**
	 * @param devId Ҫ���õ� devId
	 */
	public void setDevId(String devId) {
		this.devId = devId;
	}

	/**
	 * @return brwerId
	 */
	public Integer getBrwerId() {
		return brwerId;
	}

	/**
	 * @param brwerId Ҫ���õ� brwerId
	 */
	public void setBrwerId(Integer brwerId) {
		this.brwerId = brwerId;
	}

	/**
	 * @return date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date Ҫ���õ� date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark Ҫ���õ� remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
