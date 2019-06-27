package com.qzj.sqlOpr.model;

public class TbUserInfo {//	人员信息公共类
	private Integer id;//	人员编号
	private String name;//	姓名
	private String userId;//	用户名
	private String pwd;//	密码
	private String pos;//	职务
	private String dep;//	部门
	private String email;//	电子邮箱
	private String tel;//	电话
	private String remark;//	备注
	
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
	
	//	使用Getters和Setters方法将公共类的私有属性封装起来
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
