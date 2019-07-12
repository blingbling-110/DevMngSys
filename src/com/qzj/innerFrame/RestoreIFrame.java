package com.qzj.innerFrame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.qzj.sqlOpr.SqlOpr;

/**
 * 	数据库恢复内部窗体
 * @author qinzijun
 *
 */
public class RestoreIFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 	恢复路径文本框
	 */
	private JTextField pathField = null;
	
	/**
	 * 	恢复按钮
	 */
	private JButton restoreButton = null;
	
	/**
	 * 	恢复路径按钮
	 */
	private JButton pathButton = null;
	
	public RestoreIFrame() {
		setTitle("数据库恢复");
		setSize(700, 90);
		setMaximizable(false);//	窗体不可最大化
		setResizable(false);//	窗体不可拉伸
		setIconifiable(true);//	窗体可图标化
		setClosable(true);//	窗体可关闭
		setLayout(new FlowLayout());
		add(getPathField());
		add(getPathButton());
		add(getRestoreButton());
	}

	/**
	 * @return pathField
	 */
	public JTextField getPathField() {
		if(pathField == null) {
			pathField = new JTextField(110);
			pathField.setEditable(false);
		}
		return pathField;
	}

	/**
	 * @return restoreButton
	 */
	public JButton getRestoreButton() {
		if(restoreButton == null) {
			restoreButton = new JButton("恢复(R)", new ImageIcon(
					getClass().getResource("/res/icon/restore.png")));
			restoreButton.setMnemonic(KeyEvent.VK_R);
			restoreButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String path = pathField.getText();
					if(path == null || path.isEmpty()) {
						JOptionPane.showMessageDialog(RestoreIFrame.this, 
								"请选择数据库恢复文件", "操作失败", 
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					SqlOpr.restore(path.substring(14));
					JOptionPane.showMessageDialog(
							RestoreIFrame.this, "数据库恢复成功", 
							"操作成功", JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return restoreButton;
	}

	/**
	 * @return pathButton
	 */
	public JButton getPathButton() {
		if(pathButton == null) {
			pathButton = new JButton("选择数据库恢复文件(C)", new ImageIcon(
					getClass().getResource("/res/icon/file.png")));
			pathButton.setMnemonic(KeyEvent.VK_C);
			pathButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//	实例化文件选择器，并以backup文件夹作为当前目录路径
					JFileChooser fileChooser = new JFileChooser("./backup/");
					if(fileChooser.showOpenDialog(RestoreIFrame.this) 
							== JFileChooser.APPROVE_OPTION)
						getPathField().setText("已选择的数据库恢复文件路径：" + 
							fileChooser.getSelectedFile().getAbsolutePath());
				}
			});
		}
		return pathButton;
	}
}
