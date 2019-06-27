package com.qzj.sqlOpr.model;

public class TbDevInfo {//	设备信息公共类
	private String id;//	设备编号
	private String name;//	设备名称
	private String status;//	设备状态
	private String des;//	设备描述
	private String remark;//	备注
	
	public TbDevInfo() {
	}
	
	public TbDevInfo(String id) {
		this.id = id;
	}
	
	public TbDevInfo(String id, String name, String status, 
			String des, String remark) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.des = des;
		this.remark = remark;
	}

	//	使用Getters和Setters方法将公共类的私有属性封装起来
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDes() {
		return des;
	}
	
	public void setDes(String des) {
		this.des = des;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String toString() {//	重写toString()方法，只输出设备名称
		return getName();
	}
	
	public int hashCode() {//	重写hashCode()方法，散列值用于提高查找对象的效率
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((id == null)? 0 : id.hashCode());
		result = PRIME * result + ((name == null)? 0 : name.hashCode());
		result = PRIME * result + ((status == null)? 0 : status.hashCode());
		result = PRIME * result + ((des == null)? 0 : des.hashCode());
		result = PRIME * result + ((remark == null)? 0 : remark.hashCode());
		return result;
	}
	
	public boolean equals(Object obj) {//	重写equals方法，用于比较两个对象是否相等
		if(this == obj)//	检查两者是否指向同一内存地址
			return true;
		if(obj == null)//	检查obj是否为空
			return false;
		if(getClass() != obj.getClass())//	检查两者的运行时类是否相同
			return false;
		final TbDevInfo other = (TbDevInfo) obj;//	强制转换为本公共类对象
		if(id == null) {
			if(other.id != null)
				return false;
		}else if(!id.equals(other.id))
			return false;
		if(name == null) {
			if(other.name != null)
				return false;
		}else if(!name.equals(other.name))
			return false;
		if(status == null) {
			if(other.status != null)
				return false;
		}else if(!status.equals(other.status))
			return false;
		if(des == null) {
			if(other.des != null)
				return false;
		}else if(!des.equals(other.des))
			return false;
		if(remark == null) {
			if(other.remark != null)
				return false;
		}else if(!remark.equals(other.remark))
			return false;
		return true;
	}
}
