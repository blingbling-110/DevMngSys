package com.qzj.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class LoginDialog extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//	�������Ϊ˽�г�Ա����������Ӧ˽�з�����ʵ����
	private LoginPanel loginPanel = null;//	��¼���
	private JLabel userIdLabel = null;//	�û�����ǩ
	private JTextField userIdField = null;//	�û����ı���
	private JLabel pwdLabel = null;//	�����ǩ
	private JPasswordField pwdField = null;//	�����
	private JButton loginButton = null;//	��¼��ť
	
	public LoginDialog() {
		try {
			//	�����񱾵ػ�
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initialize() {//	�����ʼ��
		setTitle("��¼��ҵ�豸����ϵͳ");
		setIconImage(new ImageIcon(getClass().getResource(
				"/res/root.jpg")).getImage());//	���ô���ͼ��
		setSize(854, 480);
		setLocationRelativeTo(null);//	ʹ������Ļ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);//	�̶������С
		setContentPane(getLoginPanel());//	���õ�¼���
	}

	private LoginPanel getLoginPanel() {//	��ȡ��¼���
		if(loginPanel == null) {
			loginPanel = new LoginPanel();
			loginPanel.setLayout(null);//	���þ��Բ���
			userIdLabel = new JLabel("�û�����");
			userIdLabel.setBounds(320, 150, 60, 20);
			pwdLabel = new JLabel("��  �룺");
			pwdLabel.setBounds(320, 190, 60, 20);
			loginPanel.add(userIdLabel);
			loginPanel.add(getUserIdField());
			loginPanel.add(pwdLabel);
			loginPanel.add(getPwdField());
			loginPanel.add(getLoginButton());
		}
		return loginPanel;
	}

	private JTextField getUserIdField() {//	��ȡ�û����ı���
		if(userIdField == null) {
			userIdField = new JTextField();
			userIdField.setBounds(400, 150, 127, 22);
		}
		return userIdField;
	}

	private JPasswordField getPwdField() {//	��ȡ�����
		if(pwdField == null) {
			pwdField = new JPasswordField();
			pwdField.setBounds(400, 190, 127, 22);
			pwdField.addKeyListener(new KeyAdapter() {//	���Ӽ����¼�����
				public void keyTyped(KeyEvent e) {
					if(e.getKeyChar() == '\n')//	�жϰ����Ƿ��ǻس�
						loginButton.doClick();//	����ִ�а��µ�¼��ť
				}
			});
		}
		return pwdField;
	}

	private JButton getLoginButton() {//	��ȡ��¼��ť
		if(loginButton == null) {
			loginButton = new JButton(new ImageIcon(
					getClass().getResource("/res/loginButton.jpg")));
			loginButton.setBounds(360, 240, 102, 32);
			loginButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO �Զ����ɵķ������
					
				}
			});
		}
		return loginButton;
	}
	
	static class Test {
		public static void main(String[] args) {
			new LoginDialog().setVisible(true);
		}
	}
}
