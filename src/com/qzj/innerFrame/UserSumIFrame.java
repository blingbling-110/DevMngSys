package com.qzj.innerFrame;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.qzj.Item;
import com.qzj.sqlOpr.SqlOpr;
import com.qzj.sqlOpr.model.TbUserInfo;

/**
 * 	人员总览内部窗体
 * @author qinzijun
 *
 */
public class UserSumIFrame extends JInternalFrame {

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
	 * 	增加用户面板
	 */
	private JPanel addPane = null;
	
	/**
	 * 	工号标签
	 */
	private JLabel idLabel = new JLabel("工号：");
	
	/**
	 * 	工号文本框
	 */
	private JTextField idField = new JTextField(20);
	
	/**
	 * 	姓名标签
	 */
	private JLabel nameLabel = new JLabel("姓名：");
	
	/**
	 * 	姓名文本框
	 */
	private JTextField nameField = new JTextField(20);
	
	/**
	 * 	用户名标签
	 */
	private JLabel userIdLabel = new JLabel("用户名：");
	
	/**
	 * 	用户名文本框
	 */
	private JTextField userIdField = new JTextField(20);
	
	/**
	 * 	密码标签
	 */
	private JLabel pwdLabel = new JLabel("密码：");
	
	/**
	 * 	密码框
	 */
	private JPasswordField pwdField = new JPasswordField(20);
	
	/**
	 * 	职务标签
	 */
	private JLabel posLabel = new JLabel("职务：");
	
	/**
	 * 	职务文本框
	 */
	private JTextField posField = new JTextField(53);
	
	/**
	 * 	部门标签
	 */
	private JLabel depLabel = new JLabel("部门：");
	
	/**
	 * 	部门文本框
	 */
	private JTextField depField = new JTextField(53);
	
	/**
	 * 	邮箱标签
	 */
	private JLabel emailLabel = new JLabel("邮箱：");
	
	/**
	 * 	邮箱文本框
	 */
	private JTextField emailField = new JTextField(53);
	
	/**
	 * 	电话标签
	 */
	private JLabel telLabel = new JLabel("电话：");
	
	/**
	 * 	电话文本框
	 */
	private JTextField telField = new JTextField(53);
	
	/**
	 * 	备注标签
	 */
	private JLabel remarkLabel = new JLabel("备注：");
	
	/**
	 * 	备注文本框
	 */
	private JTextField remarkField = new JTextField(120);
	
	/**
	 * 	是否拥有管理权限选择框
	 */
	private JCheckBox isAdminBox = new JCheckBox("是否拥有管理权限");
	
	/**
	 * 	增加设备按钮
	 */
	private JButton addButton = null;
	
	/**
	 * 	删除设备按钮
	 */
	private JButton deleteButton = null;
	
	/**
	 * 	刷新按钮
	 */
	private JButton refreshButton = null;
	
	public UserSumIFrame() {
		setTitle("人员总览");
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

	public JScrollPane getTablePane() {
		if(tablePane == null)
			tablePane = new JScrollPane();
		return tablePane;
	}

	public JTable getTable() {
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//	关闭列的自动调节
		String[] columnLabel = {"工号", "姓名", "用户名", "是否拥有管理权限",
				"职务", "部门", "电子邮箱", "电话", "备注"};
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
			if(i > 5)
				columnattr.setPreferredWidth(168);//	调整表格列宽
			else if(i < 3)
				columnattr.setPreferredWidth(100);
			else
				columnattr.setPreferredWidth(110);
		}
		//		初始化表格内容
		List<List<String>> allUserInfo = SqlOpr.getAllUserInfo();//	获取所有人员信息
		for(int i = 0; i < allUserInfo.size(); i++) {
			List<String> infoList = allUserInfo.get(i);//	每个人员信息的List集合
			Item item = new Item(infoList.get(2), infoList.get(1),
					Integer.parseInt(infoList.get(0)));
			TbUserInfo userInfo = SqlOpr.getUserInfo(item);//	获取指定人员信息
			String[] row = new String[columnLabel.length];//	表格模型的一行
			//	判断人员信息对象是否为空
			if(userInfo.getId() != null) {
				row[0] = userInfo.getId().toString();
				row[1] = userInfo.getName();
				row[2] = userInfo.getUserId();
				row[3] = Boolean.toString(userInfo.isAdmin());
				row[4] = userInfo.getPos();
				row[5] = userInfo.getDep();
				row[6] = userInfo.getEmail();
				row[7] = userInfo.getTel();
				row[8] = userInfo.getRemark();
				tableModel.addRow(row);//	在表格模型末尾增加一行
			}
		}
		return table;
	}

