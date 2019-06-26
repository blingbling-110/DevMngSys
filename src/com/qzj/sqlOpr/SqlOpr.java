package com.qzj.sqlOpr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.qzj.Item;
import com.qzj.sqlOpr.model.TbDevInfo;

public class SqlOpr {// ���ݿ����������
	// MySQL 8.0 ���ϰ汾���ݿ�����
	protected static String dbClassName = "com.mysql.cj.jdbc.Driver";
	protected static String dbName = "db_devmngsys";// ���ݿ�����
	// ���ݿ�·��
	protected static String dbUrl = "jdbc:mysql://localhost:3306/"// ���ݿ������˿�
			+ dbName + "?useSSL=false"// ��ʽ�ر�SSL����
			+ "&serverTimezone=Asia/Shanghai"// ����ʱ��Asia/Shanghai
			+ "&allowPublicKeyRetrieval=true";// ����ͻ��˴ӷ�������ȡ��Կ
	protected static String dbUserId = "root";// ���ݿ��û���
	protected static String dbPwd = "123456";// ���ݿ�����
	protected static String exeTime;// ִ��SQL����ʱ��
	public static Connection conn;// ���ݿ����Ӷ���
	
	static {// ��̬��ʼ�����ݿ����������
		if(conn == null) {
			try {
				Class.forName(dbClassName);
				conn = DriverManager.getConnection(dbUrl);
			} catch (ClassNotFoundException e) {
				// ������ʾ��
				JOptionPane.showMessageDialog(null, "�뽫JDBC������������lib�ļ�����");
				System.exit(1);//�������˳� 
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "���ݿ����Ӷ��󴴽�ʧ��");
				System.exit(1);
			}
		}
	}
	
	// ��չ��췽������ֹ�������ݿ�����������ʵ������
	private SqlOpr() {
	}
	
	// ��ȡ�豸��Ϣ
	public static TbDevInfo getDevInfo(Item item) {
		String where = "name='" + item.getName() + "'";// ��ȡitem�����name����
		if(item.getId() != null)
			where = "id='" + item.getId() + "'";// ��ȡitem�����id����
		TbDevInfo info = new TbDevInfo();// �����豸��Ϣ����ģ��
		ResultSet res = findForRes("select * from tb_devinfo where" + where);
		// ��װ���ݵ�����ģ����
		try {
			if(res.next()) {
				info.setId(res.getString("id").trim());
				info.setName(res.getString("name").trim());
				info.setStatus(res.getString("status").trim());
				info.setDes(res.getString("des").trim());
				info.setRemark(res.getString("remark").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}

	// ��ȡSQL���Ľ����
	private static ResultSet findForRes(String sql) {
		if(conn == null)
			return null;
		long time = System.currentTimeMillis();// ��ȡ��ǰ����ֵ
		ResultSet res = null;
		try {
			/*
			 * ����ָ����������ͺͲ����Ե���������
			 * 
			 * ResultSet.TYPE_SCROLL_INSENSITIVE��
			 * ֧�ֽ����backforward ��random ��last ��first �Ȳ�����
			 * ������session �����ݿ������������ĸ����ǲ����еġ�
			 * ʵ�ַ����������ݿ�ȡ�����ݺ󣬻��ȫ�����ݻ��浽cache �У�
			 * �Խ�����ĺ����������ǲ�����cache �е����ݣ�
			 * ���ݿ��м�¼�����仯�󣬲�Ӱ��cache �е����ݣ�
			 * ����ResultSet �Խ�����е������ǲ����еġ�
			 * 
			 * ResultSet.CONCUR_READ_ONLY��
			 * ��ResultSet�е����ݼ�¼��ֻ���ģ��������޸�
			 */
			Statement sta = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			res = sta.executeQuery(sql);
			exeTime = ((System.currentTimeMillis() - time) / 1000d) + "";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	// ��ȡ�����豸��Ϣ
	public static List<List<String>> getAllDevInfo() {
		List<List<String>> list = findForList("select id, name from tb_devinfo");
		return list;
	}

	// ��ȡSQL����������ɵ������б�
	private static List<List<String>> findForList(String sql) {
		List<List<String>> list = new ArrayList<>();
		ResultSet res = findForRes(sql);
		try {
			/*
			 * �����������Ԫ����
			 * 
			 * ResultSetMetaData�����ڻ�ȡ��Ӧ��������е����ͺ�������Ϣ����
			 */
			ResultSetMetaData metaData = res.getMetaData();
			int colCount = metaData.getColumnCount();// ��ȡ������е�����
			while(res.next()) {
				List<String> row = new ArrayList<String>();
				for(int i = 1; i <= colCount; i++) {// ���л�ȡ�������Ϣ
					String str = res.getString(i);
					if(str != null && !str.isEmpty())
						str = str.trim();
					row.add(str);
				}
				list.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
