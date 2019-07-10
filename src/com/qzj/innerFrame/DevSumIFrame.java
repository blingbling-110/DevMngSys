package com.qzj.innerFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.qzj.Item;
import com.qzj.sqlOpr.SqlOpr;
import com.qzj.sqlOpr.model.TbDevInfo;

/**
 * 	设备总览内部窗体
 * @author qinzijun
 *
 */
public class DevSumIFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 	表格模型
	 */
	private JTable table = null;
	
	/**
	 * 	表格滚动面板
	 */
	private JScrollPane tablePane = null;
	
	/**
	 * 	增加设备面板
	 */
	private JPanel addPane = null;
	
	/**
	 * 	设备编号标签
	 */
	private JLabel idLabel = new JLabel("设备编号：");
	
	/**
	 * 	设备编号文本框
	 */
	private JTextField idField = new JTextField(25);
	
	/**
	 * 	设备名称标签
	 */
	private JLabel nameLabel = new JLabel("设备名称：");
	
	/**
	 * 	设备名称文本框
	 */
	private JTextField nameField = new JTextField(25);
	
	/**
	 * 	设备状态标签
	 */
	private JLabel statusLabel = new JLabel("设备状态：");
	
	/**
	 * 	设备状态文本框
	 */
	private JTextField statusField = new JTextField(25);
	
	/**
	 * 	设备描述标签
	 */
	private JLabel desLabel = new JLabel("设备描述：");
	
	/**
	 * 	设备描述文本域
	 */
	private JTextField desField = new JTextField(110);
	
	/**
	 * 	备注标签
	 */
	private JLabel remarkLabel = new JLabel("备注：");
	
	/**
	 * 	备注文本域
	 */
	private JTextField remarkField = new JTextField(110);
	
	/**
	 * 	增加设备按钮
	 */
	private JButton addButton = null;
	
	public DevSumIFrame() {
		setTitle("设备总览");
		setSize(1032, 432);
		setMaximizable(true);//	窗体可最大化
		setResizable(true);//	窗体可拉伸
		setIconifiable(true);//	窗体可图标化
		setClosable(true);//	窗体可关闭
		getTablePane();
		tablePane.setViewportView(getTable());
		getContentPane().add(tablePane, BorderLayout.CENTER);
		getAddPane();
		addPane.setLayout(new GridBagLayout());
		addComponent(addPane, idLabel, 0, 0, 1);
		addComponent(addPane, idField, 1, 0, 2);
		addComponent(addPane, nameLabel, 3, 0, 1);
		addComponent(addPane, nameField, 4, 0, 2);
		addComponent(addPane, statusLabel, 6, 0, 1);
		addComponent(addPane, statusField, 7, 0, 2);
		addComponent(addPane, desLabel, 0, 1, 1);
		addComponent(addPane, desField, 0, 2, 9);
		addComponent(addPane, remarkLabel, 0, 4, 1);
		addComponent(addPane, remarkField, 0, 5, 9);
		addComponent(addPane, getAddButton(), 0, 7, 9);
		getContentPane().add(addPane, BorderLayout.SOUTH);
	}

	public JTable getTable() {
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//	关闭列的自动调节
		String[] columnLabel = {"设备编号", "设备名称", "设备状态", "设备描述", "备注"};
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
				columnattr.setPreferredWidth(100);//	调整表格列宽
		}
		//		初始化表格内容
		List<List<String>> allDevInfo = SqlOpr.getAllDevInfo();//	获取所有设备信息
		for(int i = 0; i < allDevInfo.size(); i++) {
			List<String> infoList = allDevInfo.get(i);//	每个设备信息的List集合
			Item item = new Item(infoList.get(0), infoList.get(1), null);
			TbDevInfo devInfo = SqlOpr.getDevInfo(item);//	获取指定设备信息
			String[] row = new String[columnLabel.length];//	表格模型的一行
			//	判断设备信息对象是否为空
			if(devInfo.getId() != null && !devInfo.getId().isEmpty()) {
				row[0] = devInfo.getId();
				row[1] = devInfo.getName();
				row[2] = devInfo.getStatus();
				row[3] = devInfo.getDes();
				row[4] = devInfo.getRemark();
				tableModel.addRow(row);//	在表格模型末尾增加一行
			}
		}
		return table;
	}

	public JScrollPane getTablePane() {
		if(tablePane == null)
			tablePane = new JScrollPane();
		return tablePane;
	}

	public JPanel getAddPane() {
		if(addPane == null)
			addPane = new JPanel();
		return addPane;
	}
	
	public JButton getAddButton() {
		if(addButton == null) {
			addButton = new JButton("增加设备", new ImageIcon(
					getClass().getResource("/res/icon/addDev.png")));
			addButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					
				}
			});
		}
		return addButton;
	}

	/**
	 * 	按网格组布局管理器增加组件
	 * @param pane 待增加组件的面板
	 * @param com 待增加的组件
	 * @param x gridx
	 * @param y gridy
	 * @param width gridwidth
	 * @param fillHor 是否水平填满
	 * @param fillVer 是否垂直填满
	 */
	private void addComponent(JPanel pane, JComponent com, 
			int x, int y, int width) {
		GridBagConstraints gbc = new GridBagConstraints();//	创建网格限制对象
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		pane.add(com, gbc);
	}
}
