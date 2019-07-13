package com.qzj.innerFrame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.qzj.sqlOpr.SqlOpr;

/**
 * 	���ݿⱸ���ڲ�����
 * @author qinzijun
 *
 */
public class BackupIFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 	����·���ı���
	 */
	private JTextField pathField = null;
	
	/**
	 * 	���ݰ�ť
	 */
	private JButton backupButton = null;
	
	public BackupIFrame() {
		setTitle("���ݿⱸ��");
		setSize(800, 65);
		setMaximizable(false);//	���岻�����
		setResizable(false);//	���岻������
		setIconifiable(true);//	�����ͼ�껯
		setClosable(true);//	����ɹر�
		setLayout(new FlowLayout());
		add(getPathField());
		add(getBackupButton());
	}

	/**
	 * @return pathField
	 */
	public JTextField getPathField() {
		if(pathField == null) {
			pathField = new JTextField(110);
			pathField.setEditable(false);//	��Ϊ���ɱ༭
		}
		return pathField;
	}

	/**
	 * @return backupButton
	 */
	public JButton getBackupButton() {
		if(backupButton == null) {
			backupButton = new JButton("����(B)", new ImageIcon(
					getClass().getResource("/res/icon/backup.png")));
			backupButton.setMnemonic(KeyEvent.VK_B);
			backupButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String filePath = null;
					try {
						filePath = SqlOpr.backup();
					} catch (SQLException exc) {
						exc.printStackTrace();
					}
					getPathField().setText("����·����" + filePath);
					JOptionPane.showMessageDialog(BackupIFrame.this, 
							"���ݿⱸ�ݳɹ�", "�����ɹ�", 
							JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return backupButton;
	}
}
