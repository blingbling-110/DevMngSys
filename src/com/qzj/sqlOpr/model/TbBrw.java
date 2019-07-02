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
	private String dvId;
	
	/**
	 * 	�����˱��
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

	public TbBrw(String id, String dvId, Integer brwerId, 
			String date, String remark) {
		this.id = id;
		this.dvId = dvId;
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
	 * @return dvId
	 */
	public String getDvId() {
		return dvId;
	}

	/**
	 * @param dvId Ҫ���õ� dvId
	 */
	public void setDvId(String dvId) {
		this.dvId = dvId;
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
