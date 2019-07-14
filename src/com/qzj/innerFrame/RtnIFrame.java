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
import com.qzj.sqlOpr.model.TbRtn;

/**
 * 	�黹�����ڲ�����
 * @author blingbling_inwin
 *
 */
public class RtnIFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 	���������
	 */
	private JScrollPane tablePane = null;

	/**
	 * 	���
	 */
	private JTable table = null;
	
	/**
	 * 	���ӹ黹�����
	 */
	private JPanel addPane = null;
	
	/**
	 * 	�黹�豸��ű�ǩ
	 */
	private JLabel devIdLabel = new JLabel("�黹�豸��ţ�");
	
	/**
	 * 	�黹�豸����ı���
	 */
	private JTextField devIdField = new JTextField(30);
	
	/**
	 * 	�黹�˹��ű�ǩ
	 */
	private JLabel rtnerIdLabel = new JLabel("�黹�˹��ţ�");
	
	/**
	 * 	�黹�˹����ı���
	 */
	private JTextField rtnerIdField = new JTextField(30);
	
	/**
	 * 	��ע��ǩ
	 */
	private JLabel remarkLabel = new JLabel("��ע��");
	
	/**
	 * 	��ע�ı���
	 */
	private JTextField remarkField = new JTextField(80);
	
	/**
	 * 	���ӹ黹����ť
	 */
	private JButton addButton = null;
	
	/**
	 * 	ˢ�°�ť
	 */
	private JButton refreshButton = null;

	public RtnIFrame() {
		setTitle("�黹����");
		setSize(1032, 432);
		setMaximizable(true);//	��������
		setResizable(true);//	���������
		setIconifiable(true);//	�����ͼ�껯
		setClosable(true);//	����ɹر�
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
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//	�ر��е��Զ�����
		String[] columnLabel = {"�黹�����", "�黹�豸���", "�黹�˹���", "�黹����", "��ע"};
		//	DefaultTableModel�����ڱ�����Ԫ��ֵ�ı��ģ����
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setColumnIdentifiers(columnLabel);//	���ñ��ģ�͵�������
		JTextField readOnlyField = new JTextField();//	����ֻ���ı��򹩱��Ԫʹ��
		readOnlyField.setEditable(false);//	���ı�����Ϊ���ɱ༭
		for(int i = 0; i < columnLabel.length; i++) {
			//	��ȡ��������Զ���
			TableColumn columnattr = table.getColumnModel().getColumn(i);
			//	�������еĵ�Ԫ�༭������Ϊֻ���༭��
			columnattr.setCellEditor(new DefaultCellEditor(readOnlyField));
			if(i > 2)
				columnattr.setPreferredWidth(300);//	��������п�
			else
				columnattr.setPreferredWidth(150);//	��������п�
		}
		//		��ʼ���������
		List<List<String>> allRtn = SqlOpr.getAllRtn();//	��ȡ���й黹����Ϣ
		for(int i = 0; i < allRtn.size(); i++) {
			List<String> rtnList = allRtn.get(i);//	ÿ���豸��Ϣ��List����
			Item item = new Item(rtnList.get(0), null, null);
			TbRtn rtn = SqlOpr.getRtn(item);//	��ȡָ���豸��Ϣ
			String[] row = new String[columnLabel.length];//	���ģ�͵�һ��
			//	�ж��豸��Ϣ�����Ƿ�Ϊ��
			if(rtn.getId() != null && !rtn.getId().isEmpty()) {
				row[0] = rtn.getId();
				row[1] = rtn.getDevId();
				row[2] = rtn.getRtnerId().toString();
				row[3] = rtn.getDate();
				row[4] = rtn.getRemark();
				tableModel.addRow(row);//	�ڱ��ģ��ĩβ����һ��
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
			addComponent(addPane, rtnerIdLabel, 2, 0, 1);
			addComponent(addPane, rtnerIdField, 3, 0, 1);
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
			addButton = new JButton("���ӹ黹��(A)", new ImageIcon(
					getClass().getResource("/res/icon/add.png")));
			addButton.setMnemonic(KeyEvent.VK_A);
			addButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String devId = devIdField.getText().trim();
					String rtnerId = rtnerIdField.getText().trim();
					if(devId == null || devId.isEmpty()) {
						JOptionPane.showMessageDialog(
								RtnIFrame.this, "�黹�豸��Ų���Ϊ��", 
								"�������", JOptionPane.ERROR_MESSAGE);
						return;
					}
					//	��ȡ�����豸��Ϣ
					List<List<String>> allDevInfo = SqlOpr.getAllDevInfo();
					boolean isDevExist = false;//	�ж��豸����Ƿ����
					for(int i = 0; i < allDevInfo.size(); i++) {
						//	��ȡ���ݿ����Ѵ��ڵ��豸���
						String existDevId = allDevInfo.get(i).get(0);
						if(devId.equals(existDevId)) {
							isDevExist = true;
							break;
						}
					}
					if(!isDevExist) {
						JOptionPane.showMessageDialog(
								RtnIFrame.this, "�豸��Ų�����", 
								"�������", JOptionPane.ERROR_MESSAGE);
						return;
					}
					Integer jobNum = null;
					if(rtnerId == null || rtnerId.isEmpty()) {
						JOptionPane.showMessageDialog(
								RtnIFrame.this, "�黹�˹��Ų���Ϊ��", 
								"�������", JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						jobNum = Integer.parseInt(
								rtnerId);
					}catch(NumberFormatException exc) {
						JOptionPane.showMessageDialog(RtnIFrame.this, 
								"����ֻ��Ϊ�������Ҳ��ܳ���" + Integer.MAX_VALUE,
								"�������", JOptionPane.ERROR_MESSAGE);
						return;
					}
					//	��ȡ������Ա��Ϣ
					List<List<String>> allUserInfo = SqlOpr.getAllUserInfo();
					boolean isUserExist = false;//	�ж���Ա�Ƿ����
					for(int i = 0; i < allUserInfo.size(); i++) {
						//	��ȡ���ݿ����Ѵ��ڵ���Ա����
						Integer existJobNum = Integer.parseInt(
								allUserInfo.get(i).get(0));
						if(jobNum.equals(existJobNum)) {
							isUserExist = true;
							break;
						}
					}
					if(!isUserExist) {
						JOptionPane.showMessageDialog(
								RtnIFrame.this, "�黹�˹��Ų�����", 
								"�������", JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						String status = SqlOpr.getStatusFromDevId(devId);
						if(status.equals("�����")) {
							JOptionPane.showMessageDialog(
									RtnIFrame.this, "��Ǹ���豸�ѹ黹", 
									"�޷��黹", JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (SQLException exc) {
						exc.printStackTrace();
					}
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					String rtnId = "rtnID_" + sdf.format(date);
					String dateStr = String.format("%tc", date);
					//	��ȡ���й黹��
					List<List<String>> allRtn = SqlOpr.getAllRtn();
					int idCount = 0;//	ͳ�Ƶ������й黹������
					for(int i = 0; i < allRtn.size(); i++) {
						String id = allRtn.get(i).get(0);//	��ȡ���й黹�����
						if(id.indexOf(rtnId) == 0)
							idCount++;
					}
					//	ȷ���黹�����
					idCount++;
					rtnId = rtnId + String.format("%03d", idCount);
					TbRtn rtn = new TbRtn();//	��װ�����ӹ黹���Ķ���
					rtn.setId(rtnId.trim());
					rtn.setDevId(devId);
					rtn.setRtnerId(jobNum);
					rtn.setDate(dateStr);
					rtn.setRemark(remarkField.getText().trim());
					boolean res = SqlOpr.insertTbRtn(rtn);
					if(res) {
						refreshButton.doClick();
						devIdField.setText("");
						rtnerIdField.setText("");
						remarkField.setText("");
					}else
						JOptionPane.showMessageDialog(
								RtnIFrame.this, "�黹�豸ʧ��", 
								"����ʧ��", JOptionPane.ERROR_MESSAGE);
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
			refreshButton = new JButton("ˢ��(R)", new ImageIcon(
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
	 * 	�������鲼�ֹ������������
	 * @param pane ��������������
	 * @param com �����ӵ����
	 * @param x gridx
	 * @param y gridy
	 * @param width gridwidth
	 */
	private void addComponent(JPanel pane, JComponent com, 
			int x, int y, int width) {
		GridBagConstraints gbc = new GridBagConstraints();//	�����������ƶ���
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.insets = new Insets(2, 8, 2, 8);
		pane.add(com, gbc);
	}
}
