package com.qzj.innerFrame;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
		setSize(1032, 432);
		setMaximizable(true);//	��������
		setResizable(true);//	���������
		setIconifiable(true);//	�����ͼ�껯
		setClosable(true);//	����ɹر�
		tablePane = new JScrollPane();
		tablePane.setViewportView(getTable());
		getContentPane().add(tablePane);
	}

	public JTable getTable() {
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//	�ر��е��Զ�����
		String[] columnLabel = {"�豸���", "�豸����", "�豸״̬", "�豸����", "��ע"};
		//	DefaultTableModel�����ڱ�����Ԫ��ֵ�ı��ģ����
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(columnLabel);//	���ñ��ģ�͵�������
		
		return table;
	}
}
