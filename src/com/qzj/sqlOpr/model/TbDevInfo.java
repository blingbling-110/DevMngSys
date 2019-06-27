package com.qzj.sqlOpr.model;

public class TbDevInfo {//	�豸��Ϣ������
	private String id;//	�豸���
	private String name;//	�豸����
	private String status;//	�豸״̬
	private String des;//	�豸����
	private String remark;//	��ע
	
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

	//	ʹ��Getters��Setters�������������˽�����Է�װ����
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
	
	public String toString() {//	��дtoString()������ֻ����豸����
		return getName();
	}
	
	public int hashCode() {//	��дhashCode()������ɢ��ֵ������߲��Ҷ����Ч��
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((id == null)? 0 : id.hashCode());
		result = PRIME * result + ((name == null)? 0 : name.hashCode());
		result = PRIME * result + ((status == null)? 0 : status.hashCode());
		result = PRIME * result + ((des == null)? 0 : des.hashCode());
		result = PRIME * result + ((remark == null)? 0 : remark.hashCode());
		return result;
	}
	
	public boolean equals(Object obj) {//	��дequals���������ڱȽ����������Ƿ����
		if(this == obj)//	��������Ƿ�ָ��ͬһ�ڴ��ַ
			return true;
		if(obj == null)//	���obj�Ƿ�Ϊ��
			return false;
		if(getClass() != obj.getClass())//	������ߵ�����ʱ���Ƿ���ͬ
			return false;
		final TbDevInfo other = (TbDevInfo) obj;//	ǿ��ת��Ϊ�����������
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
