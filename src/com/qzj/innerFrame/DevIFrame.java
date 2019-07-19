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
import java.util.ArrayList;
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
import com.qzj.MainFrame;
import com.qzj.sqlOpr.SqlOpr;
import com.qzj.sqlOpr.model.TbDevInfo;
import com.qzj.sqlOpr.model.TbUserInfo;

/**
 * 	�����豸�ڲ�����
 * @author blingbling_inwin
 *
 */
public class DevIFrame extends JInternalFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 	�������
	 */
	private JPanel inputPane = null;

	/**
	 * 	�����豸��ű�ǩ
	 */
	private JLabel iIdLabel = new JLabel("�豸��ţ�");

	/**
	 * 	�����豸����ı���
	 */
	private JTextField iIdField = new JTextField(25);

	/**
	 * 	�����豸���Ʊ�ǩ
	 */
	private JLabel iNameLabel = new JLabel("�豸���ƣ�");

	/**
	 * 	�����豸�����ı���
	 */
	private JTextField iNameField = new JTextField(25);

	/**
	 * 	�����豸״̬��ǩ
	 */
	private JLabel iStatusLabel = new JLabel("�豸״̬/�����˹��Ż�������");

	/**
	 * 	�����豸״̬�ı���
	 */
	private JTextField iStatusField = new JTextField(25);

	/**
	 * 	�����豸������ǩ
	 */
	private JLabel iDesLabel = new JLabel("�豸������");

	/**
	 * 	�����豸�����ı���
	 */
	private JTextField iDesField = new JTextField(126);

	/**
	 * 	���뱸ע��ǩ
	 */
	private JLabel iRemarkLabel = new JLabel("��ע��");

	/**
	 * 	���뱸ע�ı���
	 */
	private JTextField iRemarkField = new JTextField(126);

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
	 * 	�����豸���Ʊ�ǩ
	 */
	private JLabel uNameLabel = new JLabel("�豸���ƣ�");

	/**
	 * 	�����豸�����ı���
	 */
	private JTextField uNameField = new JTextField(25);

	/**
	 * 	�����豸������ǩ
	 */
	private JLabel uDesLabel = new JLabel("�豸������");

	/**
	 * 	�����豸�����ı���
	 */
	private JTextField uDesField = new JTextField(110);

	/**
	 * 	���±�ע��ǩ
	 */
	private JLabel uRemarkLabel = new JLabel("��ע��");

	/**
	 * 	���±�ע�ı���
	 */
	private JTextField uRemarkField = new JTextField(110);

	/**
	 * 	���°�ť
	 */
	private JButton updateButton = null;

	/**
	 * 	�����豸�ڲ����幹�췽��
	 */
	public DevIFrame() {
		setTitle("�����豸");
		setSize(1032, 432);
		setMaximizable(true);
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		Container c = getContentPane();
		c.add(getInputPane(), BorderLayout.NORTH);
		c.add(getTablePane(), BorderLayout.CENTER);
		if(MainFrame.isAdmin)
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
			addComponent(inputPane, iStatusLabel, 4, 0, 1);
			addComponent(inputPane, iStatusField, 5, 0, 1);
			addComponent(inputPane, iDesLabel, 0, 1, 1);
			addComponent(inputPane, iDesField, 1, 1, 5);
			addComponent(inputPane, iRemarkLabel, 0, 2, 1);
			addComponent(inputPane, iRemarkField, 1, 2, 5);
			addComponent(inputPane, getSearchButton(), 0, 3, 6);
		}
		return inputPane;
	}

	/**
	 * @return searchButton
	 */
	public JButton getSearchButton() {
		if(searchButton == null) {
			searchButton = new JButton("����(S)", new ImageIcon(
					getClass().getResource("/res/icon/search.png")));
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
		String[] columnLabel = {"�豸���", "�豸����", "�豸״̬",
				"����������", "�绰", "��������", "�豸����", "��ע"};
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
			if(i > 3 && i < 6)
				columnattr.setPreferredWidth(200);//	��������п�
			else if (i > 5)
				columnattr.setPreferredWidth(300);
            else
				columnattr.setPreferredWidth(130);
		}
		String iId = iIdField.getText().trim();
		String iName = iNameField.getText().trim();
		String iStatus = iStatusField.getText().trim();
		String iDes = iDesField.getText().trim();
		String iRemark = iRemarkField.getText().trim();
		List<List<String>> selBrwerId = new ArrayList<>();
		//	������Ϊ�գ�����ʾ�����豸
		if (iStatus == null || iStatus.isEmpty()) {
			ArrayList<String> none=  new ArrayList<>();
			none.add(iStatus);
			selBrwerId.add(none);
		}else {
			//	����������������������������˹���
			selBrwerId = SqlOpr.searchId(iStatus);
			//	��δ�����������˹��ţ���˵����������豸״̬���߽����˹���
			if (selBrwerId.size() == 0) {
				ArrayList<String> someone=  new ArrayList<>();
				someone.add(iStatus);
				selBrwerId.add(someone);
			}
		}
		for(List<String> a: selBrwerId) {
			//	�����豸��Ϣ
			List<List<String>> selDevInfo = SqlOpr.searchDevInfo(
					iId, iName, a.get(0), iDes, iRemark);
			for(int i = 0; i < selDevInfo.size(); i++) {
				List<String> infoList = selDevInfo.get(i);//	ÿ���豸��Ϣ��List����
				Item devItem = new Item(infoList.get(0), infoList.get(1), null);
				TbDevInfo devInfo = SqlOpr.getDevInfo(devItem);//	��ȡָ���豸��Ϣ
				TbUserInfo userInfo = new TbUserInfo();
				//	�ж��Ƿ���
				if (!devInfo.getStatus().equals("�����")) {
					Item userItem = new Item(null,null,
							Integer.parseInt(devInfo.getStatus().substring(3)));
					userInfo = SqlOpr.getUserInfo(userItem);//	��ȡָ����Ա��Ϣ
				}
				String[] row = new String[columnLabel.length];//	���ģ�͵�һ��
				//	�ж��豸��Ϣ�����Ƿ�Ϊ��
				if(devInfo.getId() != null && !devInfo.getId().isEmpty()) {
					row[0] = devInfo.getId();
					row[1] = devInfo.getName();
					row[2] = devInfo.getStatus();
					row[6] = devInfo.getDes();
					row[7] = devInfo.getRemark();
					if (!row[2].equals("�����")) {
						row[3] = userInfo.getName();
						row[4] = userInfo.getTel();
						row[5] = userInfo.getEmail();
					}
					tableModel.addRow(row);//	�ڱ��ģ��ĩβ����һ��
				}
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
				uDesField.setText(table.getValueAt(row, 6).toString());
				uRemarkField.setText(table.getValueAt(row, 7).toString());
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
			addComponent(updatePane, getUpdateButton(), 2, 0, 4);
			addComponent(updatePane, uDesLabel, 0, 1, 1);
			addComponent(updatePane, uDesField, 1, 1, 5);
			addComponent(updatePane, uRemarkLabel, 0, 2, 1);
			addComponent(updatePane, uRemarkField, 1, 2, 5);
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
								DevIFrame.this, "δѡ���豸",
								"����ʧ��", JOptionPane.ERROR_MESSAGE);
						return;
					}
					String id = table.getValueAt(row, 0).toString();
					String uName = uNameField.getText().trim();
					if(uName == null || uName.isEmpty()) {
						JOptionPane.showMessageDialog(
								DevIFrame.this, "�豸���Ʋ���Ϊ��",
								"�������", JOptionPane.ERROR_MESSAGE);
						return;
					}
					//	��װ�������豸��Ϣ�Ķ���
					TbDevInfo devInfo = new TbDevInfo();
					devInfo.setId(id);
					devInfo.setName(uName);
					devInfo.setDes(uDesField.getText().trim());
					devInfo.setRemark(uRemarkField.getText().trim());
					boolean res = SqlOpr.updateTbDevInfo(devInfo);
					if(res) {
						searchButton.doClick();
						uNameField.setText("");
						uDesField.setText("");
						uRemarkField.setText("");
					}else
						JOptionPane.showMessageDialog(
								DevIFrame.this, "�����豸ʧ��",
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
