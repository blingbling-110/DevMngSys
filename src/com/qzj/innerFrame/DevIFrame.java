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
import java.util.ArrayList;
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
import com.qzj.MainFrame;
import com.qzj.sqlOpr.SqlOpr;
import com.qzj.sqlOpr.model.TbDevInfo;
import com.qzj.sqlOpr.model.TbUserInfo;

/**
 * 	搜索设备内部窗体
 * @author blingbling_inwin
 *
 */
public class DevIFrame extends JInternalFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 	输入面板
	 */
	private JPanel inputPane = null;

	/**
	 * 	输入设备编号标签
	 */
	private JLabel iIdLabel = new JLabel("设备编号：");

	/**
	 * 	输入设备编号文本框
	 */
	private JTextField iIdField = new JTextField(25);

	/**
	 * 	输入设备名称标签
	 */
	private JLabel iNameLabel = new JLabel("设备名称：");

	/**
	 * 	输入设备名称文本框
	 */
	private JTextField iNameField = new JTextField(25);

	/**
	 * 	输入设备状态标签
	 */
	private JLabel iStatusLabel = new JLabel("设备状态/借用人工号或姓名：");

	/**
	 * 	输入设备状态文本框
	 */
	private JTextField iStatusField = new JTextField(25);

	/**
	 * 	输入设备描述标签
	 */
	private JLabel iDesLabel = new JLabel("设备描述：");

	/**
	 * 	输入设备描述文本框
	 */
	private JTextField iDesField = new JTextField(126);

	/**
	 * 	输入备注标签
	 */
	private JLabel iRemarkLabel = new JLabel("备注：");

	/**
	 * 	输入备注文本框
	 */
	private JTextField iRemarkField = new JTextField(126);

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
	 * 	更新设备名称标签
	 */
	private JLabel uNameLabel = new JLabel("设备名称：");

	/**
	 * 	更新设备名称文本框
	 */
	private JTextField uNameField = new JTextField(25);

	/**
	 * 	更新设备描述标签
	 */
	private JLabel uDesLabel = new JLabel("设备描述：");

	/**
	 * 	更新设备描述文本框
	 */
	private JTextField uDesField = new JTextField(110);

	/**
	 * 	更新备注标签
	 */
	private JLabel uRemarkLabel = new JLabel("备注：");

	/**
	 * 	更新备注文本框
	 */
	private JTextField uRemarkField = new JTextField(110);

	/**
	 * 	更新按钮
	 */
	private JButton updateButton = null;

	/**
	 * 	搜索设备内部窗体构造方法
	 */
	public DevIFrame() {
		setTitle("搜索设备");
		setSize(1032, 432);
		setMaximizable(true);
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		Container c = getContentPane();
		c.add(getInputPane(), BorderLayout.NORTH);
		c.add(getTablePane(), BorderLayout.CENTER);
		if(MainFrame.isAdmin)
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
			addComponent(inputPane, iStatusLabel, 4, 0, 1);
			addComponent(inputPane, iStatusField, 5, 0, 1);
			addComponent(inputPane, iDesLabel, 0, 1, 1);
			addComponent(inputPane, iDesField, 1, 1, 5);
			addComponent(inputPane, iRemarkLabel, 0, 2, 1);
			addComponent(inputPane, iRemarkField, 1, 2, 5);
			addComponent(inputPane, getSearchButton(), 0, 3, 6);
		}
		return inputPane;
	}

	/**
	 * @return searchButton
	 */
	public JButton getSearchButton() {
		if(searchButton == null) {
			searchButton = new JButton("搜索(S)", new ImageIcon(
					getClass().getResource("/res/icon/search.png")));
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
		String[] columnLabel = {"设备编号", "设备名称", "设备状态",
				"借用人姓名", "电话", "电子邮箱", "设备描述", "备注"};
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
			if(i > 3 && i < 6)
				columnattr.setPreferredWidth(200);//	调整表格列宽
			else if (i > 5)
				columnattr.setPreferredWidth(300);
            else
				columnattr.setPreferredWidth(130);
		}
		String iId = iIdField.getText().trim();
		String iName = iNameField.getText().trim();
		String iStatus = iStatusField.getText().trim();
		String iDes = iDesField.getText().trim();
		String iRemark = iRemarkField.getText().trim();
		List<List<String>> selBrwerId = new ArrayList<>();
		//	若输入为空，则显示所有设备
		if (iStatus == null || iStatus.isEmpty()) {
			ArrayList<String> none=  new ArrayList<>();
			none.add(iStatus);
			selBrwerId.add(none);
		}else {
			//	若输入借用人姓名则先搜索借用人工号
			selBrwerId = SqlOpr.searchId(iStatus);
			//	若未搜索到借用人工号，则说明输入的是设备状态或者借用人工号
			if (selBrwerId.size() == 0) {
				ArrayList<String> someone=  new ArrayList<>();
				someone.add(iStatus);
				selBrwerId.add(someone);
			}
		}
		for(List<String> a: selBrwerId) {
			//	搜索设备信息
			List<List<String>> selDevInfo = SqlOpr.searchDevInfo(
					iId, iName, a.get(0), iDes, iRemark);
			for(int i = 0; i < selDevInfo.size(); i++) {
				List<String> infoList = selDevInfo.get(i);//	每个设备信息的List集合
				Item devItem = new Item(infoList.get(0), infoList.get(1), null);
				TbDevInfo devInfo = SqlOpr.getDevInfo(devItem);//	获取指定设备信息
				TbUserInfo userInfo = new TbUserInfo();
				//	判断是否借出
				if (!devInfo.getStatus().equals("库存中")) {
					Item userItem = new Item(null,null,
							Integer.parseInt(devInfo.getStatus().substring(3)));
					userInfo = SqlOpr.getUserInfo(userItem);//	获取指定人员信息
				}
				String[] row = new String[columnLabel.length];//	表格模型的一行
				//	判断设备信息对象是否为空
				if(devInfo.getId() != null && !devInfo.getId().isEmpty()) {
					row[0] = devInfo.getId();
					row[1] = devInfo.getName();
					row[2] = devInfo.getStatus();
					row[6] = devInfo.getDes();
					row[7] = devInfo.getRemark();
					if (!row[2].equals("库存中")) {
						row[3] = userInfo.getName();
						row[4] = userInfo.getTel();
						row[5] = userInfo.getEmail();
					}
					tableModel.addRow(row);//	在表格模型末尾增加一行
				}
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
				uDesField.setText(table.getValueAt(row, 6).toString());
				uRemarkField.setText(table.getValueAt(row, 7).toString());
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
			addComponent(updatePane, getUpdateButton(), 2, 0, 4);
			addComponent(updatePane, uDesLabel, 0, 1, 1);
			addComponent(updatePane, uDesField, 1, 1, 5);
			addComponent(updatePane, uRemarkLabel, 0, 2, 1);
			addComponent(updatePane, uRemarkField, 1, 2, 5);
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
								DevIFrame.this, "未选择设备",
								"操作失败", JOptionPane.ERROR_MESSAGE);
						return;
					}
					String id = table.getValueAt(row, 0).toString();
					String uName = uNameField.getText().trim();
					if(uName == null || uName.isEmpty()) {
						JOptionPane.showMessageDialog(
								DevIFrame.this, "设备名称不能为空",
								"输入错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
					//	封装待更新设备信息的对象
					TbDevInfo devInfo = new TbDevInfo();
					devInfo.setId(id);
					devInfo.setName(uName);
					devInfo.setDes(uDesField.getText().trim());
					devInfo.setRemark(uRemarkField.getText().trim());
					boolean res = SqlOpr.updateTbDevInfo(devInfo);
					if(res) {
						searchButton.doClick();
						uNameField.setText("");
						uDesField.setText("");
						uRemarkField.setText("");
					}else
						JOptionPane.showMessageDialog(
								DevIFrame.this, "更新设备失败",
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
