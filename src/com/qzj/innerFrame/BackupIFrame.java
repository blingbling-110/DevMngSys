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
 * 	数据库备份内部窗体
 * @author qinzijun
 *
 */
public class BackupIFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 	备份路径文本框
	 */
	private JTextField pathField = null;
	
	/**
	 * 	备份按钮
	 */
	private JButton backupButton = null;
	
	public BackupIFrame() {
		setTitle("数据库备份");
		setSize(800, 65);
		setMaximizable(false);//	窗体不可最大化
		setResizable(false);//	窗体不可拉伸
		setIconifiable(true);//	窗体可图标化
		setClosable(true);//	窗体可关闭
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
			pathField.setEditable(false);//	设为不可编辑
		}
		return pathField;
	}

	/**
	 * @return backupButton
	 */
	public JButton getBackupButton() {
		if(backupButton == null) {
			backupButton = new JButton("备份(B)", new ImageIcon(
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
					getPathField().setText("备份路径：" + filePath);
					JOptionPane.showMessageDialog(BackupIFrame.this, 
							"数据库备份成功", "操作成功", 
							JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return backupButton;
	}
}
