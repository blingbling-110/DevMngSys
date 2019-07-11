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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.qzj.Item;
import com.qzj.sqlOpr.SqlOpr;
import com.qzj.sqlOpr.model.TbDevInfo;

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
	
	/**
	 * 	�����豸���
	 */
	private JPanel addPane = null;
	
	/**
	 * 	�豸��ű�ǩ
	 */
	private JLabel idLabel = new JLabel("�豸��ţ�");
	
	/**
	 * 	�豸����ı���
	 */
	private JTextField idField = new JTextField(25);
	
	/**
	 * 	�豸���Ʊ�ǩ
	 */
	private JLabel nameLabel = new JLabel("�豸���ƣ�");
	
	/**
	 * 	�豸�����ı���
	 */
	private JTextField nameField = new JTextField(25);
	
	/**
	 * 	�豸״̬��ǩ
	 */
	private JLabel statusLabel = new JLabel("�豸״̬��");
	
	/**
	 * 	�豸״̬�ı���
	 */
	private JTextField statusField = new JTextField(25);
	
	/**
	 * 	�豸������ǩ
	 */
	private JLabel desLabel = new JLabel("�豸������");
	
	/**
	 * 	�豸�����ı���
	 */
	private JTextField desField = new JTextField(110);
	
	/**
	 * 	��ע��ǩ
	 */
	private JLabel remarkLabel = new JLabel("��ע��");
	
	/**
	 * 	��ע�ı���
	 */
	private JTextField remarkField = new JTextField(110);
	
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
	
	public DevSumIFrame() {
		setTitle("�豸����");
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
		addComponent(addPane, idField, 1, 0, 2);
		addComponent(addPane, nameLabel, 3, 0, 1);
		addComponent(addPane, nameField, 4, 0, 2);
		addComponent(addPane, statusLabel, 6, 0, 1);
		addComponent(addPane, statusField, 7, 0, 2);
		addComponent(addPane, desLabel, 0, 1, 1);
		addComponent(addPane, desField, 1, 1, 8);
		addComponent(addPane, remarkLabel, 0, 2, 1);
		addComponent(addPane, remarkField, 1, 2, 8);
		addComponent(addPane, getAddButton(), 0, 3, 3);
		addComponent(addPane, getDeleteButton(), 3, 3, 3);
		addComponent(addPane, getRefreshButton(), 6, 3, 3);
		getContentPane().add(addPane, BorderLayout.SOUTH);
	}

	public JTable getTable() {
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);//	�ر��е��Զ�����
		String[] columnLabel = {"�豸���", "�豸����", "�豸״̬", "�豸����", "��ע"};
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
				columnattr.setPreferredWidth(100);//	��������п�
		}
		//		��ʼ���������
		List<List<String>> allDevInfo = SqlOpr.getAllDevInfo();//	��ȡ�����豸��Ϣ
		for(int i = 0; i < allDevInfo.size(); i++) {
			List<String> infoList = allDevInfo.get(i);//	ÿ���豸��Ϣ��List����
			Item item = new Item(infoList.get(0), infoList.get(1), null);
			TbDevInfo devInfo = SqlOpr.getDevInfo(item);//	��ȡָ���豸��Ϣ
			String[] row = new String[columnLabel.length];//	���ģ�͵�һ��
			//	�ж��豸��Ϣ�����Ƿ�Ϊ��
			if(devInfo.getId() != null && !devInfo.getId().isEmpty()) {
				row[0] = devInfo.getId();
				row[1] = devInfo.getName();
				row[2] = devInfo.getStatus();
				row[3] = devInfo.getDes();
				row[4] = devInfo.getRemark();
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
			addButton = new JButton("�����豸", new ImageIcon(
					getClass().getResource("/res/icon/add.png")));
			addButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(idField.getText() == null || 
							idField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(
								DevSumIFrame.this, "�豸��Ų���Ϊ��");
						return;
					}
					if(nameField.getText() == null || 
							nameField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(
								DevSumIFrame.this, "�豸���Ʋ���Ϊ��");
						return;
					}
					if(statusField.getText() == null || 
							statusField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(
								DevSumIFrame.this, "�豸״̬����Ϊ��");
						return;
					}
					TbDevInfo devInfo = new TbDevInfo();//	��װ�������豸��Ϣ�Ķ���
					devInfo.setId(idField.getText());
					devInfo.setName(nameField.getText());
					devInfo.setStatus(statusField.getText());
					devInfo.setDes(desField.getText());
					devInfo.setRemark(remarkField.getText());
					boolean res = SqlOpr.insertTbDevInfo(devInfo);
					if(res) {
						refreshButton.doClick();
						idField.setText("");
						nameField.setText("");
						statusField.setText("");
						desField.setText("");
						remarkField.setText("");
					}else
						JOptionPane.showMessageDialog(
								DevSumIFrame.this, "�����豸ʧ��");
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
			deleteButton = new JButton("ɾ����ѡ�豸", new ImageIcon(
					getClass().getResource("/res/icon/del.png")));
			deleteButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int[] rows = table.getSelectedRows();
					if(rows.length == 0) {
						JOptionPane.showMessageDialog(
								DevSumIFrame.this, "δѡ���豸");
						return;
					}
					for(int i = 0; i < rows.length; i++) {
						String id = table.getValueAt(rows[i], 0).toString();
						boolean res = SqlOpr.deleteDevInfo(id);
						if(!res) {
							JOptionPane.showMessageDialog(DevSumIFrame.this,
									"ɾ���豸ʧ�ܣ����²���ʧ�ܵ��豸��ţ�" + id);
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
