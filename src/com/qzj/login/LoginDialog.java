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
 * 	登录对话框
 * @author qinzijun
 *
 */
public class LoginDialog extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//	将组件作为私有成员变量并在相应私有方法中实例化
	/**
	 * 	登录面板
	 */
	private LoginPanel loginPanel = null;
	
	/**
	 * 	用户名标签
	 */
	private JLabel userIdLabel = null;
	
	/**
	 * 	用户名文本框
	 */
	private JTextField userIdField = null;
	
	/**
	 * 	密码标签
	 */
	private JLabel pwdLabel = null;
	
	/**
	 * 	密码框
	 */
	private JPasswordField pwdField = null;
	
	/**
	 * 	登录按钮
	 */
	private JButton loginButton = null;
	
	/**
	 * 	主窗体
	 */
	private MainFrame mainFrame;
	
	public LoginDialog() {
		try {
			/*
			 * 	窗体风格本地化
			 * 
			 * 	UIManager管理当前外观和感观
			 * 	外观和感观可以通过两种不同的方式指定：
			 * 	1.通过为外观指定类的完全限定名称；
			 * 	2.或通过创建一个LookAndFeel的实例LookAndFeel
			 * 		并将其传递给setLookAndFeel。
			 * 
			 * 	此处为方式2，即设置为系统外观。
			 */
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			initialize();
			mainFrame = new MainFrame();//	实例化主窗体
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 	窗体初始化
	 */
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

	/**
	 * 	获取登录面板
	 * @return 登录面板
	 */
	private LoginPanel getLoginPanel() {
		if(loginPanel == null) {
			loginPanel = new LoginPanel();
			loginPanel.setLayout(null);//	设置绝对布局
			userIdLabel = new JLabel("用户名：");
			userIdLabel.setForeground(Color.WHITE);
			userIdLabel.setBounds(320, 150, 60, 20);
			pwdLabel = new JLabel("密  码：");
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
	 * 	获取用户名文本框
	 * @return 用户名文本框
	 */
	private JTextField getUserIdField() {
		if(userIdField == null) {
			userIdField = new JTextField();
			userIdField.setBounds(400, 150, 127, 22);
		}
		return userIdField;
	}

	/**
	 * 	获取密码框
	 * @return 密码框
	 */
	private JPasswordField getPwdField() {
		if(pwdField == null) {
			pwdField = new JPasswordField();
			pwdField.setBounds(400, 190, 127, 22);
			pwdField.addKeyListener(new KeyAdapter() {//	增加键盘事件监听
				public void keyTyped(KeyEvent e) {
					if(e.getKeyChar() == '\n')//	判断按键是否是回车
						loginButton.doClick();//	登录按钮的点击事件
				}
			});
		}
		return pwdField;
	}

	/**
	 * 	获取登录按钮
	 * @return 登录按钮
	 */
	private JButton getLoginButton() {
		if(loginButton == null) {
			loginButton = new JButton(new ImageIcon(
					getClass().getResource("/res/loginButton.png")));
			loginButton.setContentAreaFilled(false);//	不绘制内容区域
			loginButton.setBounds(370, 240, 102, 39);
			loginButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String userId = userIdField.getText();
					//密码框的getPassword()方法返回字符数组
					String pwd = new String(pwdField.getPassword());
					String userName = null;
					try {
						if(!SqlOpr.checkLogin(userId, pwd)) {//	验证用户名、密码
							JOptionPane.showMessageDialog(LoginDialog.this, 
									"用户名或密码错误", "登录失败", 
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						userName = SqlOpr.getUserNameFromUserId(userId);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					setVisible(false);//	设置登录窗体不可见
					mainFrame.setVisible(true);//	设置主窗体可见
					mainFrame.setTitle("企业设备管理系统——" + userName);
				}
			});
		}
		return loginButton;
	}
	
	/**
	 * 	用于编写测试用例的静态内部类
	 * @author qinzijun
	 *
	 */
	static class Test {
		public static void main(String[] args) {
			new LoginDialog().setVisible(true);
		}
	}
}
