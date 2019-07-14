package com.qzj.innerFrame;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
	 * 	���������
	 */
	private JScrollPane tablePane = null;

	/**
	 * 	���
	 */
	private JTable table = null;
	
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
	 * 	�Ƿ�ӵ�й���Ȩ��ѡ���
	 */
	private JCheckBox isAdminBox = new JCheckBox("�Ƿ�ӵ�й���Ȩ��");
	
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
		getContentPane().add(addPane, BorderLayout.SOUTH);
	}

	public JScrollPane getTablePane() {
		if(tablePane == null)
			tablePane = new JScrollPane();
		return tablePane;
	}

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
		//		��ʼ���������
		List<List<String>> allUserInfo = SqlOpr.getAllUserInfo();//	��ȡ������Ա��Ϣ
		for(int i = 0; i < allUserInfo.size(); i++) {
			List<String> infoList = allUserInfo.get(i);//	ÿ����Ա��Ϣ��List����
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
		return table;
	}

	public JPanel getAddPane() {
		if(addPane == null) {
			addPane = new JPanel();
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
			addComponent(addPane, isAdminBox, 0, 5, 2);
			addComponent(addPane, getAddButton(), 2, 5, 2);
			addComponent(addPane, getDeleteButton(), 4, 5, 2);
			addComponent(addPane, getRefreshButton(), 6, 5, 2);
		}
		return addPane;
	}
	
	public JButton getAddButton() {
		if(addButton == null) {
			addButton = new JButton("������Ա(A)", new ImageIcon(
					getClass().getResource("/res/icon/add.png")));
			addButton.setMnemonic(KeyEvent.VK_A);
			addButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String id = idField.getText().trim();
					String name = nameField.getText().trim();
					String userId = userIdField.getText().trim();
					String email = emailField.getText().trim();
					String tel = telField.getText().trim();
					if(id == null || id.isEmpty()) {
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "���Ų���Ϊ��", 
								"�������", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(name == null || name.isEmpty()) {
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "��������Ϊ��", 
								"�������", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(userId == null || userId.isEmpty()) {
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "�û�������Ϊ��", 
								"�������", JOptionPane.ERROR_MESSAGE);
						return;
					}
					String pwd = new String(pwdField.getPassword());
					if(pwd == null || pwd.isEmpty()) {
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "���벻��Ϊ��", 
								"�������", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(email == null || email.isEmpty()) {
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "���䲻��Ϊ��", 
								"�������", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(tel == null || tel.isEmpty()) {
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "�绰����Ϊ��", 
								"�������", JOptionPane.ERROR_MESSAGE);
						return;
					}
					//	��ȡ������Ա��Ϣ
					List<List<String>> allUserInfo = SqlOpr.getAllUserInfo();
					for(int i = 0; i < allUserInfo.size(); i++) {
						String eachId = allUserInfo.get(i).get(0);
						String eachUserId = allUserInfo.get(i).get(2);
						if(id.equals(eachId)) {
							JOptionPane.showMessageDialog(
									UserSumIFrame.this, "�����Ѵ���", 
									"�������", JOptionPane.ERROR_MESSAGE);
							return;
						}
						if(userId.equals(eachUserId)) {
							JOptionPane.showMessageDialog(
									UserSumIFrame.this, "�û����Ѵ���", 
									"�������", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					//	��װ��������Ա��Ϣ�Ķ���
					TbUserInfo userInfo = new TbUserInfo();
					try {
						userInfo.setId(Integer.parseInt(
								id));
					}catch(NumberFormatException exc) {
						JOptionPane.showMessageDialog(UserSumIFrame.this, 
								"����ֻ��Ϊ�������Ҳ��ܳ���" + Integer.MAX_VALUE,
								"�������", JOptionPane.ERROR_MESSAGE);
						return;
					}
					userInfo.setName(name);
					userInfo.setUserId(userId);
					userInfo.setPwd(pwd);
					userInfo.setPos(posField.getText().trim());
					userInfo.setDep(depField.getText().trim());
					userInfo.setEmail(email);
					userInfo.setTel(tel);
					userInfo.setRemark(remarkField.getText().trim());
					userInfo.setAdmin(isAdminBox.isSelected());
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
						isAdminBox.setSelected(false);
					}else
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "������Աʧ��", 
								"����ʧ��", JOptionPane.ERROR_MESSAGE);
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
			deleteButton = new JButton("ɾ����ѡ��Ա(D)", new ImageIcon(
					getClass().getResource("/res/icon/del.png")));
			deleteButton.setMnemonic(KeyEvent.VK_D);
			deleteButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int[] rows = table.getSelectedRows();
					if(rows.length == 0) {
						JOptionPane.showMessageDialog(
								UserSumIFrame.this, "δѡ����Ա", 
								"����ʧ��", JOptionPane.ERROR_MESSAGE);
						return;
					}
					for(int i = 0; i < rows.length; i++) {
						String id = table.getValueAt(rows[i], 0).toString();
						if(id.equals("0")) {
							JOptionPane.showMessageDialog(UserSumIFrame.this,
									"�޷�ɾ����������Ա", "����ʧ��", 
									JOptionPane.ERROR_MESSAGE);
							continue;
						}
						boolean res = SqlOpr.deleteUserInfo(id);
						if(!res) {
							JOptionPane.showMessageDialog(UserSumIFrame.this,
									"ɾ��ʧ�ܵ���Ա���ţ�" + id, "����ʧ��", 
									JOptionPane.ERROR_MESSAGE);
							continue;
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
