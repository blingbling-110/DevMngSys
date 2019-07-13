package com.qzj.sqlOpr.model;

/**
 * 	�黹�����ࣺ���ڷ�װtb_rtn���ݱ�
 * @author qinzijun
 *
 */
public class TbRtn {
	/**
	 * 	�黹�����
	 */
	private String id;
	
	/**
	 * 	�黹�豸���
	 */
	private String devId;
	
	/**
	 * 	�黹�˹���
	 */
	private Integer rtnerId;
	
	/**
	 * 	�黹����
	 */
	private String date;
	
	/**
	 * 	��ע
	 */
	private String remark;
	
	public TbRtn() {
	}

	public TbRtn(String id) {
		this.id = id;
	}

	public TbRtn(String id, String devId, Integer rtnerId, 
			String date, String remark) {
		this.id = id;
		this.devId = devId;
		this.rtnerId = rtnerId;
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
	 * @return rtnerId
	 */
	public Integer getRtnerId() {
		return rtnerId;
	}

	/**
	 * @param rtnerId Ҫ���õ� rtnerId
	 */
	public void setRtnerId(Integer rtnerId) {
		this.rtnerId = rtnerId;
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
