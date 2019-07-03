package com.qzj.sqlOpr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.io.OutputStreamWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import com.qzj.Item;
import com.qzj.sqlOpr.model.TbBrw;
import com.qzj.sqlOpr.model.TbDevInfo;
import com.qzj.sqlOpr.model.TbRtn;
import com.qzj.sqlOpr.model.TbUserInfo;

/**
 * 	数据库操作公共类：封装了系统所需的所有数据库操作
 * @author qinzijun
 *
 */
public class SqlOpr {
	/**
	 * 	MySQL 8.0 以上版本数据库驱动
	 */
	protected static String dbClassName = "com.mysql.cj.jdbc.Driver";
	
	/**
	 * 	数据库名称
	 */
	protected static String dbName = "db_devmngsys";
	
	/**
	 * 	数据库路径
	 */
	protected static String dbUrl = "jdbc:mysql://localhost:3306/"
			+ dbName + "?useSSL=false"//	显式关闭SSL连接
			+ "&serverTimezone=Asia/Shanghai"//	设置时区Asia/Shanghai
			+ "&allowPublicKeyRetrieval=true";//	允许客户端从服务器取回公钥
	
	/**
	 * 	数据库用户名
	 */
	protected static String dbUserId = "root";
	
	/**
	 * 	数据库密码
	 */
	protected static String dbPwd = "123456";
	
	/**
	 * 	执行SQL语句的时间
	 */
	protected static String exeTime;
	
	/**
	 * 	数据库连接对象
	 */
	public static Connection conn;
	
