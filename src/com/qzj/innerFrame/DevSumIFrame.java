package com.qzj.innerFrame;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
		setMaximizable(true);//	窗体可最大化
		setClosable(true);//	窗体可关闭
		setSize(800, 600);
	}
}
