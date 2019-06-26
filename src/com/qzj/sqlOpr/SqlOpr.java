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
	protected static String exeTime;// 执行SQL语句的时间
	public static Connection conn;// 数据库连接对象
	
	static {// 静态初始化数据库操作公共类
		if(conn == null) {
			try {
				Class.forName(dbClassName);
				conn = DriverManager.getConnection(dbUrl);
			} catch (ClassNotFoundException e) {
				// 弹出提示框
				JOptionPane.showMessageDialog(null, "请将JDBC驱动包放置在lib文件夹中");
				System.exit(1);//非正常退出 
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "数据库连接对象创建失败");
				System.exit(1);
			}
		}
	}
	
	// 封闭构造方法，禁止创建数据库操作公共类的实例对象
	private SqlOpr() {
	}
	
	// 读取设备信息
	public static TbDevInfo getDevInfo(Item item) {
		String where = "name='" + item.getName() + "'";// 获取item对象的name属性
		if(item.getId() != null)
			where = "id='" + item.getId() + "'";// 获取item对象的id属性
		TbDevInfo info = new TbDevInfo();// 创建设备信息数据模型
		ResultSet res = findForRes("select * from tb_devinfo where" + where);
		// 封装数据到数据模型中
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

	// 获取SQL语句的结果集
	private static ResultSet findForRes(String sql) {
		if(conn == null)
			return null;
		long time = System.currentTimeMillis();// 获取当前毫秒值
		ResultSet res = null;
		try {
			/*
			 * 创建指定结果集类型和并发性的声明对象
			 * 
			 * ResultSet.TYPE_SCROLL_INSENSITIVE：
			 * 支持结果集backforward ，random ，last ，first 等操作，
			 * 对其它session 对数据库中数据做出的更改是不敏感的。
			 * 实现方法：从数据库取出数据后，会把全部数据缓存到cache 中，
			 * 对结果集的后续操作，是操作的cache 中的数据，
			 * 数据库中记录发生变化后，不影响cache 中的数据，
			 * 所以ResultSet 对结果集中的数据是不敏感的。
			 * 
			 * ResultSet.CONCUR_READ_ONLY：
			 * 在ResultSet中的数据记录是只读的，不可以修改
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
	
	// 读取所有设备信息
	public static List<List<String>> getAllDevInfo() {
		List<List<String>> list = findForList("select id, name from tb_devinfo");
		return list;
	}

	// 获取SQL语句结果集构成的数组列表
	private static List<List<String>> findForList(String sql) {
		List<List<String>> list = new ArrayList<>();
		ResultSet res = findForRes(sql);
		try {
			/*
			 * 创建结果集的元数据
			 * 
			 * ResultSetMetaData：用于获取相应结果集中列的类型和属性信息的类
			 */
			ResultSetMetaData metaData = res.getMetaData();
			int colCount = metaData.getColumnCount();// 获取结果集中的列数
			while(res.next()) {
				List<String> row = new ArrayList<String>();
				for(int i = 1; i <= colCount; i++) {// 按列获取结果集信息
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
