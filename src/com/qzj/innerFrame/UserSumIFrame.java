package com.qzj.innerFrame;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.qzj.Item;
import com.qzj.sqlOpr.SqlOpr;
import com.qzj.sqlOpr.model.TbUserInfo;

/**
 * 	��Ա�����ڲ�����
 * @author qinzijun
 *
 */
public class UserSumIFrame extends JInternalFrame {

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
	
	/**
	 * 	�����û����
	 */
	private JPanel addPane = null;
	
	/**
	 * 	���ű�ǩ
	 */
	private JLabel idLabel = new JLabel("���ţ�");
	
	/**
	 * 	�����ı���
	 */
	private JTextField idField = new JTextField(20);
	
	/**
	 * 	������ǩ
	 */
	private JLabel nameLabel = new JLabel("������");
	
	/**
	 * 	�����ı���
	 */
	private JTextField nameField = new JTextField(20);
	
	/**
	 * 	�û�����ǩ
	 */
	private JLabel userIdLabel = new JLabel("�û�����");
	
	/**
	 * 	�û����ı���
	 */
	private JTextField userIdField = new JTextField(20);
	
	/**
	 * 	�����ǩ
	 */
	private JLabel pwdLabel = new JLabel("���룺");
	
	/**
	 * 	�����
	 */
	private JPasswordField pwdField = new JPasswordField(20);
	
	/**
	 * 	ְ���ǩ
	 */
	private JLabel posLabel = new JLabel("ְ��");
	
	/**
	 * 	ְ���ı���
	 */
	private JTextField posField = new JTextField(53);
	
	/**
	 * 	���ű�ǩ
	 */
	private JLabel depLabel = new JLabel("���ţ�");
	
	/**
	 * 	�����ı���
	 */
	private JTextField depField = new JTextField(53);
	
	/**
	 * 	�����ǩ
	 */
	private JLabel emailLabel = new JLabel("���䣺");
	
	/**
	 * 	�����ı���
	 */
	private JTextField emailField = new JTextField(53);
	
	/**
	 * 	�绰��ǩ
	 */
	private JLabel telLabel = new JLabel("�绰��");
	
	/**
	 * 	�绰�ı���
	 */
	private JTextField telField = new JTextField(53);
	
	/**
	 * 	��ע��ǩ
	 */
	private JLabel remarkLabel = new JLabel("��ע��");
	
	/**
	 * 	��ע�ı���
	 */
	private JTextField remarkField = new JTextField(120);
	
	/**
	 * 	�����豸��ť
	 */
	private JButton addButton = null;
	
	/**
	 * 	ɾ���豸��ť
	 */
	private JButton deleteButton = null;
	
	/**
	 * 	ˢ�°�ť
	 */
	private JButton refreshButton = null;
	
	public UserSumIFrame() {
		setTitle("��Ա����");
		setSize(1032, 432);
		setMaximizable(true);//	��������
		setResizable(true);//	���������
		setIconifiable(true);//	�����ͼ�껯
		setClosable(true);//	����ɹر�
		getTablePane();
		tablePane.setViewportView(getTable());
		getContentPane().add(tablePane, BorderLayout.CENTER);
		getAddPane();
		addPane.setLayout(new GridBagLayout());
		addComponent(addPane, idLabel, 0, 0, 1);
		addComponent(addPane, idField, 1, 0, 1);
		addComponent(addPane, nameLabel, 2, 0, 1);
		addComponent(addPane, nameField, 3, 0, 1);
		addComponent(addPane, userIdLabel, 4, 0, 1);
		addComponent(addPane, userIdField, 5, 0, 1);
		addComponent(addPane, pwdLabel, 6, 0, 1);
		addComponent(addPane, pwdField, 7, 0, 1);
		addComponent(addPane, posLabel, 0, 1, 1);
		addComponent(addPane, posField, 1, 1, 3);
		addComponent(addPane, depLabel, 4, 1, 1);
		addComponent(addPane, depField, 5, 1, 3);
		addComponent(addPane, emailLabel, 0, 2, 1);
		addComponent(addPane, emailField, 1, 2, 3);
		addComponent(addPane, telLabel, 4, 2, 1);
		addComponent(addPane, telField, 5, 2, 3);
		addComponent(addPane, remarkLabel, 0, 3, 1);
		addComponent(addPane, remarkField, 1, 3, 7);
		addComponent(addPane, getAddButton(), 0, 5, 3);
		addComponent(addPane, getDeleteButton(), 3, 5, 2);
		addComponent(addPane, getRefreshButton(), 5, 5, 3);
		getContentPane().add(addPane, BorderLayout.SOUTH);
	}

