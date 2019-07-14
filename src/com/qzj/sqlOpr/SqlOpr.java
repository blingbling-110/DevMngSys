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
 * 	���ݿ���������ࣺ��װ��ϵͳ������������ݿ����
 * @author qinzijun
 *
 */
public class SqlOpr {
	/**
	 * 	MySQL 8.0 ���ϰ汾���ݿ�����
	 */
	protected static String DB_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	
	/**
	 * 	���ݿ�����
	 */
	protected static String DB_NAME = "db_devmngsys";
	
	/**
	 * 	���ݿ�·��
	 */
	protected static String DB_URL = "jdbc:mysql://localhost:3306/"
			+ DB_NAME + "?useSSL=false"//	��ʽ�ر�SSL����
			+ "&serverTimezone=Asia/Shanghai"//	����ʱ��Asia/Shanghai
			+ "&allowPublicKeyRetrieval=true";//	����ͻ��˴ӷ�����ȡ�ع�Կ
	
	/**
	 * 	���ݿ��û���
	 */
	protected static String DB_USER_ID = "root";
	
	/**
	 * 	���ݿ�����
	 */
	protected static String DB_PWD = "123456";
	
	/**
	 * 	ִ��SQL����ʱ��
	 */
	protected static String exeTime;
	
	/**
	 * 	���ݿ����Ӷ���
	 */
	public static Connection conn;
	
