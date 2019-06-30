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

	//	将组件作为私有成员变量并在相应私有方法中实例化
	private LoginPanel loginPanel = null;//	登录面板
	private JLabel userIdLabel = null;//	用户名标签
	private JTextField userIdField = null;//	用户名文本框
	private JLabel pwdLabel = null;//	密码标签
	private JPasswordField pwdField = null;//	密码框
	private JButton loginButton = null;//	登录按钮
	
	public LoginDialog() {
		try {
			//	窗体风格本地化
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initialize() {//	窗体初始化
		setTitle("登录企业设备管理系统");
		setIconImage(new ImageIcon(getClass().getResource(
				"/res/root.jpg")).getImage());//	设置窗体图标
		setSize(854, 480);
		setLocationRelativeTo(null);//	使窗体屏幕居中
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);//	固定窗体大小
		setContentPane(getLoginPanel());//	设置登录面板
	}

	private LoginPanel getLoginPanel() {//	获取登录面板
		if(loginPanel == null) {
			loginPanel = new LoginPanel();
			loginPanel.setLayout(null);//	设置绝对布局
			userIdLabel = new JLabel("用户名：");
			userIdLabel.setBounds(320, 150, 60, 20);
			pwdLabel = new JLabel("密  码：");
			pwdLabel.setBounds(320, 190, 60, 20);
			loginPanel.add(userIdLabel);
			loginPanel.add(getUserIdField());
			loginPanel.add(pwdLabel);
			loginPanel.add(getPwdField());
			loginPanel.add(getLoginButton());
		}
		return loginPanel;
	}

	private JTextField getUserIdField() {//	获取用户名文本框
		if(userIdField == null) {
			userIdField = new JTextField();
			userIdField.setBounds(400, 150, 127, 22);
		}
		return userIdField;
	}

	private JPasswordField getPwdField() {//	获取密码框
		if(pwdField == null) {
			pwdField = new JPasswordField();
			pwdField.setBounds(400, 190, 127, 22);
			pwdField.addKeyListener(new KeyAdapter() {//	增加键盘事件监听
				public void keyTyped(KeyEvent e) {
					if(e.getKeyChar() == '\n')//	判断按键是否是回车
						loginButton.doClick();//	程序化执行按下登录按钮
				}
			});
		}
		return pwdField;
	}

	private JButton getLoginButton() {//	获取登录按钮
		if(loginButton == null) {
			loginButton = new JButton(new ImageIcon(
					getClass().getResource("/res/loginButton.jpg")));
			loginButton.setBounds(360, 240, 102, 32);
			loginButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					
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