	public JTable getTable() {
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//	�ر��е��Զ�����
		String[] columnLabel = {"����", "����", "�û���", "ְ��", 
				"����", "��������", "�绰", "��ע"};
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
			if(i > 3)
				columnattr.setPreferredWidth(168);//	��������п�
			else
				columnattr.setPreferredWidth(100);//	��������п�
		}
		//		��ʼ���������
		List<List<String>> allUserInfo = SqlOpr.getAllUserInfo();//	��ȡ������Ա��Ϣ
		for(int i = 0; i < allUserInfo.size(); i++) {
			List<String> infoList = allUserInfo.get(i);//	ÿ����Ա��Ϣ��List����
			Item item = new Item(infoList.get(2), infoList.get(1),
					Integer.parseInt(infoList.get(0)));
			TbUserInfo userInfo = SqlOpr.getUserInfo(item);//	��ȡָ����Ա��Ϣ
			String[] row = new String[columnLabel.length];//	���ģ�͵�һ��
			//	�ж���Ա��Ϣ�����Ƿ�Ϊ��
			if(userInfo.getId() != null && userInfo.getId() != 0) {
				row[0] = userInfo.getId().toString();
				row[1] = userInfo.getName();
				row[2] = userInfo.getUserId();
				row[3] = userInfo.getPos();
				row[4] = userInfo.getDep();
				row[5] = userInfo.getEmail();
				row[6] = userInfo.getTel();
				row[7] = userInfo.getRemark();
				tableModel.addRow(row);//	�ڱ��ģ��ĩβ����һ��
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
			addButton = new JButton("������Ա", new ImageIcon(
					getClass().getResource("/res/icon/add.png")));
			addButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(idField.getText() == null || 
							idField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "���Ų���Ϊ��");
						return;
					}
					if(nameField.getText() == null || 
							nameField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "��������Ϊ��");
						return;
					}
					if(userIdField.getText() == null || 
							userIdField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "�û�������Ϊ��");
						return;
					}
					String pwd = new String(pwdField.getPassword());
					if(pwd == null || pwd.isEmpty()) {
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "���벻��Ϊ��");
						return;
					}
					if(emailField.getText() == null || 
							emailField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "���䲻��Ϊ��");
						return;
					}
					if(telField.getText() == null || 
							telField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "�绰����Ϊ��");
						return;
					}
					//	��ȡ������Ա��Ϣ
					List<List<String>> allUserInfo = SqlOpr.getAllUserInfo();
					for(int i = 0; i < allUserInfo.size(); i++) {
						String userId = allUserInfo.get(i).get(2);
						if(userIdField.getText().equals(userId)) {
							JOptionPane.showMessageDialog(
									UserSumIFrame.this, "�û����Ѵ���");
							return;
						}
					}
					TbUserInfo userInfo = new TbUserInfo();//	��װ��������Ա��Ϣ�Ķ���
					try {
						userInfo.setId(Integer.parseInt(idField.getText()));
					}catch(NumberFormatException exc) {
						JOptionPane.showMessageDialog(UserSumIFrame.this, 
								"����ֻ��Ϊ�������Ҳ��ܳ���" + Integer.MAX_VALUE);
						return;
					}
					userInfo.setName(nameField.getText());
					userInfo.setUserId(userIdField.getText());
					userInfo.setPwd(pwd);
					userInfo.setPos(posField.getText());
					userInfo.setDep(depField.getText());
					userInfo.setEmail(emailField.getText());
					userInfo.setTel(telField.getText());
					userInfo.setRemark(remarkField.getText());
					boolean res = SqlOpr.insertTbUserInfo(userInfo);
					if(res) {
						refreshButton.doClick();
						idField.setText("");
						nameField.setText("");
						userIdField.setText("");
						pwdField.setText("");
						posField.setText("");
						depField.setText("");
						emailField.setText("");
						telField.setText("");
						remarkField.setText("");
					}else
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "������Աʧ��");
				}
			});
		}
		return addButton;
	}

	/**
	 * @return deleteButton
	 */
	public JButton getDeleteButton() {
		if(deleteButton == null) {
			deleteButton = new JButton("ɾ����ѡ��Ա", new ImageIcon(
					getClass().getResource("/res/icon/del.png")));
			deleteButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int[] rows = table.getSelectedRows();
					if(rows.length == 0) {
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "δѡ����Ա");
						return;
					}
					for(int i = 0; i < rows.length; i++) {
						String id = table.getValueAt(rows[i], 0).toString();
						boolean res = SqlOpr.deleteUserInfo(id);
						if(!res) {
							JOptionPane.showMessageDialog(UserSumIFrame.this,
									"ɾ����Աʧ�ܣ����²���ʧ�ܵ���Ա���ţ�" + id);
							break;
						}
					}
					refreshButton.doClick();
				}
			});
		}
		return deleteButton;
	}

	/**
	 * @return refreshButton
	 */
	public JButton getRefreshButton() {
		if(refreshButton == null) {
			refreshButton = new JButton("ˢ��", new ImageIcon(
					getClass().getResource("/res/icon/refresh.png")));
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
	 * @param fillHor �Ƿ�ˮƽ����
	 * @param fillVer �Ƿ�ֱ����
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
