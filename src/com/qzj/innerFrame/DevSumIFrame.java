package com.qzj.innerFrame;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * 	�豸�����ڲ�����
 * @author qinzijun
 *
 */
public class DevSumIFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 	���ģ��
	 */
	private JTable table = null;
	
	/**
	 * 	���������
	 */
	private JScrollPane tablePane = null;
	
	public DevSumIFrame() {
		setTitle("�豸����");
		setMaximizable(true);//	��������
		setClosable(true);//	����ɹر�
		setSize(800, 600);
	}
}
