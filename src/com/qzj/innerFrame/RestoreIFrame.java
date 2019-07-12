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
 * 	���ݿ�ָ��ڲ�����
 * @author qinzijun
 *
 */
public class RestoreIFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 	�ָ�·���ı���
	 */
	private JTextField pathField = null;
	
	/**
	 * 	�ָ���ť
	 */
	private JButton restoreButton = null;
	
	/**
	 * 	�ָ�·����ť
	 */
	private JButton pathButton = null;
	
	public RestoreIFrame() {
		setTitle("���ݿ�ָ�");
		setSize(700, 90);
		setMaximizable(false);//	���岻�����
		setResizable(false);//	���岻������
		setIconifiable(true);//	�����ͼ�껯
		setClosable(true);//	����ɹر�
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
			restoreButton = new JButton("�ָ�(R)", new ImageIcon(
					getClass().getResource("/res/icon/restore.png")));
			restoreButton.setMnemonic(KeyEvent.VK_R);
			restoreButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String path = pathField.getText();
					if(path == null || path.isEmpty()) {
						JOptionPane.showMessageDialog(RestoreIFrame.this, 
								"��ѡ�����ݿ�ָ��ļ�", "����ʧ��", 
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					SqlOpr.restore(path.substring(14));
					JOptionPane.showMessageDialog(
							RestoreIFrame.this, "���ݿ�ָ��ɹ�", 
							"�����ɹ�", JOptionPane.INFORMATION_MESSAGE);
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
			pathButton = new JButton("ѡ�����ݿ�ָ��ļ�(C)", new ImageIcon(
					getClass().getResource("/res/icon/file.png")));
			pathButton.setMnemonic(KeyEvent.VK_C);
			pathButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//	ʵ�����ļ�ѡ����������backup�ļ�����Ϊ��ǰĿ¼·��
					JFileChooser fileChooser = new JFileChooser("./backup/");
					if(fileChooser.showOpenDialog(RestoreIFrame.this) 
							== JFileChooser.APPROVE_OPTION)
						getPathField().setText("��ѡ������ݿ�ָ��ļ�·����" + 
							fileChooser.getSelectedFile().getAbsolutePath());
				}
			});
		}
		return pathButton;
	}
}
