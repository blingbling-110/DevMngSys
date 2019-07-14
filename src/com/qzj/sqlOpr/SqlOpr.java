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
	protected static String DB_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	
	/**
	 * 	数据库名称
	 */
	protected static String DB_NAME = "db_devmngsys";
	
	/**
	 * 	数据库路径
	 */
	protected static String DB_URL = "jdbc:mysql://localhost:3306/"
			+ DB_NAME + "?useSSL=false"//	显式关闭SSL连接
			+ "&serverTimezone=Asia/Shanghai"//	设置时区Asia/Shanghai
			+ "&allowPublicKeyRetrieval=true";//	允许客户端从服务器取回公钥
	
	/**
	 * 	数据库用户名
	 */
	protected static String DB_USER_ID = "root";
	
	/**
	 * 	数据库密码
	 */
	protected static String DB_PWD = "123456";
	
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
				Class.forName(DB_CLASS_NAME);
				conn = DriverManager.getConnection(DB_URL, DB_USER_ID, DB_PWD);
			} catch (ClassNotFoundException e) {
				//	弹出提示框
				JOptionPane.showMessageDialog(null, 
						"请将JDBC驱动包放置在lib文件夹中", 
						"数据库错误", JOptionPane.ERROR_MESSAGE);
				System.exit(1);//	非正常退出 
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "数据库连接对象创建失败", 
						"数据库错误", JOptionPane.ERROR_MESSAGE);
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
		ResultSet res = findForRes("select * from tb_devinfo where " + where);
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
		if(item.getJobNum() != null)
			where = "id=" + item.getJobNum();
		TbUserInfo info = new TbUserInfo();
		ResultSet res = findForRes("select * from tb_userinfo where " + where);
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
				info.setAdmin(res.getBoolean("isadmin"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}
	
	/**
	 * 	读取借用单
	 * @param item 欲读取的借用单
	 * @return 该借用单的公共类对象
	 */
	public static TbBrw getBrw(Item item) {
		String where = "id='" + item.getId() + "'";
		TbBrw brw = new TbBrw();
		ResultSet res = findForRes("select * from tb_brw where " + where);
		try {
			if(res.next()) {
				brw.setId(res.getString("id").trim());
				brw.setDevId(res.getString("devid").trim());
				brw.setBrwerId(res.getInt("brwerid"));
				brw.setDate(res.getString("date").trim());
				brw.setRemark(res.getString("remark").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return brw;
	}
	
	/**
	 * 	读取归还单
	 * @param item 欲读取的归还单
	 * @return 该归还单的公共类对象
	 */
	public static TbRtn getRtn(Item item) {
		String where = "id='" + item.getId() + "'";
		TbRtn rtn = new TbRtn();
		ResultSet res = findForRes("select * from tb_rtn where " + where);
		try {
			if(res.next()) {
				rtn.setId(res.getString("id").trim());
				rtn.setDevId(res.getString("devid").trim());
				rtn.setRtnerId(res.getInt("rtnerid"));
				rtn.setDate(res.getString("date").trim());
				rtn.setRemark(res.getString("remark").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rtn;
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
			 * 		对其它会话 对数据库中数据做出的更改是不敏感的。
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
				"select id, name, userid from tb_userinfo");
		return list;
	}
	
	/**
	 * 	读取所有借用单
	 * @return 包含所有借用单的List集合
	 */
	public static List<List<String>> getAllBrw() {
		List<List<String>> list = findForList(
				"select id from tb_brw order by id desc");//	倒序
		return list;
	}
	
	/**
	 * 	读取所有归还单
	 * @return 包含所有归还单的List集合
	 */
	public static List<List<String>> getAllRtn() {
		List<List<String>> list = findForList(
				"select id from tb_rtn order by id desc");//	倒序
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
			JOptionPane.showMessageDialog(null, "尚未创建数据库连接对象", 
					"数据库错误", JOptionPane.ERROR_MESSAGE);
		PreparedStatement preSta = conn.prepareStatement(//	通过预处理防范SQL注入
				"select * from tb_userinfo where userid=? and pwd=?");
		preSta.setString(1, userid);
		preSta.setString(2, pwd);
		ResultSet res = preSta.executeQuery();
		return res.next();
	}
	
	/**
	 * 	在事务中增加借用信息（保持数据的完整性）
	 * <p>
	 * 	JDBC中事务处理的步骤：
	 * 	1、取消JDBC的自动提交；
	 * 	2、执行各个SQL语句；
	 * 	3、如果所有语句执行成功，则提交事务；如果出现了错误，则回滚。
	 * @param brw 欲插入数据库的借用公共类对象
	 * @return 数据插入成功与否
	 */
	public static boolean insertTbBrw(TbBrw brw) {
		try {
			boolean autoCommit = conn.getAutoCommit();//	获取提交方式
			conn.setAutoCommit(false);//	取消自动提交
			//	增加借用表记录
			exeUpdate("insert into tb_brw values('"+ brw.getId()
			+ "','" + brw.getDevId() + "'," + brw.getBrwerId()
			+ ",'" + brw.getDate() + "','" + brw.getRemark() + "')");
			//	更改设备信息
			Item item = new Item();
			item.setId(brw.getDevId());//	获取借用设备编号
			TbDevInfo devInfo = getDevInfo(item);
			boolean res = false;
			if(devInfo.getId() != null && !devInfo.getId().isEmpty())
				res = exeUpdate("update tb_devinfo set status='" 
						+ "工号：" + brw.getBrwerId() 
						+ "',remark='" + brw.getRemark() 
						+ "' where id='" + devInfo.getId() + "'");
			if(res)
				conn.commit();//	提交事务
			else {
				conn.rollback();//	回滚
				return false;
			}
			conn.setAutoCommit(autoCommit);//	还原提交方式
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * 	在事务中增加归还信息
	 * @param rtn 欲插入数据库的归还公共类对象
	 * @return 数据插入成功与否
	 */
	public static boolean insertTbRtn(TbRtn rtn) {
		try {
			boolean autoCommit = conn.getAutoCommit();//	获取提交方式
			conn.setAutoCommit(false);//	取消自动提交
			//	增加归还表记录
			exeUpdate("insert into tb_rtn values('"+ rtn.getId()
			+ "','" + rtn.getDevId() + "'," + rtn.getRtnerId()
			+ ",'" + rtn.getDate() + "','" + rtn.getRemark() + "')");
			//	更改设备信息
			Item item = new Item();
			item.setId(rtn.getDevId());//	获取归还设备编号
			TbDevInfo devInfo = getDevInfo(item);
			boolean res = false;
			if(devInfo.getId() != null && !devInfo.getId().isEmpty())
				res = exeUpdate("update tb_devinfo set status='" 
						+ "库存中" + "',remark='" + rtn.getRemark() 
						+ "' where id='" + devInfo.getId() + "'");
			if(res)
				conn.commit();//	提交事务
			else {
				conn.rollback();//	回滚
				return false;
			}
			conn.setAutoCommit(autoCommit);//	还原提交方式
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	/**
	 * 	执行SQL语句进行增、删、改操作
	 * @param sql 欲执行的SQL语句
	 * @return SQL语句更改成功与否
	 */
	private static boolean exeUpdate(String sql) {
		boolean res = false;
		try {
			PreparedStatement preSta = conn.prepareStatement(sql);
			int row = preSta.executeUpdate();
			if(row > 0)
				res = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * 	数据库备份
	 * @return 备份文件路径
	 * @throws SQLException
	 */
	public static String backup() throws SQLException {
		LinkedList<String> sqls = new LinkedList<>();//	备份文件中的所有SQL语句
		//	需备份的数据表名称
		String tables[] = {"tb_brw", "tb_devinfo", "tb_rtn", "tb_userinfo"};
		ArrayList<Tables> tableList = new ArrayList<>();//	数据表对象的集合
		
		//	获取各数据表对象
		for(int i = 0; i < tables.length; i++) {//	遍历数据表名称数组
			Statement sta = conn.createStatement();
			//	desc <table name>等价于show columns from <table name>
			ResultSet res = sta.executeQuery("desc " + tables[i]);//	查询数据表结构
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
			ResultSet res = sta.executeQuery("select * from " + table.getName());
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
		return sqlFile.getAbsolutePath();//	返回备份文件的绝对路径
	}
	
	/**
	 * 	数据库恢复
	 * @param backupFilePath 欲恢复的备份文件路径
	 */
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
	
	/**
	 * 	由用户名获取用户姓名
	 * @param userId 用户名
	 * @return 用户姓名
	 * @throws SQLException
	 */
	public static String getUserNameFromUserId(String userId) 
			throws SQLException {
		PreparedStatement preSta = conn.prepareStatement(
				"select name from tb_userinfo where userid=?");
		preSta.setString(1, userId);
		ResultSet res = preSta.executeQuery();
		String userName = null;
		if(res.next())
			userName = res.getString("name");
		return userName;
	}
	
	/**
	 * 	在事务中增加设备信息
	 * @param devInfo 欲插入数据库的设备信息公共类对象
	 * @return 数据插入成功与否
	 */
	public static boolean insertTbDevInfo(TbDevInfo devInfo) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			boolean res = false;
			if(devInfo.getId() != null && !devInfo.getId().isEmpty())
				res = exeUpdate("insert into tb_devinfo values('"
						+ devInfo.getId() + "','" + devInfo.getName()
						+ "','" + devInfo.getStatus() + "','" + devInfo.getDes()
						+ "','" + devInfo.getRemark() + "')");
			if(res)
				conn.commit();
			else {
				conn.rollback();
				return false;
			}
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * 	在事务中删除设备信息
	 * @param id 欲删除设备的编号
	 * @return 数据删除成功与否
	 */
	public static boolean deleteDevInfo(String id) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			boolean res = false;
			if(id != null && !id.isEmpty())
				res = exeUpdate("delete from tb_devinfo where id='" + id + "'");
			if(res)
				conn.commit();
			else {
				conn.rollback();
				return false;
			}
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	/**
	 * 	在事务中增加人员信息
	 * @param userInfo 欲插入数据库的人员信息公共类对象
	 * @return 数据插入成功与否
	 */
	public static boolean insertTbUserInfo(TbUserInfo userInfo) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			boolean res = false;
			if(userInfo.getId() != null)
				res = exeUpdate("insert into tb_userinfo values("
						+ userInfo.getId() + ",'" + userInfo.getName()
						+ "','" + userInfo.getUserId() + "','"
						+ userInfo.getPwd() + "','" + userInfo.getPos()
						+ "','" + userInfo.getDep() + "','"
						+ userInfo.getEmail() + "','" + userInfo.getTel()
						+ "','" + userInfo.getRemark() + "'," 
						+ userInfo.isAdmin() + ")");
			if(res)
				conn.commit();
			else {
				conn.rollback();
				return false;
			}
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 	在事务中删除人员信息
	 * @param id 欲删除人员的工号
	 * @return 数据删除成功与否
	 */
	public static boolean deleteUserInfo(String id) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			boolean res = false;
			if(id != null && !id.isEmpty())
				res = exeUpdate("delete from tb_userinfo where id=" + id);
			if(res)
				conn.commit();
			else {
				conn.rollback();
				return false;
			}
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * 	由用户名获取用户是否拥有管理权限
	 * @param userId 用户名
	 * @return 用户是否拥有管理权限
	 * @throws SQLException
	 */
	public static boolean isAdminFromUserId(String userId) throws SQLException {
		boolean isAdmin = false;
		PreparedStatement preSta = conn.prepareStatement(
				"select isadmin from tb_userinfo where userid='"
				+ userId + "'");
		ResultSet res = preSta.executeQuery();
		if(res.next())
			isAdmin = res.getBoolean("isadmin");
		return isAdmin;
	}
	
	/**
	 * 	由设备编号获取设备状态
	 * @param devId 设备编号
	 * @return 设备状态
	 * @throws SQLException
	 */
	public static String getStatusFromDevId(String devId) 
			throws SQLException {
		PreparedStatement preSta = conn.prepareStatement(
				"select status from tb_devinfo where id=?");
		preSta.setString(1, devId);
		ResultSet res = preSta.executeQuery();
		String status = null;
		if(res.next())
			status = res.getString("status");
		return status;
	}
	
	/**
	 * 	修改密码
	 * @param userId 用户名
	 * @param oriPwd 原密码
	 * @param newPwd 新密码
	 * @return 密码修改成功与否
	 */
	public static boolean changePwd(String userId, 
			String oriPwd, String newPwd) {
		boolean res = false;
		res = exeUpdate("update tb_userinfo set pwd='" + newPwd 
				+ "' where userid='" + userId + "' and pwd='"
				+ oriPwd + "'");
		return res;
	}

	/**
	 * 	搜索设备信息
	 * @param iId 欲搜索的设备编号
	 * @param iName 欲搜索的设备名称
	 * @param iStatus 欲搜索的设备状态
	 * @param iDes 欲搜索的设备描述
	 * @param iRemark 欲搜索的备注
	 * @return 包含返回的设备信息的List集合
	 */
	public static List<List<String>> searchDevInfo(String iId, 
			String iName, String iStatus, String iDes, String iRemark) {
		if(iId == null || iId.isEmpty())
			iId = "%";
		if(iName == null || iName.isEmpty())
			iName = "%";
		if(iStatus == null || iStatus.isEmpty())
			iStatus = "%";
		if(iDes == null || iDes.isEmpty())
			iDes = "%";
		if(iRemark == null || iRemark.isEmpty())
			iRemark = "%";
		String sql = "select id, name from tb_devinfo where "
				+ "id like '" + iId + "' and name like '" + iName
				+ "' and status like '" + iStatus + "' and des like '"
				+ iDes + "' and remark like '" + iRemark + "'";
		List<List<String>> list = findForList(sql);
		return list;
	}
	
	/**
	 * 	在事务中更新设备信息
	 * @param devInfo 欲更新的设备信息公共类对象
	 * @return 数据更新成功与否
	 */
	public static boolean updateTbDevInfo(TbDevInfo devInfo) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			boolean res = false;
			if(devInfo.getId() != null && !devInfo.getId().isEmpty())
				res = exeUpdate("update tb_devinfo set name='" 
						+ devInfo.getName() + "', des='" + devInfo.getDes()
						+ "', remark='" + devInfo.getRemark()
						+ "' where id='" + devInfo.getId() + "'");
			if(res)
				conn.commit();
			else {
				conn.rollback();
				return false;
			}
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	/**
	 * 	搜索人员信息
	 * @param iId 欲搜索的工号
	 * @param iName 欲搜索的姓名
	 * @param iUserId 欲搜索的用户名
	 * @param iPos 欲搜索的职位
	 * @param iDep 欲搜索的部门
	 * @param iEmail 欲搜索的邮箱
	 * @param iTel 欲搜索的电话
	 * @param iRemark 欲搜索的备注
	 * @return 包含返回的人员信息的List集合
	 */
	public static List<List<String>> searchUserInfo(String iId, 
			String iName, String iUserId, String iPos, String iDep, 
			String iEmail, String iTel, String iRemark, boolean iIsAdmin) {
		if(iId == null || iId.isEmpty())
			iId = "'%'";
		if(iName == null || iName.isEmpty())
			iName = "%";
		if(iUserId == null || iUserId.isEmpty())
			iUserId = "%";
		if(iPos == null || iPos.isEmpty())
			iPos = "%";
		if(iDep == null || iDep.isEmpty())
			iDep = "%";
		if(iEmail == null || iEmail.isEmpty())
			iEmail = "%";
		if(iTel == null || iTel.isEmpty())
			iTel = "%";
		if(iRemark == null || iRemark.isEmpty())
			iRemark = "%";
		String sql = "select id, name, userid from tb_userinfo where "
				+ "id like " + iId + " and name like '" + iName
				+ "' and userId like '" + iUserId + "' and pos like '"
				+ iPos + "' and dep like '" + iDep + "' and email like '"
				+ iEmail + "' and tel like '" + iTel + "' and remark like '" 
				+ iRemark + "' and isadmin like " + iIsAdmin;
		List<List<String>> list = findForList(sql);
		return list;
	}

	/**
	 * 	在事务中更新人员信息
	 * @param userInfo 欲更新的人员信息公共类对象
	 * @return 数据更新成功与否
	 */
	public static boolean updateTbUserInfo(TbUserInfo userInfo) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			boolean res = false;
			if(userInfo.getId() != null)
				res = exeUpdate("update tb_userinfo set name='"
						+ userInfo.getName() + "', userid='" 
						+ userInfo.getUserId() + "', pos='" 
						+ userInfo.getPos() + "', dep='" + userInfo.getDep()
						+ "', email='" + userInfo.getEmail() + "', tel='"
						+ userInfo.getTel() + "', remark='" 
						+ userInfo.getRemark() + "', isadmin=" 
						+ userInfo.isAdmin() + " where id=" + userInfo.getId());
			if(res)
				conn.commit();
			else {
				conn.rollback();
				return false;
			}
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
}
