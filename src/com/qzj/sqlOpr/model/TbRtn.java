package com.qzj.sqlOpr.model;

public class TbRtn {//	�黹������
	private String id;//	�黹�����
	private String dvId;//	�黹�豸���
	private Integer rtnerId;//	�黹�˱��
	private String date;//	�黹����
	private String remark;//	��ע
	
	public TbRtn() {
	}

	public TbRtn(String id) {
		this.id = id;
	}

	public TbRtn(String id, String dvId, Integer rtnerId, 
			String date, String remark) {
		this.id = id;
		this.dvId = dvId;
		this.rtnerId = rtnerId;
		this.date = date;
		this.remark = remark;
	}
	
	//	ʹ��Getters��Setters�������������˽�����Է�װ����
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDvId() {
		return dvId;
	}
	
	public void setDvId(String dvId) {
		this.dvId = dvId;
	}
	
	public Integer getRtnerId() {
		return rtnerId;
	}
	
	public void setRtnerId(Integer rtnerId) {
		this.rtnerId = rtnerId;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
