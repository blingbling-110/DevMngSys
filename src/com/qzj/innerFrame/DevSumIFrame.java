package com.qzj.innerFrame;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
	
	public DevSumIFrame() {
		setTitle("设备总览");
		setSize(1032, 432);
		setMaximizable(true);//	窗体可最大化
		setResizable(true);//	窗体可拉伸
		setIconifiable(true);//	窗体可图标化
		setClosable(true);//	窗体可关闭
		tablePane = new JScrollPane();
		tablePane.setViewportView(getTable());
		getContentPane().add(tablePane, BorderLayout.CENTER);
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
}
