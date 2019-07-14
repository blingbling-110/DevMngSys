package com.qzj.innerFrame;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.qzj.Item;
import com.qzj.sqlOpr.SqlOpr;
import com.qzj.sqlOpr.model.TbBrw;

/**
 * 	借用管理内部窗体
 * @author blingbling_inwin
 *
 */
public class BrwIFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 	表格滚动面板
	 */
	private JScrollPane tablePane = null;

	/**
	 * 	表格
	 */
	private JTable table = null;
	
	/**
	 * 	增加借用单面板
	 */
	private JPanel addPane = null;
	
	/**
	 * 	借用设备编号标签
	 */
	private JLabel devIdLabel = new JLabel("借用设备编号：");
	
	/**
	 * 	借用设备编号文本框
	 */
	private JTextField devIdField = new JTextField(30);
	
	/**
	 * 	借用人工号标签
	 */
	private JLabel brwerIdLabel = new JLabel("借用人工号：");
	
	/**
	 * 	借用人工号文本框
	 */
	private JTextField brwerIdField = new JTextField(30);
	
	/**
	 * 	备注标签
	 */
	private JLabel remarkLabel = new JLabel("备注：");
	
	/**
	 * 	备注文本框
	 */
	private JTextField remarkField = new JTextField(80);
	
	/**
	 * 	增加借用单按钮
	 */
	private JButton addButton = null;
	
	/**
	 * 	刷新按钮
	 */
	private JButton refreshButton = null;

	public BrwIFrame() {
		setTitle("借用管理");
		setSize(1032, 432);
		setMaximizable(true);//	窗体可最大化
		setResizable(true);//	窗体可拉伸
		setIconifiable(true);//	窗体可图标化
		setClosable(true);//	窗体可关闭
		getTablePane();
		tablePane.setViewportView(getTable());
		getContentPane().add(tablePane, BorderLayout.CENTER);
		getAddPane();
		getContentPane().add(addPane, BorderLayout.SOUTH);
	}
	
	/**
	 * @return tablePane
	 */
	public JScrollPane getTablePane() {
		if(tablePane == null)
			tablePane = new JScrollPane();
		return tablePane;
	}
	
	/**
	 * @return table
	 */
	public JTable getTable() {
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//	关闭列的自动调节
		String[] columnLabel = {"借用单编号", "借用设备编号", "借用人工号", "借用日期", "备注"};
		//	DefaultTableModel是用于保存表格单元数值的表格模型类
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setColumnIdentifiers(columnLabel);//	设置表格模型的列名称
		JTextField readOnlyField = new JTextField();//	创建只读文本框供表格单元使用
		readOnlyField.setEditable(false);//	将文本框设为不可编辑
		for(int i = 0; i < columnLabel.length; i++) {
			//	获取表格列属性对象
			TableColumn columnattr = table.getColumnModel().getColumn(i);
			//	将表格各列的单元编辑器均设为只读编辑器
			columnattr.setCellEditor(new DefaultCellEditor(readOnlyField));
			if(i > 2)
				columnattr.setPreferredWidth(300);//	调整表格列宽
			else
				columnattr.setPreferredWidth(150);//	调整表格列宽
		}
		//	初始化表格内容
		List<List<String>> allBrw = SqlOpr.getAllBrw();//	获取所有借用单信息
		for(int i = 0; i < allBrw.size(); i++) {
			List<String> brwList = allBrw.get(i);//	每个设备信息的List集合
			Item item = new Item(brwList.get(0), null, null);
			TbBrw brw = SqlOpr.getBrw(item);//	获取指定设备信息
			String[] row = new String[columnLabel.length];//	表格模型的一行
			//	判断设备信息对象是否为空
			if(brw.getId() != null && !brw.getId().isEmpty()) {
				row[0] = brw.getId();
				row[1] = brw.getDevId();
				row[2] = brw.getBrwerId().toString();
				row[3] = brw.getDate();
				row[4] = brw.getRemark();
				tableModel.addRow(row);//	在表格模型末尾增加一行
			}
		}
		return table;
	}
	
	/**
	 * @return addPane
	 */
	public JPanel getAddPane() {
		if(addPane == null) {
			addPane = new JPanel();
			addPane.setLayout(new GridBagLayout());
			addComponent(addPane, devIdLabel, 0, 0, 1);
			addComponent(addPane, devIdField, 1, 0, 1);
			addComponent(addPane, brwerIdLabel, 2, 0, 1);
			addComponent(addPane, brwerIdField, 3, 0, 1);
			addComponent(addPane, remarkLabel, 0, 1, 1);
			addComponent(addPane, remarkField, 1, 1, 3);
			addComponent(addPane, getAddButton(), 0, 2, 2);
			addComponent(addPane, getRefreshButton(), 2, 2, 2);
		}
		return addPane;
	}
	
	/**
	 * @return addButton
	 */
	public JButton getAddButton() {
		if(addButton == null) {
			addButton = new JButton("增加借用单(A)", new ImageIcon(
					getClass().getResource("/res/icon/add.png")));
			addButton.setMnemonic(KeyEvent.VK_A);
			addButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String devId = devIdField.getText().trim();
					boolean isDevExist = false;//	判断设备编号是否存在
					Integer jobNum = null;
					String brwerId = brwerIdField.getText().trim();
					if(devId == null || devId.isEmpty()) {
						JOptionPane.showMessageDialog(
								BrwIFrame.this, "借用设备编号不能为空", 
								"输入错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
					//	获取所有设备信息
					List<List<String>> allDevInfo = SqlOpr.getAllDevInfo();
					for(int i = 0; i < allDevInfo.size(); i++) {
						//	获取数据库中已存在的设备编号
						String existDevId = allDevInfo.get(i).get(0);
						if(devId.equals(existDevId)) {
							isDevExist = true;
							break;
						}
					}
					if(!isDevExist) {
						JOptionPane.showMessageDialog(
								BrwIFrame.this, "设备编号不存在", 
								"输入错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(brwerId == null || brwerId.isEmpty()) {
						JOptionPane.showMessageDialog(
								BrwIFrame.this, "借用人工号不能为空", 
								"输入错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						jobNum = Integer.parseInt(
								brwerId);
					}catch(NumberFormatException exc) {
						JOptionPane.showMessageDialog(BrwIFrame.this, 
								"工号只能为整数，且不能超过" + Integer.MAX_VALUE,
								"输入错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
					//	获取所有人员信息
					List<List<String>> allUserInfo = SqlOpr.getAllUserInfo();
					boolean isUserExist = false;//	判断人员是否存在
					for(int i = 0; i < allUserInfo.size(); i++) {
						//	获取数据库中已存在的人员工号
						Integer existJobNum = Integer.parseInt(
								allUserInfo.get(i).get(0));
						if(jobNum.equals(existJobNum)) {
							isUserExist = true;
							break;
						}
					}
					if(!isUserExist) {
						JOptionPane.showMessageDialog(
								BrwIFrame.this, "借用人工号不存在", 
								"输入错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						String status = SqlOpr.getStatusFromDevId(devId);
						if(!status.equals("库存中")) {
							JOptionPane.showMessageDialog(
									BrwIFrame.this, "抱歉，设备已借出", 
									"无法借用", JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (SQLException exc) {
						exc.printStackTrace();
					}
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					String brwId = "brwID_" + sdf.format(date);
					String dateStr = String.format("%tc", date);
					//	获取所有借用单
					List<List<String>> allBrw = SqlOpr.getAllBrw();
					int idCount = 0;//	统计当日已有借用单数量
					for(int i = 0; i < allBrw.size(); i++) {
						String id = allBrw.get(i).get(0);//	获取现有借用单编号
						if(id.indexOf(brwId) == 0)
							idCount++;
					}
					//	确定借用单编号
					idCount++;
					brwId = brwId + String.format("%03d", idCount);
					TbBrw brw = new TbBrw();//	封装待增加借用单的对象
					brw.setId(brwId.trim());
					brw.setDevId(devId);
					brw.setBrwerId(jobNum);
					brw.setDate(dateStr);
					brw.setRemark(remarkField.getText().trim());
					boolean res = SqlOpr.insertTbBrw(brw);
					if(res) {
						refreshButton.doClick();
						devIdField.setText("");
						brwerIdField.setText("");
						remarkField.setText("");
					}else
						JOptionPane.showMessageDialog(
								BrwIFrame.this, "借用设备失败", 
								"操作失败", JOptionPane.ERROR_MESSAGE);
				}
			});
		}
		return addButton;
	}

	/**
	 * @return refreshButton
	 */
	public JButton getRefreshButton() {
		if(refreshButton == null) {
			refreshButton = new JButton("刷新(R)", new ImageIcon(
					getClass().getResource("/res/icon/refresh.png")));
			refreshButton.setMnemonic(KeyEvent.VK_R);
			refreshButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					tablePane.setViewportView(getTable());
					validate();
				}
			});
		}
		return refreshButton;
	}

	/**
	 * 	按网格组布局管理器增加组件
	 * @param pane 待增加组件的面板
	 * @param com 待增加的组件
	 * @param x gridx
	 * @param y gridy
	 * @param width gridwidth
	 */
	private void addComponent(JPanel pane, JComponent com, 
			int x, int y, int width) {
		GridBagConstraints gbc = new GridBagConstraints();//	创建网格限制对象
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.insets = new Insets(2, 8, 2, 8);
		pane.add(com, gbc);
	}
}
