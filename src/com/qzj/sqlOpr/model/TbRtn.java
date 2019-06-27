package com.qzj.sqlOpr.model;

public class TbRtn {//	归还公共类
	private String id;//	归还单编号
	private String dvId;//	归还设备编号
	private Integer rtnerId;//	归还人编号
	private String date;//	归还日期
	private String remark;//	备注
	
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
	
	//	使用Getters和Setters方法将公共类的私有属性封装起来
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
