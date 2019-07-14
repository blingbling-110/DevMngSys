package com.qzj.innerFrame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.qzj.Item;
import com.qzj.sqlOpr.SqlOpr;
import com.qzj.sqlOpr.model.TbUserInfo;

/**
 * 	搜索人员内部窗体
 * @author blingbling_inwin
 *
 */
public class UserIFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 	输入面板
	 */
	private JPanel inputPane = null;
	
	/**
	 * 	输入工号标签
	 */
	private JLabel iIdLabel = new JLabel("工号：");
	
	/**
	 * 	输入工号文本框
	 */
	private JTextField iIdField = new JTextField(20);
	
	/**
	 * 	输入姓名标签
	 */
	private JLabel iNameLabel = new JLabel("姓名：");
	
	/**
	 * 	输入姓名文本框
	 */
	private JTextField iNameField = new JTextField(20);
	
	/**
	 * 	输入用户名标签
	 */
	private JLabel iUserIdLabel = new JLabel("用户名：");
	
	/**
	 * 	输入用户名文本框
	 */
	private JTextField iUserIdField = new JTextField(20);
	
	/**
	 * 	输入职务标签
	 */
	private JLabel iPosLabel = new JLabel("职务：");
	
	/**
	 * 	输入职务文本框
	 */
	private JTextField iPosField = new JTextField(53);
	
	/**
	 * 	输入部门标签
	 */
	private JLabel iDepLabel = new JLabel("部门：");
	
	/**
	 * 	输入部门文本框
	 */
	private JTextField iDepField = new JTextField(53);
	
	/**
	 * 	输入邮箱标签
	 */
	private JLabel iEmailLabel = new JLabel("邮箱：");
	
	/**
	 * 	输入邮箱文本框
	 */
	private JTextField iEmailField = new JTextField(53);
	
	/**
	 * 	输入电话标签
	 */
	private JLabel iTelLabel = new JLabel("电话：");
	
	/**
	 * 	输入电话文本框
	 */
	private JTextField iTelField = new JTextField(53);
	
	/**
	 * 	输入备注标签
	 */
	private JLabel iRemarkLabel = new JLabel("备注：");
	
	/**
	 * 	输入备注文本框
	 */
	private JTextField iRemarkField = new JTextField(120);
	
	/**
	 * 	输入是否拥有管理权限选择框
	 */
	private JCheckBox iIsAdminBox = new JCheckBox("是否拥有管理权限");
	
	/**
	 * 	搜索按钮
	 */
	private JButton searchButton = null;
	
	/**
	 * 	表格面板
	 */
	private JScrollPane tablePane = null; 
	
	/**
	 * 	表格
	 */
	private JTable table = null;
	
	/**
	 * 	更新面板
	 */
	private JPanel updatePane = null;
	
	/**
	 * 	更新姓名标签
	 */
	private JLabel uNameLabel = new JLabel("姓名：");
	
	/**
	 * 	更新姓名文本框
	 */
	private JTextField uNameField = new JTextField(20);
	
	/**
	 * 	更新用户名标签
	 */
	private JLabel uUserIdLabel = new JLabel("用户名：");
	
	/**
	 * 	更新用户名文本框
	 */
	private JTextField uUserIdField = new JTextField(20);
	
	/**
	 * 	输入职务标签
	 */
	private JLabel uPosLabel = new JLabel("职务：");
	
	/**
	 * 	输入职务文本框
	 */
	private JTextField uPosField = new JTextField(53);
	
	/**
	 * 	输入部门标签
	 */
	private JLabel uDepLabel = new JLabel("部门：");
	
	/**
	 * 	输入部门文本框
	 */
	private JTextField uDepField = new JTextField(53);
	
	/**
	 * 	输入邮箱标签
	 */
	private JLabel uEmailLabel = new JLabel("邮箱：");
	
	/**
	 * 	输入邮箱文本框
	 */
	private JTextField uEmailField = new JTextField(53);
	
	/**
	 * 	输入电话标签
	 */
	private JLabel uTelLabel = new JLabel("电话：");
	
	/**
	 * 	输入电话文本框
	 */
	private JTextField uTelField = new JTextField(53);
	
	/**
	 * 	更新备注标签
	 */
	private JLabel uRemarkLabel = new JLabel("备注：");
	
	/**
	 * 	更新备注文本框
	 */
	private JTextField uRemarkField = new JTextField(120);
	
	/**
	 * 	更新是否拥有管理权限选择框
	 */
	private JCheckBox uIsAdminBox = new JCheckBox("是否拥有管理权限");
	
	/**
	 * 	更新按钮
	 */
	private JButton updateButton = null;

	/**
	 * 	搜索人员内部窗体构造方法
	 */
	public UserIFrame() {
		setTitle("搜索人员");
		setSize(1032, 432);
		setMaximizable(true);
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		Container c = getContentPane();
		c.add(getInputPane(), BorderLayout.NORTH);
		c.add(getTablePane(), BorderLayout.CENTER);
		c.add(getUpdatePane(), BorderLayout.SOUTH);
	}

	/**
	 * @return inputPane
	 */
	public JPanel getInputPane() {
		if(inputPane == null) {
			inputPane = new JPanel();
			inputPane.setLayout(new GridBagLayout());
			addComponent(inputPane, iIdLabel, 0, 0, 1);
			addComponent(inputPane, iIdField, 1, 0, 1);
			addComponent(inputPane, iNameLabel, 2, 0, 1);
			addComponent(inputPane, iNameField, 3, 0, 1);
			addComponent(inputPane, iUserIdLabel, 4, 0, 1);
			addComponent(inputPane, iUserIdField, 5, 0, 1);
			addComponent(inputPane, iIsAdminBox, 6, 0, 2);
			addComponent(inputPane, iPosLabel, 0, 1, 1);
			addComponent(inputPane, iPosField, 1, 1, 3);
			addComponent(inputPane, iDepLabel, 4, 1, 1);
			addComponent(inputPane, iDepField, 5, 1, 3);
			addComponent(inputPane, iEmailLabel, 0, 2, 1);
			addComponent(inputPane, iEmailField, 1, 2, 3);
			addComponent(inputPane, iTelLabel, 4, 2, 1);
			addComponent(inputPane, iTelField, 5, 2, 3);
			addComponent(inputPane, iRemarkLabel, 0, 3, 1);
			addComponent(inputPane, iRemarkField, 1, 3, 7);
			addComponent(inputPane, getSearchButton(), 0, 4, 8);
		}
		return inputPane;
	}

	/**
	 * @return searchButton
	 */
	public JButton getSearchButton() {
		if(searchButton == null) {
			searchButton = new JButton("搜索(S)", new ImageIcon(
					getClass().getResource("/res/icon/finger.png")));
			searchButton.setMnemonic(KeyEvent.VK_S);
			searchButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					tablePane.setViewportView(getTable());
					validate();
				}
			});
		}
		return searchButton;
	}

	/**
	 * @return tablePane
	 */
	public JScrollPane getTablePane() {
		if(tablePane == null) {
			tablePane = new JScrollPane();
			getTable();
		}
		return tablePane;
	}

	/**
	 * @return table
	 */
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
		String iId = iIdField.getText().trim();
		String iName = iNameField.getText().trim();
		String iUserId = iUserIdField.getText().trim();
		String iPos = iPosField.getText().trim();
		String iDep = iDepField.getText().trim();
		String iEmail = iEmailField.getText().trim();
		String iTel = iTelField.getText().trim();
		String iRemark = iRemarkField.getText().trim();
		boolean iIsAdmin = iIsAdminBox.isSelected();
		//	搜索人员信息
		List<List<String>> selUserInfo = SqlOpr.searchUserInfo(iId, iName, 
				iUserId, iPos, iDep, iEmail, iTel, iRemark, iIsAdmin);
		for(int i = 0; i < selUserInfo.size(); i++) {
			List<String> infoList = selUserInfo.get(i);//	每个人员信息的List集合
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
		//	给表格添加鼠标监听器
		table.addMouseListener(new MouseAdapter() {

			/* （非 Javadoc）
			 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				int row = table.rowAtPoint(e.getPoint());
				uNameField.setText(table.getValueAt(row, 1).toString());
				uUserIdField.setText(table.getValueAt(row, 2).toString());
				uIsAdminBox.setSelected(Boolean.valueOf(
						table.getValueAt(row, 3).toString()));
				uPosField.setText(table.getValueAt(row, 4).toString());
				uDepField.setText(table.getValueAt(row, 5).toString());
				uEmailField.setText(table.getValueAt(row, 6).toString());
				uTelField.setText(table.getValueAt(row, 7).toString());
				uRemarkField.setText(table.getValueAt(row, 8).toString());
			}
			
		});
		return table;
	}

	/**
	 * @return updatePane
	 */
	public JPanel getUpdatePane() {
		if(updatePane == null) {
			updatePane = new JPanel();
			updatePane.setLayout(new GridBagLayout());
			addComponent(updatePane, uNameLabel, 0, 0, 1);
			addComponent(updatePane, uNameField, 1, 0, 1);
			addComponent(updatePane, uUserIdLabel, 2, 0, 1);
			addComponent(updatePane, uUserIdField, 3, 0, 1);
			addComponent(updatePane, uIsAdminBox, 4, 0, 2);
			addComponent(updatePane, getUpdateButton(), 6, 0, 2);
			addComponent(updatePane, uPosLabel, 0, 1, 1);
			addComponent(updatePane, uPosField, 1, 1, 3);
			addComponent(updatePane, uDepLabel, 4, 1, 1);
			addComponent(updatePane, uDepField, 5, 1, 3);
			addComponent(updatePane, uEmailLabel, 0, 2, 1);
			addComponent(updatePane, uEmailField, 1, 2, 3);
			addComponent(updatePane, uTelLabel, 4, 2, 1);
			addComponent(updatePane, uTelField, 5, 2, 3);
			addComponent(updatePane, uRemarkLabel, 0, 3, 1);
			addComponent(updatePane, uRemarkField, 1, 3, 7);
		}
		return updatePane;
	}

	/**
	 * @return updateButton
	 */
	public JButton getUpdateButton() {
		if(updateButton == null) {
			updateButton = new JButton("更新(U)", new ImageIcon(
					getClass().getResource("/res/icon/update.png")));
			updateButton.setMnemonic(KeyEvent.VK_U);
			updateButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int row = table.getSelectedRow();
					if(row == -1) {
						JOptionPane.showMessageDialog(
								UserIFrame.this, "未选择人员", 
								"操作失败", JOptionPane.ERROR_MESSAGE);
						return;
					}
					String id = table.getValueAt(row, 0).toString();
					String uName = uNameField.getText().trim();
					String uUserId = uUserIdField.getText().trim();
					String uEmail = uEmailField.getText().trim();
					String uTel = uTelField.getText().trim();
					if(uName == null || uName.isEmpty()) {
						JOptionPane.showMessageDialog(
								UserIFrame.this, "姓名不能为空", 
								"输入错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(uUserId == null || uUserId.isEmpty()) {
						JOptionPane.showMessageDialog(
								UserIFrame.this, "用户名不能为空", 
								"输入错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(uEmail == null || uEmail.isEmpty()) {
						JOptionPane.showMessageDialog(
								UserIFrame.this, "邮箱不能为空", 
								"输入错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(uTel == null || uTel.isEmpty()) {
						JOptionPane.showMessageDialog(
								UserIFrame.this, "电话不能为空", 
								"输入错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
					//	获取所有人员信息
					List<List<String>> allUserInfo = SqlOpr.getAllUserInfo();
					for(int i = 0; i < allUserInfo.size(); i++) {
						String eachUserId = allUserInfo.get(i).get(2);
						if(uUserId.equals(eachUserId) && !uUserId.equals(
								table.getValueAt(row, 2).toString())) {
							JOptionPane.showMessageDialog(
									UserIFrame.this, "用户名已存在", 
									"输入错误", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					//	封装待更新人员信息的对象
					TbUserInfo userInfo = new TbUserInfo();
					userInfo.setId(Integer.parseInt(id));
					userInfo.setName(uName);
					userInfo.setUserId(uUserId);
					userInfo.setPos(uPosField.getText().trim());
					userInfo.setDep(uDepField.getText().trim());
					userInfo.setEmail(uEmail);
					userInfo.setTel(uTel);
					userInfo.setRemark(uRemarkField.getText().trim());
					userInfo.setAdmin(uIsAdminBox.isSelected());
					boolean res = SqlOpr.updateTbUserInfo(userInfo);
					if(res) {
						searchButton.doClick();
						uNameField.setText("");
						uUserIdField.setText("");
						uIsAdminBox.setSelected(false);
						uPosField.setText("");
						uDepField.setText("");
						uEmailField.setText("");
						uTelField.setText("");
						uRemarkField.setText("");
					}else
						JOptionPane.showMessageDialog(
								UserIFrame.this, "更新人员失败", 
								"操作失败", JOptionPane.ERROR_MESSAGE);
				}
			});
		}
		return updateButton;
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
