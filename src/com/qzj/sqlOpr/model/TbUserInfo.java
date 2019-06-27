package com.qzj.sqlOpr.model;

public class TbUserInfo {//	��Ա��Ϣ������
	private Integer id;//	��Ա���
	private String name;//	����
	private String userId;//	�û���
	private String pwd;//	����
	private String pos;//	ְ��
	private String dep;//	����
	private String email;//	��������
	private String tel;//	�绰
	private String remark;//	��ע
	
	public TbUserInfo() {
	}
	
	public TbUserInfo(Integer id) {
		this.id = id;
	}
	
	public TbUserInfo(Integer id, String name, String userId, 
			String pwd, String pos, String dep, String email, 
			String tel, String remark) {
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.pwd = pwd;
		this.pos = pos;
		this.dep = dep;
		this.email = email;
		this.tel = tel;
		this.remark = remark;
	}
	
	//	ʹ��Getters��Setters�������������˽�����Է�װ����
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getPos() {
		return pos;
	}
	
	public void setPos(String pos) {
		this.pos = pos;
	}
	
	public String getDep() {
		return dep;
	}
	
	public void setDep(String dep) {
		this.dep = dep;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
