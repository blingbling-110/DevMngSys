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
 * 	������Ա�ڲ�����
 * @author blingbling_inwin
 *
 */
public class UserIFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 	�������
	 */
	private JPanel inputPane = null;
	
	/**
	 * 	���빤�ű�ǩ
	 */
	private JLabel iIdLabel = new JLabel("���ţ�");
	
	/**
	 * 	���빤���ı���
	 */
	private JTextField iIdField = new JTextField(20);
	
	/**
	 * 	����������ǩ
	 */
	private JLabel iNameLabel = new JLabel("������");
	
	/**
	 * 	���������ı���
	 */
	private JTextField iNameField = new JTextField(20);
	
	/**
	 * 	�����û�����ǩ
	 */
	private JLabel iUserIdLabel = new JLabel("�û�����");
	
	/**
	 * 	�����û����ı���
	 */
	private JTextField iUserIdField = new JTextField(20);
	
	/**
	 * 	����ְ���ǩ
	 */
	private JLabel iPosLabel = new JLabel("ְ��");
	
	/**
	 * 	����ְ���ı���
	 */
	private JTextField iPosField = new JTextField(53);
	
	/**
	 * 	���벿�ű�ǩ
	 */
	private JLabel iDepLabel = new JLabel("���ţ�");
	
	/**
	 * 	���벿���ı���
	 */
	private JTextField iDepField = new JTextField(53);
	
	/**
	 * 	���������ǩ
	 */
	private JLabel iEmailLabel = new JLabel("���䣺");
	
	/**
	 * 	���������ı���
	 */
	private JTextField iEmailField = new JTextField(53);
	
	/**
	 * 	����绰��ǩ
	 */
	private JLabel iTelLabel = new JLabel("�绰��");
	
	/**
	 * 	����绰�ı���
	 */
	private JTextField iTelField = new JTextField(53);
	
	/**
	 * 	���뱸ע��ǩ
	 */
	private JLabel iRemarkLabel = new JLabel("��ע��");
	
	/**
	 * 	���뱸ע�ı���
	 */
	private JTextField iRemarkField = new JTextField(120);
	
	/**
	 * 	�����Ƿ�ӵ�й���Ȩ��ѡ���
	 */
	private JCheckBox iIsAdminBox = new JCheckBox("�Ƿ�ӵ�й���Ȩ��");
	
	/**
	 * 	������ť
	 */
	private JButton searchButton = null;
	
	/**
	 * 	������
	 */
	private JScrollPane tablePane = null; 
	
	/**
	 * 	���
	 */
	private JTable table = null;
	
	/**
	 * 	�������
	 */
	private JPanel updatePane = null;
	
	/**
	 * 	����������ǩ
	 */
	private JLabel uNameLabel = new JLabel("������");
	
	/**
	 * 	���������ı���
	 */
	private JTextField uNameField = new JTextField(20);
	
	/**
	 * 	�����û�����ǩ
	 */
	private JLabel uUserIdLabel = new JLabel("�û�����");
	
	/**
	 * 	�����û����ı���
	 */
	private JTextField uUserIdField = new JTextField(20);
	
	/**
	 * 	����ְ���ǩ
	 */
	private JLabel uPosLabel = new JLabel("ְ��");
	
	/**
	 * 	����ְ���ı���
	 */
	private JTextField uPosField = new JTextField(53);
	
	/**
	 * 	���벿�ű�ǩ
	 */
	private JLabel uDepLabel = new JLabel("���ţ�");
	
	/**
	 * 	���벿���ı���
	 */
	private JTextField uDepField = new JTextField(53);
	
	/**
	 * 	���������ǩ
	 */
	private JLabel uEmailLabel = new JLabel("���䣺");
	
	/**
	 * 	���������ı���
	 */
	private JTextField uEmailField = new JTextField(53);
	
	/**
	 * 	����绰��ǩ
	 */
	private JLabel uTelLabel = new JLabel("�绰��");
	
	/**
	 * 	����绰�ı���
	 */
	private JTextField uTelField = new JTextField(53);
	
	/**
	 * 	���±�ע��ǩ
	 */
	private JLabel uRemarkLabel = new JLabel("��ע��");
	
	/**
	 * 	���±�ע�ı���
	 */
	private JTextField uRemarkField = new JTextField(120);
	
	/**
	 * 	�����Ƿ�ӵ�й���Ȩ��ѡ���
	 */
	private JCheckBox uIsAdminBox = new JCheckBox("�Ƿ�ӵ�й���Ȩ��");
	
	/**
	 * 	���°�ť
	 */
	private JButton updateButton = null;

	/**
	 * 	������Ա�ڲ����幹�췽��
	 */
	public UserIFrame() {
		setTitle("������Ա");
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
			searchButton = new JButton("����(S)", new ImageIcon(
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
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//	�ر��е��Զ�����
		String[] columnLabel = {"����", "����", "�û���", "�Ƿ�ӵ�й���Ȩ��",
				"ְ��", "����", "��������", "�绰", "��ע"};
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
			if(i > 5)
				columnattr.setPreferredWidth(168);//	��������п�
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
		//	������Ա��Ϣ
		List<List<String>> selUserInfo = SqlOpr.searchUserInfo(iId, iName, 
				iUserId, iPos, iDep, iEmail, iTel, iRemark, iIsAdmin);
		for(int i = 0; i < selUserInfo.size(); i++) {
			List<String> infoList = selUserInfo.get(i);//	ÿ����Ա��Ϣ��List����
			Item item = new Item(infoList.get(2), infoList.get(1),
					Integer.parseInt(infoList.get(0)));
			TbUserInfo userInfo = SqlOpr.getUserInfo(item);//	��ȡָ����Ա��Ϣ
			String[] row = new String[columnLabel.length];//	���ģ�͵�һ��
			//	�ж���Ա��Ϣ�����Ƿ�Ϊ��
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
				tableModel.addRow(row);//	�ڱ��ģ��ĩβ����һ��
			}
		}
		//	����������������
		table.addMouseListener(new MouseAdapter() {

			/* ���� Javadoc��
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
			updateButton = new JButton("����(U)", new ImageIcon(
					getClass().getResource("/res/icon/update.png")));
			updateButton.setMnemonic(KeyEvent.VK_U);
			updateButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int row = table.getSelectedRow();
					if(row == -1) {
						JOptionPane.showMessageDialog(
								UserIFrame.this, "δѡ����Ա", 
								"����ʧ��", JOptionPane.ERROR_MESSAGE);
						return;
					}
					String id = table.getValueAt(row, 0).toString();
					String uName = uNameField.getText().trim();
					String uUserId = uUserIdField.getText().trim();
					String uEmail = uEmailField.getText().trim();
					String uTel = uTelField.getText().trim();
					if(uName == null || uName.isEmpty()) {
						JOptionPane.showMessageDialog(
								UserIFrame.this, "��������Ϊ��", 
								"�������", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(uUserId == null || uUserId.isEmpty()) {
						JOptionPane.showMessageDialog(
								UserIFrame.this, "�û�������Ϊ��", 
								"�������", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(uEmail == null || uEmail.isEmpty()) {
						JOptionPane.showMessageDialog(
								UserIFrame.this, "���䲻��Ϊ��", 
								"�������", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(uTel == null || uTel.isEmpty()) {
						JOptionPane.showMessageDialog(
								UserIFrame.this, "�绰����Ϊ��", 
								"�������", JOptionPane.ERROR_MESSAGE);
						return;
					}
					//	��ȡ������Ա��Ϣ
					List<List<String>> allUserInfo = SqlOpr.getAllUserInfo();
					for(int i = 0; i < allUserInfo.size(); i++) {
						String eachUserId = allUserInfo.get(i).get(2);
						if(uUserId.equals(eachUserId) && !uUserId.equals(
								table.getValueAt(row, 2).toString())) {
							JOptionPane.showMessageDialog(
									UserIFrame.this, "�û����Ѵ���", 
									"�������", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					//	��װ��������Ա��Ϣ�Ķ���
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
								UserIFrame.this, "������Աʧ��", 
								"����ʧ��", JOptionPane.ERROR_MESSAGE);
				}
			});
		}
		return updateButton;
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
