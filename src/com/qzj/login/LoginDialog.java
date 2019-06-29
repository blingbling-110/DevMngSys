package com.qzj.login;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LoginDialog extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//	�������Ϊ˽�г�Ա����������Ӧ˽�з�����ʵ����
	private LoginPanel loginPanel = null;
	private JLabel userIdLabel = null;
	private JTextField userIdField = null;
	private JLabel pwdLabel = null;
	private JPasswordField pwdField = null;
	private JButton loginButton = null;
	
	public LoginDialog() {
		try {
			//	�����񱾵ػ�
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initialize() {
		setTitle("��¼��ҵ�豸����ϵͳ");
		setIconImage(new ImageIcon(getClass().getResource(
				"/res/root.jpg")).getImage());//	���ô���ͼ��
		setSize(854, 480);
		setLocationRelativeTo(null);//	ʹ������Ļ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);//	�̶������С
		setContentPane(getLoginPanel());//	���õ�¼���
	}

	private LoginPanel getLoginPanel() {
		// TODO �Զ����ɵķ������
		return loginPanel;
	}
}