	/**
	 * 	��̬��ʼ�����ݿ����������
	 */
	static {
		if(conn == null) {
			try {
				Class.forName(DB_CLASS_NAME);
				conn = DriverManager.getConnection(DB_URL, DB_USER_ID, DB_PWD);
			} catch (ClassNotFoundException e) {
				//	������ʾ��
				JOptionPane.showMessageDialog(null, 
						"�뽫JDBC������������lib�ļ�����", 
						"���ݿ����", JOptionPane.ERROR_MESSAGE);
				System.exit(1);//	�������˳� 
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "���ݿ����Ӷ��󴴽�ʧ��", 
						"���ݿ����", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
		}
	}
	
	/**
	 * 	��չ��췽������ֹ�������ݿ�����������ʵ������
	 */
	private SqlOpr() {
	}
	
	/**
	 * 	��ȡ�豸��Ϣ
	 * @param item ����ȡ���豸
	 * @return ���豸���豸��Ϣ���������
	 */
	public static TbDevInfo getDevInfo(Item item) {
		String where = "name='" + item.getName() + "'";//	��ȡitem�����name����
		if(item.getId() != null)
			where = "id='" + item.getId() + "'";//	��ȡitem�����id����
		TbDevInfo info = new TbDevInfo();//	�����豸��Ϣ����ģ��
		ResultSet res = findForRes("select * from tb_devinfo where " + where);
		//	��װ���ݵ�����ģ����
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
	 * 	��ȡ�û���Ϣ
	 * @param item ����ȡ���û�
	 * @return ���û�����Ա��Ϣ���������
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
	 * 	��ȡ���õ�
	 * @param item ����ȡ�Ľ��õ�
	 * @return �ý��õ��Ĺ��������
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
	 * 	��ȡ�黹��
	 * @param item ����ȡ�Ĺ黹��
	 * @return �ù黹���Ĺ��������
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
	 * 	��ȡSQL���Ľ����
	 * @param sql ��ִ�е�SQL���
	 * @return ���ݿⷵ�صĽ����
	 */
	private static ResultSet findForRes(String sql) {
		if(conn == null)
			return null;
		long time = System.currentTimeMillis();//	��ȡ��ǰ����ֵ
		ResultSet res = null;
		try {
			/*
			 * 	����ָ����������ͺͲ����Ե�������������
			 * 
			 * 	ResultSet.TYPE_SCROLL_INSENSITIVE��
			 * 	֧�ֽ����backforward ��random ��last ��first �Ȳ�����
			 * 		�������Ự �����ݿ������������ĸ����ǲ����еġ�
			 * 	ʵ�ַ����������ݿ�ȡ�����ݺ󣬻��ȫ�����ݻ��浽cache �У�
			 * 		�Խ�����ĺ����������ǲ�����cache �е����ݣ�
			 * 		���ݿ��м�¼�����仯�󣬲�Ӱ��cache �е����ݣ�
			 * 		����ResultSet �Խ�����е������ǲ����еġ�
			 * 
			 * 	ResultSet.CONCUR_READ_ONLY��
			 * 	��ResultSet�е����ݼ�¼��ֻ���ģ������Ը���
			 */
			Statement sta = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			res = sta.executeQuery(sql);
			//	����ִ��SQL�������ʱ��
			exeTime = ((System.currentTimeMillis() - time) / 1000d) + "";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * 	��ȡ�����豸��Ϣ
	 * @return ���������豸��Ϣ��List����
	 */
	public static List<List<String>> getAllDevInfo() {
		List<List<String>> list = findForList(
				"select id, name from tb_devinfo");
		return list;
	}
	
	/**
	 * 	��ȡ�����û���Ϣ
	 * @return ���������û���Ϣ��List����
	 */
	public static List<List<String>> getAllUserInfo() {
		List<List<String>> list = findForList(
				"select id, name, userid from tb_userinfo");
		return list;
	}
	
	/**
	 * 	��ȡ���н��õ�
	 * @return �������н��õ���List����
	 */
	public static List<List<String>> getAllBrw() {
		List<List<String>> list = findForList(
				"select id from tb_brw order by id desc");//	����
		return list;
	}
	
	/**
	 * 	��ȡ���й黹��
	 * @return �������й黹����List����
	 */
	public static List<List<String>> getAllRtn() {
		List<List<String>> list = findForList(
				"select id from tb_rtn order by id desc");//	����
		return list;
	}

	/**
	 * 	��ȡSQL����������ɵ������б�
	 * @param sql ��ִ�е�SQL���
	 * @return �������н����Ϣ��List����
	 */
	private static List<List<String>> findForList(String sql) {
		List<List<String>> list = new ArrayList<>();
		ResultSet res = findForRes(sql);
		try {
			/*
			 * 	�����������Ԫ����
			 * 
			 * 	ResultSetMetaData�����ڻ�ȡ��Ӧ��������е����ͺ�������Ϣ����
			 */
			ResultSetMetaData metaData = res.getMetaData();
			int colCount = metaData.getColumnCount();//	��ȡ������е�����
			while(res.next()) {
				ArrayList<String> row = new ArrayList<String>();
				for(int i = 1; i <= colCount; i++) {//	���л�ȡ�������Ϣ
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
	 * 	��֤�û���¼
	 * @param userid �û���
	 * @param pwd ����
	 * @return ��¼�ɹ����
	 * @throws SQLException
	 */
	public static boolean checkLogin(String userid, String pwd) 
			throws SQLException {
		if(conn == null)
			JOptionPane.showMessageDialog(null, "��δ�������ݿ����Ӷ���", 
					"���ݿ����", JOptionPane.ERROR_MESSAGE);
		PreparedStatement preSta = conn.prepareStatement(//	ͨ��Ԥ�������SQLע��
				"select * from tb_userinfo where userid=? and pwd=?");
		preSta.setString(1, userid);
		preSta.setString(2, pwd);
		ResultSet res = preSta.executeQuery();
		return res.next();
	}
	
	/**
	 * 	�����������ӽ�����Ϣ���������ݵ������ԣ�
	 * <p>
	 * 	JDBC��������Ĳ��裺
	 * 	1��ȡ��JDBC���Զ��ύ��
	 * 	2��ִ�и���SQL��䣻
	 * 	3������������ִ�гɹ������ύ������������˴�����ع���
	 * @param brw ���������ݿ�Ľ��ù��������
	 * @return ���ݲ���ɹ����
	 */
	public static boolean insertTbBrw(TbBrw brw) {
		try {
			boolean autoCommit = conn.getAutoCommit();//	��ȡ�ύ��ʽ
			conn.setAutoCommit(false);//	ȡ���Զ��ύ
			//	���ӽ��ñ��¼
			exeUpdate("insert into tb_brw values('"+ brw.getId()
			+ "','" + brw.getDevId() + "'," + brw.getBrwerId()
			+ ",'" + brw.getDate() + "','" + brw.getRemark() + "')");
			//	�����豸��Ϣ
			Item item = new Item();
			item.setId(brw.getDevId());//	��ȡ�����豸���
			TbDevInfo devInfo = getDevInfo(item);
			boolean res = false;
			if(devInfo.getId() != null && !devInfo.getId().isEmpty())
				res = exeUpdate("update tb_devinfo set status='" 
						+ "���ţ�" + brw.getBrwerId() 
						+ "',remark='" + brw.getRemark() 
						+ "' where id='" + devInfo.getId() + "'");
			if(res)
				conn.commit();//	�ύ����
			else {
				conn.rollback();//	�ع�
				return false;
			}
			conn.setAutoCommit(autoCommit);//	��ԭ�ύ��ʽ
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * 	�����������ӹ黹��Ϣ
	 * @param rtn ���������ݿ�Ĺ黹���������
	 * @return ���ݲ���ɹ����
	 */
	public static boolean insertTbRtn(TbRtn rtn) {
		try {
			boolean autoCommit = conn.getAutoCommit();//	��ȡ�ύ��ʽ
			conn.setAutoCommit(false);//	ȡ���Զ��ύ
			//	���ӹ黹���¼
			exeUpdate("insert into tb_rtn values('"+ rtn.getId()
			+ "','" + rtn.getDevId() + "'," + rtn.getRtnerId()
			+ ",'" + rtn.getDate() + "','" + rtn.getRemark() + "')");
			//	�����豸��Ϣ
			Item item = new Item();
			item.setId(rtn.getDevId());//	��ȡ�黹�豸���
			TbDevInfo devInfo = getDevInfo(item);
			boolean res = false;
			if(devInfo.getId() != null && !devInfo.getId().isEmpty())
				res = exeUpdate("update tb_devinfo set status='" 
						+ "�����" + "',remark='" + rtn.getRemark() 
						+ "' where id='" + devInfo.getId() + "'");
			if(res)
				conn.commit();//	�ύ����
			else {
				conn.rollback();//	�ع�
				return false;
			}
			conn.setAutoCommit(autoCommit);//	��ԭ�ύ��ʽ
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	/**
	 * 	ִ��SQL����������ɾ���Ĳ���
	 * @param sql ��ִ�е�SQL���
	 * @return SQL�����ĳɹ����
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
	 * 	���ݿⱸ��
	 * @return �����ļ�·��
	 * @throws SQLException
	 */
	public static String backup() throws SQLException {
		LinkedList<String> sqls = new LinkedList<>();//	�����ļ��е�����SQL���
		//	�豸�ݵ����ݱ�����
		String tables[] = {"tb_brw", "tb_devinfo", "tb_rtn", "tb_userinfo"};
		ArrayList<Tables> tableList = new ArrayList<>();//	���ݱ����ļ���
		
		//	��ȡ�����ݱ����
		for(int i = 0; i < tables.length; i++) {//	�������ݱ���������
			Statement sta = conn.createStatement();
			//	desc <table name>�ȼ���show columns from <table name>
			ResultSet res = sta.executeQuery("desc " + tables[i]);//	��ѯ���ݱ�ṹ
			ArrayList<Columns> columns = new ArrayList<>();//	��ģ�Ͷ���ļ���
			while(res.next()) {
				Columns column = new Columns();
				column.setName(res.getString("Field"));//	��ȡ������
				column.setType(res.getString("Type"));//	��ȡ������
				if(res.getString("Null").equals("YES"))//	��ȡ���Ƿ��Ϊ��
					column.setNull(true);
				if(res.getString("Key").equals("PRI")) {//	��ȡ���Ƿ�Ϊ����
					column.setKey(true);
					//	��ȡ���Ƿ�����
					if(res.getString("Extra").equals("auto_increment"))
						column.setIncrement(true);
				}
				columns.add(column);
			}
			//	�����ݱ����ƺ���Ӧ��ģ�Ͷ���ʵ�������ݱ�ģ��
			Tables table = new Tables(tables[i], columns);
			tableList.add(table);
			res.close();//	�رս����
			sta.close();//	�ر���������
		}
		
		//	����ɾ�����������ݱ�Ͳ������ݵ�SQL���
		for(int i = 0; i < tableList.size(); i++) {//	�������ݱ����ļ���
			Tables table = tableList.get(i);//	��ȡ���ݱ����
			ArrayList<Columns> columns = table.getColumns();//	��ȡ�����ݱ����ģ��
			/*
			 * 	drop table <table name>��
			 * 	ɾ�����ݱ�ȫ�����ݺͱ�ṹ�������ͷŴ��̿ռ䣬������ Innodb �� MyISAM
			 */
			sqls.add("drop table if exists " + table.getName() + ";");
			
			//	�������ݱ��SQL���
			StringBuilder createSql = new StringBuilder();
			createSql.append("create table `" + table.getName() + "`(");
			for(int j = 0; j < columns.size(); j++) {//	���������ݱ����ģ��
				Columns column = columns.get(j);
				createSql.append("`" + column.getName() //	��ȡ������
				+ "` " + column.getType());//	��ȡ������
				if(!column.isNull())//	��ȡ���Ƿ��Ϊ��
					createSql.append(" not null");
				if(column.isKey()) {//	��ȡ���Ƿ�Ϊ����
					createSql.append(" primary key");
					if(column.isIncrement())//	��ȡ���Ƿ�����
						createSql.append(" auto_increment");
				}
				
				if(j < columns.size() - 1)//	�ж��Ƿ�Ϊ���һ��
					createSql.append(",");
				else
					createSql.append(") engine=innodb default charset=utf8;");
			}
			sqls.add(createSql.toString());
			
			//	�������ݵ�SQL���
			Statement sta = conn.createStatement();
			ResultSet res = sta.executeQuery("select * from " + table.getName());
			while(res.next()) {
				StringBuilder insertSql = new StringBuilder();
				insertSql.append("insert into " + table.getName() + " values(");
				for(int j = 0; j < columns.size(); j++) {//	���������ݱ����ģ��
					Columns column = columns.get(j);
					String type = column.getType();//	��ȡ������
					//	��������Ϊ�ַ��ͣ����������Ű�Χ
					if(type.startsWith("varchar") ||
							type.startsWith("char") ||
							type.startsWith("datetime"))
						insertSql.append("'"
							+ res.getString(column.getName()) + "'");
					else
						insertSql.append(res.getString(column.getName()));
					
					if(j < columns.size() - 1)//	�ж��Ƿ�Ϊ���һ��
						insertSql.append(",");
					else
						insertSql.append(");");
				}
				sqls.add(insertSql.toString());
			}
			res.close();
			sta.close();
		}
		
		//	��������ļ�
		Date date = new Date();//	��ȡ��ǰ����ֵ
		/*
		 * 	���õ�ǰʱ��������ʽ
		 * 
		 * 	SimpleDateFormat��
		 * 		��һ���Թ������еķ�ʽ��ʽ���ͷ������ݵľ����ࡣ
		 * 		�������ʽ�� (date -> text)���﷨���� (text -> date)�ͱ�׼����
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		//	�����ļ�·��
		String backupFilePath = "backup\\" + sdf.format(date) + ".sql";
		File sqlFile = new File(backupFilePath);//	����SQL�ļ�����
		FileOutputStream fos = null;//	�����ļ��ֽ������
		OutputStreamWriter osw = null;//	�����ַ������
		BufferedWriter bw = null;//	���������ַ������
		try {
			fos = new FileOutputStream(sqlFile);
			osw = new OutputStreamWriter(fos);
			bw = new BufferedWriter(osw);
			for(String sql: sqls) {//	���������ļ��е�����SQL���
				bw.write(sql);
				bw.newLine();
				bw.flush();//	ˢ�»����ַ���
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//	�������ιر������
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
		return sqlFile.getAbsolutePath();//	���ر����ļ��ľ���·��
	}
	
	/**
	 * 	���ݿ�ָ�
	 * @param backupFilePath ���ָ��ı����ļ�·��
	 */
	public static void restore(String backupFilePath) {
		File sqlFile = new File(backupFilePath);//	����SQL�ļ�����
		FileInputStream fis = null;//	�����ļ��ֽ�������
		InputStreamReader isr = null;//	�����ַ�������
		BufferedReader br = null;//	���������ַ�������
		Statement sta = null;//	������������
		try {
			fis = new FileInputStream(sqlFile);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String oneLine = null;
			while((oneLine = br.readLine()) != null) {
				if(!oneLine.trim().equals("")) {
					sta = conn.createStatement();
					sta.executeUpdate(oneLine);//	ִ��SQL���
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
			//	�������ιر������
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
	 * 	���û�����ȡ�û�����
	 * @param userId �û���
	 * @return �û�����
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
	 * 	�������������豸��Ϣ
	 * @param devInfo ���������ݿ���豸��Ϣ���������
	 * @return ���ݲ���ɹ����
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
	 * 	��������ɾ���豸��Ϣ
	 * @param id ��ɾ���豸�ı��
	 * @return ����ɾ���ɹ����
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
	 * 	��������������Ա��Ϣ
	 * @param userInfo ���������ݿ����Ա��Ϣ���������
	 * @return ���ݲ���ɹ����
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
	 * 	��������ɾ����Ա��Ϣ
	 * @param id ��ɾ����Ա�Ĺ���
	 * @return ����ɾ���ɹ����
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
	 * 	���û�����ȡ�û��Ƿ�ӵ�й���Ȩ��
	 * @param userId �û���
	 * @return �û��Ƿ�ӵ�й���Ȩ��
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
	 * 	���豸��Ż�ȡ�豸״̬
	 * @param devId �豸���
	 * @return �豸״̬
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
	 * 	�޸�����
	 * @param userId �û���
	 * @param oriPwd ԭ����
	 * @param newPwd ������
	 * @return �����޸ĳɹ����
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
	 * 	�����豸��Ϣ
	 * @param iId ���������豸���
	 * @param iName ���������豸����
	 * @param iStatus ���������豸״̬
	 * @param iDes ���������豸����
	 * @param iRemark �������ı�ע
	 * @return �������ص��豸��Ϣ��List����
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
	 * 	�������и����豸��Ϣ
	 * @param devInfo �����µ��豸��Ϣ���������
	 * @return ���ݸ��³ɹ����
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
	 * 	������Ա��Ϣ
	 * @param iId �������Ĺ���
	 * @param iName ������������
	 * @param iUserId ���������û���
	 * @param iPos ��������ְλ
	 * @param iDep �������Ĳ���
	 * @param iEmail ������������
	 * @param iTel �������ĵ绰
	 * @param iRemark �������ı�ע
	 * @return �������ص���Ա��Ϣ��List����
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
	 * 	�������и�����Ա��Ϣ
	 * @param userInfo �����µ���Ա��Ϣ���������
	 * @return ���ݸ��³ɹ����
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
