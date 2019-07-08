package com.qzj.innerFrame;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
	
	public DevSumIFrame() {
		setTitle("设备总览");
		setSize(1032, 432);
		setMaximizable(true);//	窗体可最大化
		setResizable(true);//	窗体可拉伸
		setIconifiable(true);//	窗体可图标化
		setClosable(true);//	窗体可关闭
		tablePane = new JScrollPane();
		tablePane.setViewportView(getTable());
		getContentPane().add(tablePane);
	}

	public JTable getTable() {
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//	关闭列的自动调节
		String[] columnLabel = {"设备编号", "设备名称", "设备状态", "设备描述", "备注"};
		//	DefaultTableModel是用于保存表格单元数值的表格模型类
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(columnLabel);//	设置表格模型的列名称
		
		return table;
	}
}
