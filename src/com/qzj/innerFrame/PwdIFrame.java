package com.qzj.innerFrame;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.qzj.MainFrame;
import com.qzj.sqlOpr.SqlOpr;

/**
 * 	修改密码内部窗体
 * @author blingbling_inwin
 *
 */
public class PwdIFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 	原密码标签
	 */
	private JLabel oriLabel = new JLabel("原密码：");
	
	/**
	 * 	原密码框
	 */
	private JPasswordField oriField = new JPasswordField();
	
	/**
	 * 	新密码标签
	 */
	private JLabel newLabel = new JLabel("新密码：");
	
	/**
	 * 	新密码框
	 */
	private JPasswordField newField = new JPasswordField();
	
	/**
	 * 	确认密码标签
	 */
	private JLabel conLabel = new JLabel("确认密码：");
	
	/**
	 * 	确认密码框
	 */
	private JPasswordField conField = new JPasswordField();
	
	/**
	 * 	确认按钮
	 */
	private JButton yesButton = null;
	
	/**
	 * 	取消按钮
	 */
	private JButton noButton = null;
	
	/**
	 * 	修改密码内部窗体构造方法
	 * @param userId 登录用户名
	 */
	public PwdIFrame() {
		setTitle("修改密码");
		setSize(384, 216);
		setMaximizable(false);//	窗体不可最大化
		setResizable(false);//	窗体不可拉伸
		setIconifiable(true);//	窗体可图标化
		setClosable(true);//	窗体可关闭
		oriLabel.setBounds(40, 20, 80, 20);
		oriField.setBounds(110, 20, 200, 20);
		newLabel.setBounds(40, 60, 80, 20);
		newField.setBounds(110, 60, 200, 20);
		conLabel.setBounds(40, 100, 80, 20);
		conField.setBounds(110, 100, 200, 20);
		getYesButton().setBounds(70, 140, 100, 30);
		getNoButton().setBounds(200, 140, 100, 30);
		Container c = getContentPane();
		c.setLayout(null);
		c.add(oriLabel);
		c.add(oriField);
		c.add(newLabel);
		c.add(newField);
		c.add(conLabel);
		c.add(conField);
		c.add(getYesButton());
		c.add(getNoButton());
	}

	/**
	 * @return yesButton
	 */
	public JButton getYesButton() {
		if(yesButton == null) {
			yesButton = new JButton("确认(Y)", new ImageIcon(
					getClass().getResource("/res/icon/yes.png")));
			yesButton.setMnemonic(KeyEvent.VK_Y);
			yesButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String oriPwd = new String(oriField.getPassword());
					String newPwd = new String(newField.getPassword());
					String conPwd = new String(conField.getPassword());
					if(oriPwd == null || oriPwd.isEmpty() 
							|| newPwd == null || newPwd.isEmpty()
							|| conPwd == null || conPwd.isEmpty()) {
						JOptionPane.showMessageDialog(PwdIFrame.this, 
								"密码不能为空", "输入错误", 
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(newPwd.equals(conPwd)) {
						boolean res = SqlOpr.changePwd(
								MainFrame.userId, oriPwd, newPwd);
						if(res) {
							JOptionPane.showMessageDialog(PwdIFrame.this, 
									"密码已修改", "操作成功", 
									JOptionPane.INFORMATION_MESSAGE);
							PwdIFrame.this.dispose();
						}
						else
							JOptionPane.showMessageDialog(PwdIFrame.this, 
									"原密码输入错误", "操作失败", 
									JOptionPane.ERROR_MESSAGE);
					}else
						JOptionPane.showMessageDialog(PwdIFrame.this, 
								"确认密码与新密码不一致", "输入错误", 
								JOptionPane.ERROR_MESSAGE);
				}
			});
		}
		return yesButton;
	}

	/**
	 * @return noButton
	 */
	public JButton getNoButton() {
		if(noButton == null) {
			noButton = new JButton("取消(N)", new ImageIcon(
					getClass().getResource("/res/icon/no.png")));
			noButton.setMnemonic(KeyEvent.VK_N);
			noButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					PwdIFrame.this.dispose();
				}
			});
		}
		return noButton;
	}
}
