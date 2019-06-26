package com.qzj.sqlOpr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class SqlOpr {// 数据库操作公共类
	// MySQL 8.0 以上版本数据库驱动
	protected static String dbClassName = "com.mysql.cj.jdbc.Driver";
	protected static String dbName = "db_devmngsys";// 数据库名称
	// 数据库路径
	protected static String dbUrl = "jdbc:mysql://localhost:3306/"// 数据库域名端口
			+ dbName + "?useSSL=false"// 显式关闭SSL连接
			+ "&serverTimezone=Asia/Shanghai"// 设置时区Asia/Shanghai
			+ "&allowPublicKeyRetrieval=true";// 允许客户端从服务器获取公钥
	protected static String dbUserId = "root";// 数据库用户名
	protected static String dbPwd = "123456";// 数据库密码
	public static Connection conn;// 数据库连接对象
	
	static {// 静态初始化数据库操作公共类
		if(conn == null) {
			try {
				Class.forName(dbClassName);
				conn = DriverManager.getConnection(dbUrl);
			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
				// 弹出提示框
				JOptionPane.showMessageDialog(null, "请将JDBC驱动包放置在lib文件夹中");
				System.exit(1);//非正常退出 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
