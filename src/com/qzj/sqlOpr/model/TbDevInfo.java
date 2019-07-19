package com.qzj.sqlOpr.model;

/**
 * 	�豸��Ϣ�����ࣺ���ڷ�װtb_devinfo���ݱ�
 * @author qinzijun
 *
 */
public class TbDevInfo {
	/**
	 * 	�豸���
	 */
	private String id;
	
	/**
	 * 	�豸����
	 */
	private String name;
	
	/**
	 * 	�豸״̬
	 */
	private String status;
	
	/**
	 * 	�豸����
	 */
	private String des;
	
	/**
	 * 	��ע
	 */
	private String remark;

	/**
	 * 	�����˵Ĺ���
	 */
	private String req;
	
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
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status Ҫ���õ� status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return des
	 */
	public String getDes() {
		return des;
	}

	/**
	 * @param des Ҫ���õ� des
	 */
	public void setDes(String des) {
		this.des = des;
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

	/**
	 * @return req
	 */
	public String getReq() {
		return req;
	}

	/**
	 * @param req Ҫ���õ� req
	 */
	public void setReq(String req) {
		this.req = req;
	}

	/**
	 * 	��дtoString()������ֻ����豸����
	 */
	public String toString() {
		return getName();
	}
	
	/**
	 * 	��дhashCode()������ɢ��ֵ������߲��Ҷ����Ч��
	 */
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((id == null)? 0 : id.hashCode());
		result = PRIME * result + ((name == null)? 0 : name.hashCode());
		result = PRIME * result + ((status == null)? 0 : status.hashCode());
		result = PRIME * result + ((des == null)? 0 : des.hashCode());
		result = PRIME * result + ((remark == null)? 0 : remark.hashCode());
		result = PRIME * result + ((req == null)? 0 : req.hashCode());
		return result;
	}
	
	/**
	 * 	��дequals���������ڱȽ����������Ƿ����
	 */
	public boolean equals(Object obj) {
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
		if(req == null) {
			if(other.req != null)
				return false;
		}else if(!req.equals(other.req))
			return false;
		return true;
	}
}
