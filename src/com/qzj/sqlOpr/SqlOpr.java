package com.qzj.sqlOpr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

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
	public static Connection conn;// ���ݿ����Ӷ���
	
	static {// ��̬��ʼ�����ݿ����������
		if(conn == null) {
			try {
				Class.forName(dbClassName);
				conn = DriverManager.getConnection(dbUrl);
			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
				// ������ʾ��
				JOptionPane.showMessageDialog(null, "�뽫JDBC������������lib�ļ�����");
				System.exit(1);//�������˳� 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
