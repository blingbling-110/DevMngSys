package com.qzj.sqlOpr.model;

public class TbBrw {// ���ù�����
	private String id;// ���õ����
	private String dvId;// �����豸���
	private Integer brwerId;// �����˱��
	private String date;// ��������
	private String remark;// ��ע
	
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
	
	// ʹ��Getters��Setters�������������˽�����Է�װ����
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
	
	public Integer getBrwerId() {
		return brwerId;
	}
	
	public void setBrwerId(Integer brwerId) {
		this.brwerId = brwerId;
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
