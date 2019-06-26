package com.qzj.sqlOpr.model;

public class TbBrw {// 借用公共类
	private String id;// 借用单编号
	private String dvId;// 借用设备编号
	private Integer brwerId;// 借用人编号
	private String date;// 借用日期
	private String remark;// 备注
	
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
	
	// 使用Getters和Setters方法将公共类的私有属性封装起来
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
