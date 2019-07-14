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
import com.qzj.sqlOpr.model.TbBrw;

/**
 * 	���ù����ڲ�����
 * @author blingbling_inwin
 *
 */
public class BrwIFrame extends JInternalFrame {

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
	 * 	���ӽ��õ����
	 */
	private JPanel addPane = null;
	
	/**
	 * 	�����豸��ű�ǩ
	 */
	private JLabel devIdLabel = new JLabel("�����豸��ţ�");
	
	/**
	 * 	�����豸����ı���
	 */
	private JTextField devIdField = new JTextField(30);
	
	/**
	 * 	�����˹��ű�ǩ
	 */
	private JLabel brwerIdLabel = new JLabel("�����˹��ţ�");
	
	/**
	 * 	�����˹����ı���
	 */
	private JTextField brwerIdField = new JTextField(30);
	
	/**
	 * 	��ע��ǩ
	 */
	private JLabel remarkLabel = new JLabel("��ע��");
	
	/**
	 * 	��ע�ı���
	 */
	private JTextField remarkField = new JTextField(80);
	
	/**
	 * 	���ӽ��õ���ť
	 */
	private JButton addButton = null;
	
	/**
	 * 	ˢ�°�ť
	 */
	private JButton refreshButton = null;

	public BrwIFrame() {
		setTitle("���ù���");
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
		String[] columnLabel = {"���õ����", "�����豸���", "�����˹���", "��������", "��ע"};
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
		//	��ʼ���������
		List<List<String>> allBrw = SqlOpr.getAllBrw();//	��ȡ���н��õ���Ϣ
		for(int i = 0; i < allBrw.size(); i++) {
			List<String> brwList = allBrw.get(i);//	ÿ���豸��Ϣ��List����
			Item item = new Item(brwList.get(0), null, null);
			TbBrw brw = SqlOpr.getBrw(item);//	��ȡָ���豸��Ϣ
			String[] row = new String[columnLabel.length];//	���ģ�͵�һ��
			//	�ж��豸��Ϣ�����Ƿ�Ϊ��
			if(brw.getId() != null && !brw.getId().isEmpty()) {
				row[0] = brw.getId();
				row[1] = brw.getDevId();
				row[2] = brw.getBrwerId().toString();
				row[3] = brw.getDate();
				row[4] = brw.getRemark();
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
			addComponent(addPane, brwerIdLabel, 2, 0, 1);
			addComponent(addPane, brwerIdField, 3, 0, 1);
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
			addButton = new JButton("���ӽ��õ�(A)", new ImageIcon(
					getClass().getResource("/res/icon/add.png")));
			addButton.setMnemonic(KeyEvent.VK_A);
			addButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String devId = devIdField.getText().trim();
					boolean isDevExist = false;//	�ж��豸����Ƿ����
					Integer jobNum = null;
					String brwerId = brwerIdField.getText().trim();
					if(devId == null || devId.isEmpty()) {
						JOptionPane.showMessageDialog(
								BrwIFrame.this, "�����豸��Ų���Ϊ��", 
								"�������", JOptionPane.ERROR_MESSAGE);
						return;
					}
					//	��ȡ�����豸��Ϣ
					List<List<String>> allDevInfo = SqlOpr.getAllDevInfo();
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
								BrwIFrame.this, "�豸��Ų�����", 
								"�������", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(brwerId == null || brwerId.isEmpty()) {
						JOptionPane.showMessageDialog(
								BrwIFrame.this, "�����˹��Ų���Ϊ��", 
								"�������", JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						jobNum = Integer.parseInt(
								brwerId);
					}catch(NumberFormatException exc) {
						JOptionPane.showMessageDialog(BrwIFrame.this, 
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
								BrwIFrame.this, "�����˹��Ų�����", 
								"�������", JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						String status = SqlOpr.getStatusFromDevId(devId);
						if(!status.equals("�����")) {
							JOptionPane.showMessageDialog(
									BrwIFrame.this, "��Ǹ���豸�ѽ��", 
									"�޷�����", JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (SQLException exc) {
						exc.printStackTrace();
					}
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					String brwId = "brwID_" + sdf.format(date);
					String dateStr = String.format("%tc", date);
					//	��ȡ���н��õ�
					List<List<String>> allBrw = SqlOpr.getAllBrw();
					int idCount = 0;//	ͳ�Ƶ������н��õ�����
					for(int i = 0; i < allBrw.size(); i++) {
						String id = allBrw.get(i).get(0);//	��ȡ���н��õ����
						if(id.indexOf(brwId) == 0)
							idCount++;
					}
					//	ȷ�����õ����
					idCount++;
					brwId = brwId + String.format("%03d", idCount);
					TbBrw brw = new TbBrw();//	��װ�����ӽ��õ��Ķ���
					brw.setId(brwId.trim());
					brw.setDevId(devId);
					brw.setBrwerId(jobNum);
					brw.setDate(dateStr);
					brw.setRemark(remarkField.getText().trim());
					boolean res = SqlOpr.insertTbBrw(brw);
					if(res) {
						refreshButton.doClick();
						devIdField.setText("");
						brwerIdField.setText("");
						remarkField.setText("");
					}else
						JOptionPane.showMessageDialog(
								BrwIFrame.this, "�����豸ʧ��", 
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
