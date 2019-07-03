package com.qzj.login;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.qzj.MainFrame;
import com.qzj.sqlOpr.SqlOpr;

/**
 * 	��¼�Ի���
 * @author qinzijun
 *
 */
public class LoginDialog extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//	�������Ϊ˽�г�Ա����������Ӧ˽�з�����ʵ����
	/**
	 * 	��¼���
	 */
	private LoginPanel loginPanel = null;
	
	/**
	 * 	�û�����ǩ
	 */
	private JLabel userIdLabel = null;
	
	/**
	 * 	�û����ı���
	 */
	private JTextField userIdField = null;
	
	/**
	 * 	�����ǩ
	 */
	private JLabel pwdLabel = null;
	
	/**
	 * 	�����
	 */
	private JPasswordField pwdField = null;
	
	/**
	 * 	��¼��ť
	 */
	private JButton loginButton = null;
	
	/**
	 * 	������
	 */
	private MainFrame mainFrame;
	
	public LoginDialog() {
		try {
			/*
			 * 	�����񱾵ػ�
			 * 
			 * 	UIManager����ǰ��ۺ͸й�
			 * 	��ۺ͸йۿ���ͨ�����ֲ�ͬ�ķ�ʽָ����
			 * 	1.ͨ��Ϊ���ָ�������ȫ�޶����ƣ�
			 * 	2.��ͨ������һ��LookAndFeel��ʵ��LookAndFeel
			 * 		�����䴫�ݸ�setLookAndFeel��
			 * 
			 * 	�˴�Ϊ��ʽ2��������Ϊϵͳ��ۡ�
			 */
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			initialize();
			mainFrame = new MainFrame();//	ʵ����������
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 	�����ʼ��
	 */
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

	/**
	 * 	��ȡ��¼���
	 * @return ��¼���
	 */
	private LoginPanel getLoginPanel() {
		if(loginPanel == null) {
			loginPanel = new LoginPanel();
			loginPanel.setLayout(null);//	���þ��Բ���
			userIdLabel = new JLabel("�û�����");
			userIdLabel.setForeground(Color.WHITE);
			userIdLabel.setBounds(320, 150, 60, 20);
			pwdLabel = new JLabel("��  �룺");
			pwdLabel.setForeground(Color.WHITE);
			pwdLabel.setBounds(320, 190, 60, 20);
			loginPanel.add(userIdLabel);
			loginPanel.add(getUserIdField());
			loginPanel.add(pwdLabel);
			loginPanel.add(getPwdField());
			loginPanel.add(getLoginButton());
		}
		return loginPanel;
	}

	/**
	 * 	��ȡ�û����ı���
	 * @return �û����ı���
	 */
	private JTextField getUserIdField() {
		if(userIdField == null) {
			userIdField = new JTextField();
			userIdField.setBounds(400, 150, 127, 22);
		}
		return userIdField;
	}

	/**
	 * 	��ȡ�����
	 * @return �����
	 */
	private JPasswordField getPwdField() {
		if(pwdField == null) {
			pwdField = new JPasswordField();
			pwdField.setBounds(400, 190, 127, 22);
			pwdField.addKeyListener(new KeyAdapter() {//	���Ӽ����¼�����
				public void keyTyped(KeyEvent e) {
					if(e.getKeyChar() == '\n')//	�жϰ����Ƿ��ǻس�
						loginButton.doClick();//	��¼��ť�ĵ���¼�
				}
			});
		}
		return pwdField;
	}

	/**
	 * 	��ȡ��¼��ť
	 * @return ��¼��ť
	 */
	private JButton getLoginButton() {
		if(loginButton == null) {
			loginButton = new JButton(new ImageIcon(
					getClass().getResource("/res/loginButton.png")));
			loginButton.setContentAreaFilled(false);//	��������������
			loginButton.setBounds(370, 240, 102, 39);
			loginButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String userId = userIdField.getText();
					//������getPassword()���������ַ�����
					String pwd = new String(pwdField.getPassword());
					String userName = null;
					try {
						if(!SqlOpr.checkLogin(userId, pwd)) {//	��֤�û���������
							JOptionPane.showMessageDialog(LoginDialog.this, 
									"�û������������", "��¼ʧ��", 
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						userName = SqlOpr.getUserNameFromUserId(userId);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					setVisible(false);//	���õ�¼���岻�ɼ�
					mainFrame.setVisible(true);//	����������ɼ�
					mainFrame.setTitle("��ҵ�豸����ϵͳ����" + userName);
				}
			});
		}
		return loginButton;
	}
	
	/**
	 * 	���ڱ�д���������ľ�̬�ڲ���
	 * @author qinzijun
	 *
	 */
	static class Test {
		public static void main(String[] args) {
			new LoginDialog().setVisible(true);
		}
	}
}
