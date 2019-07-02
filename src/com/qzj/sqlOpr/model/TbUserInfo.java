package com.qzj.sqlOpr.model;

/**
 * 	��Ա��Ϣ�����ࣺ���ڷ�װtb_userinfo���ݱ�
 * @author qinzijun
 *
 */
public class TbUserInfo {
	/**
	 * 	��Ա���
	 */
	private Integer id;
	
	/**
	 * 	����
	 */
	private String name;
	
	/**
	 * 	�û���
	 */
	private String userId;
	
	/**
	 * 	����
	 */
	private String pwd;
	
	/**
	 * 	ְ��
	 */
	private String pos;
	
	/**
	 * 	����
	 */
	private String dep;
	
	/**
	 * 	��������
	 */
	private String email;
	
	/**
	 * 	�绰
	 */
	private String tel;
	
	/**
	 * 	��ע
	 */
	private String remark;
	
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
	
	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id Ҫ���õ� id
	 */
	public void setId(Integer id) {
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
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId Ҫ���õ� userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @param pwd Ҫ���õ� pwd
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * @return pos
	 */
	public String getPos() {
		return pos;
	}

	/**
	 * @param pos Ҫ���õ� pos
	 */
	public void setPos(String pos) {
		this.pos = pos;
	}

	/**
	 * @return dep
	 */
	public String getDep() {
		return dep;
	}

	/**
	 * @param dep Ҫ���õ� dep
	 */
	public void setDep(String dep) {
		this.dep = dep;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email Ҫ���õ� email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel Ҫ���õ� tel
	 */
	public void setTel(String tel) {
		this.tel = tel;
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