	/**
	 * 	静态初始化数据库操作公共类
	 */
	static {
		if(conn == null) {
			try {
				Class.forName(dbClassName);
				conn = DriverManager.getConnection(dbUrl, dbUserId, dbPwd);
			} catch (ClassNotFoundException e) {
				//	弹出提示框
				JOptionPane.showMessageDialog(null, "请将JDBC驱动包放置在lib文件夹中");
				System.exit(1);//	非正常退出 
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "数据库连接对象创建失败");
				System.exit(1);
			}
		}
	}
	
	/**
	 * 	封闭构造方法，禁止创建数据库操作公共类的实例对象
	 */
	private SqlOpr() {
	}
	
	/**
	 * 	读取设备信息
	 * @param item 欲读取的设备
	 * @return 该设备的设备信息公共类对象
	 */
	public static TbDevInfo getDevInfo(Item item) {
		String where = "name='" + item.getName() + "'";//	获取item对象的name属性
		if(item.getId() != null)
			where = "id='" + item.getId() + "'";//	获取item对象的id属性
		TbDevInfo info = new TbDevInfo();//	创建设备信息数据模型
		ResultSet res = findForRes("select * from tb_devinfo where" + where);
		//	封装数据到数据模型中
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
	
	/**
	 * 	读取用户信息
	 * @param item 欲读取的用户
	 * @return 该用户的人员信息公共类对象
	 */
	public static TbUserInfo getUserInfo(Item item) {
		String where = "name='" + item.getName() + "'";
		if(item.getJobNum() != 0)
			where = "id='" + item.getJobNum() + "'";
		TbUserInfo info = new TbUserInfo();
		ResultSet res = findForRes("select * from tb_userinfo where" + where);
		try {
			if(res.next()) {
				info.setId(res.getInt("id"));
				info.setName(res.getString("name").trim());
				info.setUserId(res.getString("userid").trim());
				info.setPos(res.getString("pos").trim());
				info.setDep(res.getString("dep").trim());
				info.setEmail(res.getString("email").trim());
				info.setTel(res.getString("tel").trim());
				info.setRemark(res.getString("remark").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}

	/**
	 * 	获取SQL语句的结果集
	 * @param sql 欲执行的SQL语句
	 * @return 数据库返回的结果集
	 */
	private static ResultSet findForRes(String sql) {
		if(conn == null)
			return null;
		long time = System.currentTimeMillis();//	获取当前毫秒值
		ResultSet res = null;
		try {
			/*
			 * 	创建指定结果集类型和并发性的连接声明对象
			 * 
			 * 	ResultSet.TYPE_SCROLL_INSENSITIVE：
			 * 	支持结果集backforward ，random ，last ，first 等操作，
			 * 		对其它session 对数据库中数据做出的更改是不敏感的。
			 * 	实现方法：从数据库取出数据后，会把全部数据缓存到cache 中，
			 * 		对结果集的后续操作，是操作的cache 中的数据，
			 * 		数据库中记录发生变化后，不影响cache 中的数据，
			 * 		所以ResultSet 对结果集中的数据是不敏感的。
			 * 
			 * 	ResultSet.CONCUR_READ_ONLY：
			 * 	在ResultSet中的数据记录是只读的，不可以更改
			 */
			Statement sta = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			res = sta.executeQuery(sql);
			//	计算执行SQL语句所用时间
			exeTime = ((System.currentTimeMillis() - time) / 1000d) + "";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * 	读取所有设备信息
	 * @return 包含所有设备信息的List集合
	 */
	public static List<List<String>> getAllDevInfo() {
		List<List<String>> list = findForList(
				"select id, name from tb_devinfo");
		return list;
	}
	
	/**
	 * 	读取所有用户信息
	 * @return 包含所有用户信息的List集合
	 */
	public static List<List<String>> getAllUserInfo() {
		List<List<String>> list = findForList(
				"select name, userid from tb_userinfo");
		return list;
	}

	/**
	 * 	获取SQL语句结果集构成的数组列表
	 * @param sql 欲执行的SQL语句
	 * @return 包含所有结果信息的List集合
	 */
	private static List<List<String>> findForList(String sql) {
		List<List<String>> list = new ArrayList<>();
		ResultSet res = findForRes(sql);
		try {
			/*
			 * 	创建结果集的元数据
			 * 
			 * 	ResultSetMetaData：用于获取相应结果集中列的类型和属性信息的类
			 */
			ResultSetMetaData metaData = res.getMetaData();
			int colCount = metaData.getColumnCount();//	获取结果集中的列数
			while(res.next()) {
				ArrayList<String> row = new ArrayList<String>();
				for(int i = 1; i <= colCount; i++) {//	按列获取结果集信息
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
	
	/**
	 * 	验证用户登录
	 * @param userid 用户名
	 * @param pwd 密码
	 * @return 登录成功与否
	 * @throws SQLException
	 */
	public static boolean checkLogin(String userid, String pwd) 
			throws SQLException {
		if(conn == null)
			JOptionPane.showMessageDialog(null, "尚未创建数据库连接对象");;
		PreparedStatement preSta = conn.prepareStatement(//	通过预处理防范SQL注入
				"select * from tb_userinfo where userid=? and pwd=?");
		preSta.setString(1, userid);
		preSta.setString(2, pwd);
		ResultSet res = preSta.executeQuery();
		return res.next();
	}
	
	/*
	 * 	在事务中增加借用信息（保持数据的完整性）
	 * 
	 * 	JDBC中事务处理的步骤：
	 * 	1、取消JDBC的自动提交；
	 * 	2、执行各个SQL语句；
	 * 	3、如果所有语句执行成功，则提交事务；如果出现了错误，则回滚。
	 */
	/**
	 * TODO
	 * @param brw
	 * @return
	 */
	public static boolean insertTbBrw(TbBrw brw) {
		try {
			boolean autoCommit = conn.getAutoCommit();//	获取提交方式
			conn.setAutoCommit(false);//	取消自动提交
			//	增加借用表记录
			insert("insert into tb_brw values('"+ brw.getId()
			+ "','" + brw.getDvId() + "'," + brw.getBrwerId()
			+ ",'" + brw.getDate() + "','" + brw.getRemark() + "')");
			//	更改设备信息
			Item item = new Item();
			item.setId(brw.getDvId());//	获取借用设备编号
			TbDevInfo devInfo = getDevInfo(item);
			boolean res = false;
			if(devInfo.getId() != null && !devInfo.getId().isEmpty())
				res = update("update tb_devinfo set status='" 
						+ brw.getBrwerId() + "',remark='" 
						+ brw.getId() + "' where id='"
						+ devInfo.getId() + "'");
			if(res)
				conn.commit();//	提交事务
			else
				conn.rollback();//	回滚
			conn.setAutoCommit(autoCommit);//	还原提交方式
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	//	在事务中增加归还信息
	public static boolean insertTbRtn(TbRtn rtn) {
		try {
			boolean autoCommit = conn.getAutoCommit();//	获取提交方式
			conn.setAutoCommit(false);//	取消自动提交
			//	增加归还表记录
			insert("insert into tb_rtn values('"+ rtn.getId()
			+ "','" + rtn.getDvId() + "'," + rtn.getRtnerId()
			+ ",'" + rtn.getDate() + "','" + rtn.getRemark() + "')");
			//	更改设备信息
			Item item = new Item();
			item.setId(rtn.getDvId());//	获取归还设备编号
			TbDevInfo devInfo = getDevInfo(item);
			boolean res = false;
			if(devInfo.getId() != null && !devInfo.getId().isEmpty())
				res = update("update tb_devinfo set status='" 
						+ "库存中" + "',remark='" 
						+ rtn.getId() + "' where id='"
						+ devInfo.getId() + "'");
			if(res)
				conn.commit();//	提交事务
			else
				conn.rollback();//	回滚
			conn.setAutoCommit(autoCommit);//	还原提交方式
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	//	执行SQL语句更改数据
	private static boolean update(String sql) {
		boolean res = false;
		try {
			PreparedStatement preSta = conn.prepareStatement(sql);
			res = preSta.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	//	执行SQL语句增加数据
	private static boolean insert(String sql) {
		boolean res = false;
		try {
			PreparedStatement preSta = conn.prepareStatement(sql);
			res = preSta.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	//	数据库备份
	public static String backup() throws SQLException {
		LinkedList<String> sqls = new LinkedList<>();//	备份文件中的所有SQL语句
		//	需备份的数据表名称
		String tables[] = {"tb_brw", "tb_devinfo", "tb_rtn", "tb_userinfo"};
		ArrayList<Tables> tableList = new ArrayList<>();//	数据表对象的集合
		
		//	获取各数据表对象
		for(int i = 0; i < tables.length; i++) {//	遍历数据表名称数组
			Statement sta = conn.createStatement();
			//	desc <table name>等价于show columns from <table name>
			ResultSet res = sta.executeQuery("desc" + tables[i]);//	查询数据表结构
			ArrayList<Columns> columns = new ArrayList<>();//	列模型对象的集合
			while(res.next()) {
				Columns column = new Columns();
				column.setName(res.getString("Field"));//	获取列名称
				column.setType(res.getString("Type"));//	获取列类型
				if(res.getString("Null").equals("YES"))//	获取列是否可为空
					column.setNull(true);
				if(res.getString("Key").equals("PRI")) {//	获取列是否为主键
					column.setKey(true);
					//	获取列是否自增
					if(res.getString("Extra").equals("auto_increment"))
						column.setIncrement(true);
				}
				columns.add(column);
			}
			//	按数据表名称和相应列模型对象实例化数据表模型
			Tables table = new Tables(tables[i], columns);
			tableList.add(table);
			res.close();//	关闭结果集
			sta.close();//	关闭连接声明
		}
		
		//	增加删除、创建数据表和插入数据的SQL语句
		for(int i = 0; i < tableList.size(); i++) {//	遍历数据表对象的集合
			Tables table = tableList.get(i);//	获取数据表对象
			ArrayList<Columns> columns = table.getColumns();//	获取该数据表的列模型
			/*
			 * 	drop table <table name>：
			 * 	删除数据表全部数据和表结构，立刻释放磁盘空间，不管是 Innodb 和 MyISAM
			 */
			sqls.add("drop table if exists " + table.getName() + ";");
			
			//	创建数据表的SQL语句
			StringBuilder createSql = new StringBuilder();
			createSql.append("create table `" + table.getName() + "`(");
			for(int j = 0; j < columns.size(); j++) {//	遍历该数据表的列模型
				Columns column = columns.get(j);
				createSql.append("`" + column.getName() //	获取列名称
				+ "` " + column.getType());//	获取列类型
				if(!column.isNull())//	获取列是否可为空
					createSql.append(" not null");
				if(column.isKey()) {//	获取列是否为主键
					createSql.append(" primary key");
					if(column.isIncrement())//	获取列是否自增
						createSql.append(" auto_increment");
				}
				
				if(j < columns.size() - 1)//	判断是否为最后一列
					createSql.append(",");
				else
					createSql.append(") engine=innodb default charset=utf8;");
			}
			sqls.add(createSql.toString());
			
			//	插入数据的SQL语句
			Statement sta = conn.createStatement();
			ResultSet res = sta.executeQuery("select * from" + table.getName());
			while(res.next()) {
				StringBuilder insertSql = new StringBuilder();
				insertSql.append("insert into " + table.getName() + " values(");
				for(int j = 0; j < columns.size(); j++) {//	遍历该数据表的列模型
					Columns column = columns.get(j);
					String type = column.getType();//	获取列类型
					//	若列类型为字符型，则需用引号包围
					if(type.startsWith("varchar") ||
							type.startsWith("char") ||
							type.startsWith("datetime"))
						insertSql.append("'"
							+ res.getString(column.getName()) + "'");
					else
						insertSql.append(res.getString(column.getName()));
					
					if(j < columns.size() - 1)//	判断是否为最后一列
						insertSql.append(",");
					else
						insertSql.append(");");
				}
				sqls.add(insertSql.toString());
			}
			res.close();
			sta.close();
		}
		
		/*
		 * 	增加删除、创建视图的SQL语句
		 * 
		 * 	视图（view）：
		 * 		是一种虚拟存在的逻辑表，本身并不包含数据，
		 * 		而是作为一个select语句保存在数据字典中。
		 * 		使用视图的大部分情况是为了保障数据安全性，提高查询效率。
		 */
		sqls.add("drop view if exists v_brwInfo;");
		sqls.add("create view v_brwInfo as "
				+ "select tb_brw.id, tb_devinfo.name as devname, tb_brw.dvid, "
				+ "tb_userinfo.name as username, tb_userinfo.email, "
				+ "tb_userinfo.tel from tb_brw "
				+ "inner join tb_devinfo on tb_brw.dvid = tb_devinfo.id "
				+ "inner join tb_userinfo on tb_brw.brwerid = tb_userinfo.id;");
		sqls.add("drop view if exists v_rtninfo;");
		sqls.add("create view v_rtninfo as "
				+ "select tb_rtn.id, tb_devinfo.name as devname, tb_rtn.dvid, "
				+ "tb_userinfo.name as username, tb_userinfo.email, "
				+ "tb_userinfo.tel from tb_rtn "
				+ "inner join tb_devinfo on tb_rtn.dvid = tb_devinfo.id "
				+ "inner join tb_userinfo on tb_rtn.rtnerid = tb_userinfo.id;");
		
		//	输出备份文件
		Date date = new Date();//	获取当前毫秒值
		/*
		 * 	设置当前时间的输出格式
		 * 
		 * 	SimpleDateFormat：
		 * 		是一个以国别敏感的方式格式化和分析数据的具体类。
		 * 		它允许格式化 (date -> text)、语法分析 (text -> date)和标准化。
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		//	备份文件路径
		String backupFilePath = "backup\\" + sdf.format(date) + ".sql";
		File sqlFile = new File(backupFilePath);//	创建SQL文件对象
		FileOutputStream fos = null;//	创建文件字节输出流
		OutputStreamWriter osw = null;//	创建字符输出流
		BufferedWriter bw = null;//	创建缓冲字符输出流
		try {
			fos = new FileOutputStream(sqlFile);
			osw = new OutputStreamWriter(fos);
			bw = new BufferedWriter(osw);
			for(String sql: sqls) {//	遍历备份文件中的所有SQL语句
				bw.write(sql);
				bw.newLine();
				bw.flush();//	刷新缓冲字符流
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//	倒序依次关闭输出流
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(osw != null) {
				try {
					osw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return backupFilePath;
	}
	
	//	数据库恢复
	public static void restore(String backupFilePath) {
		File sqlFile = new File(backupFilePath);//	创建SQL文件对象
		FileInputStream fis = null;//	创建文件字节输入流
		InputStreamReader isr = null;//	创建字符输入流
		BufferedReader br = null;//	创建缓冲字符输入流
		Statement sta = null;//	创建连接声明
		try {
			fis = new FileInputStream(sqlFile);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String oneLine = null;
			while((oneLine = br.readLine()) != null) {
				if(!oneLine.trim().equals("")) {
					sta = conn.createStatement();
					sta.executeUpdate(oneLine);//	执行SQL语句
					sta.close();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//	倒序依次关闭输出流
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String getUserNameFromUserId(String userId) 
			throws SQLException {//	由用户名获取用户姓名
		PreparedStatement preSta = conn.prepareStatement(
				"select name from tb_userinfo where userid=?");
		preSta.setString(1, userId);
		ResultSet res = preSta.executeQuery();
		String userName = null;
		if(res.next())
			userName = res.getString("name");
		return userName;
	}
}
