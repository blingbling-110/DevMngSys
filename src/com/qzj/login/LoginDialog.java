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

	//	将组件作为私有成员变量并在相应私有方法中实例化
	private LoginPanel loginPanel = null;
	private JLabel userIdLabel = null;
	private JTextField userIdField = null;
	private JLabel pwdLabel = null;
	private JPasswordField pwdField = null;
	private JButton loginButton = null;
	
	public LoginDialog() {
		try {
			//	窗体风格本地化
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initialize() {
		setTitle("登录企业设备管理系统");
		setIconImage(new ImageIcon(getClass().getResource(
				"/res/root.jpg")).getImage());//	设置窗体图标
		setSize(854, 480);
		setLocationRelativeTo(null);//	使窗体屏幕居中
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);//	固定窗体大小
		setContentPane(getLoginPanel());//	设置登录面板
	}

	private LoginPanel getLoginPanel() {
		// TODO 自动生成的方法存根
		return loginPanel;
	}
}