	public JPanel getAddPane() {
		if(addPane == null) {
			addPane = new JPanel();
			addPane.setLayout(new GridBagLayout());
			addComponent(addPane, idLabel, 0, 0, 1);
			addComponent(addPane, idField, 1, 0, 1);
			addComponent(addPane, nameLabel, 2, 0, 1);
			addComponent(addPane, nameField, 3, 0, 1);
			addComponent(addPane, userIdLabel, 4, 0, 1);
			addComponent(addPane, userIdField, 5, 0, 1);
			addComponent(addPane, pwdLabel, 6, 0, 1);
			addComponent(addPane, pwdField, 7, 0, 1);
			addComponent(addPane, posLabel, 0, 1, 1);
			addComponent(addPane, posField, 1, 1, 3);
			addComponent(addPane, depLabel, 4, 1, 1);
			addComponent(addPane, depField, 5, 1, 3);
			addComponent(addPane, emailLabel, 0, 2, 1);
			addComponent(addPane, emailField, 1, 2, 3);
			addComponent(addPane, telLabel, 4, 2, 1);
			addComponent(addPane, telField, 5, 2, 3);
			addComponent(addPane, remarkLabel, 0, 3, 1);
			addComponent(addPane, remarkField, 1, 3, 7);
			addComponent(addPane, isAdminBox, 0, 5, 2);
			addComponent(addPane, getAddButton(), 2, 5, 2);
			addComponent(addPane, getDeleteButton(), 4, 5, 2);
			addComponent(addPane, getRefreshButton(), 6, 5, 2);
		}
		return addPane;
	}
	
	public JButton getAddButton() {
		if(addButton == null) {
			addButton = new JButton("增加人员(A)", new ImageIcon(
					getClass().getResource("/res/icon/add.png")));
			addButton.setMnemonic(KeyEvent.VK_A);
			addButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String id = idField.getText().trim();
					String name = nameField.getText().trim();
					String userId = userIdField.getText().trim();
					String email = emailField.getText().trim();
					String tel = telField.getText().trim();
					if(id == null || id.isEmpty()) {
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "工号不能为空", 
								"输入错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(name == null || name.isEmpty()) {
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "姓名不能为空", 
								"输入错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(userId == null || userId.isEmpty()) {
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "用户名不能为空", 
								"输入错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
					String pwd = new String(pwdField.getPassword());
					if(pwd == null || pwd.isEmpty()) {
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "密码不能为空", 
								"输入错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(email == null || email.isEmpty()) {
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "邮箱不能为空", 
								"输入错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(tel == null || tel.isEmpty()) {
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "电话不能为空", 
								"输入错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
					//	获取所有人员信息
					List<List<String>> allUserInfo = SqlOpr.getAllUserInfo();
					for(int i = 0; i < allUserInfo.size(); i++) {
						String eachId = allUserInfo.get(i).get(0);
						String eachUserId = allUserInfo.get(i).get(2);
						if(id.equals(eachId)) {
							JOptionPane.showMessageDialog(
									UserSumIFrame.this, "工号已存在", 
									"输入错误", JOptionPane.ERROR_MESSAGE);
							return;
						}
						if(userId.equals(eachUserId)) {
							JOptionPane.showMessageDialog(
									UserSumIFrame.this, "用户名已存在", 
									"输入错误", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					//	封装待增加人员信息的对象
					TbUserInfo userInfo = new TbUserInfo();
					try {
						userInfo.setId(Integer.parseInt(
								id));
					}catch(NumberFormatException exc) {
						JOptionPane.showMessageDialog(UserSumIFrame.this, 
								"工号只能为整数，且不能超过" + Integer.MAX_VALUE,
								"输入错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
					userInfo.setName(name);
					userInfo.setUserId(userId);
					userInfo.setPwd(pwd);
					userInfo.setPos(posField.getText().trim());
					userInfo.setDep(depField.getText().trim());
					userInfo.setEmail(email);
					userInfo.setTel(tel);
					userInfo.setRemark(remarkField.getText().trim());
					userInfo.setAdmin(isAdminBox.isSelected());
					boolean res = SqlOpr.insertTbUserInfo(userInfo);
					if(res) {
						refreshButton.doClick();
						idField.setText("");
						nameField.setText("");
						userIdField.setText("");
						pwdField.setText("");
						posField.setText("");
						depField.setText("");
						emailField.setText("");
						telField.setText("");
						remarkField.setText("");
						isAdminBox.setSelected(false);
					}else
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "增加人员失败", 
								"操作失败", JOptionPane.ERROR_MESSAGE);
				}
			});
		}
		return addButton;
	}

	/**
	 * @return deleteButton
	 */
	public JButton getDeleteButton() {
		if(deleteButton == null) {
			deleteButton = new JButton("删除所选人员(D)", new ImageIcon(
					getClass().getResource("/res/icon/del.png")));
			deleteButton.setMnemonic(KeyEvent.VK_D);
			deleteButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int[] rows = table.getSelectedRows();
					if(rows.length == 0) {
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "未选择人员", 
								"操作失败", JOptionPane.ERROR_MESSAGE);
						return;
					}
					for(int i = 0; i < rows.length; i++) {
						String id = table.getValueAt(rows[i], 0).toString();
						if(id.equals("0")) {
							JOptionPane.showMessageDialog(UserSumIFrame.this,
									"无法删除超级管理员", "操作失败", 
									JOptionPane.ERROR_MESSAGE);
							continue;
						}
						boolean res = SqlOpr.deleteUserInfo(id);
						if(!res) {
							JOptionPane.showMessageDialog(UserSumIFrame.this,
									"删除失败的人员工号：" + id, "操作失败", 
									JOptionPane.ERROR_MESSAGE);
							continue;
						}
					}
					refreshButton.doClick();
				}
			});
		}
		return deleteButton;
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
