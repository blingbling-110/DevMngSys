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
 * 	�޸������ڲ�����
 * @author blingbling_inwin
 *
 */
public class PwdIFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 	ԭ�����ǩ
	 */
	private JLabel oriLabel = new JLabel("ԭ���룺");
	
	/**
	 * 	ԭ�����
	 */
	private JPasswordField oriField = new JPasswordField();
	
	/**
	 * 	�������ǩ
	 */
	private JLabel newLabel = new JLabel("�����룺");
	
	/**
	 * 	�������
	 */
	private JPasswordField newField = new JPasswordField();
	
	/**
	 * 	ȷ�������ǩ
	 */
	private JLabel conLabel = new JLabel("ȷ�����룺");
	
	/**
	 * 	ȷ�������
	 */
	private JPasswordField conField = new JPasswordField();
	
	/**
	 * 	ȷ�ϰ�ť
	 */
	private JButton yesButton = null;
	
	/**
	 * 	ȡ����ť
	 */
	private JButton noButton = null;
	
	/**
	 * 	�޸������ڲ����幹�췽��
	 * @param userId ��¼�û���
	 */
	public PwdIFrame() {
		setTitle("�޸�����");
		setSize(384, 216);
		setMaximizable(false);//	���岻�����
		setResizable(false);//	���岻������
		setIconifiable(true);//	�����ͼ�껯
		setClosable(true);//	����ɹر�
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
			yesButton = new JButton("ȷ��(Y)", new ImageIcon(
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
								"���벻��Ϊ��", "�������", 
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(newPwd.equals(conPwd)) {
						boolean res = SqlOpr.changePwd(
								MainFrame.userId, oriPwd, newPwd);
						if(res) {
							JOptionPane.showMessageDialog(PwdIFrame.this, 
									"�������޸�", "�����ɹ�", 
									JOptionPane.INFORMATION_MESSAGE);
							PwdIFrame.this.dispose();
						}
						else
							JOptionPane.showMessageDialog(PwdIFrame.this, 
									"ԭ�����������", "����ʧ��", 
									JOptionPane.ERROR_MESSAGE);
					}else
						JOptionPane.showMessageDialog(PwdIFrame.this, 
								"ȷ�������������벻һ��", "�������", 
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
			noButton = new JButton("ȡ��(N)", new ImageIcon(
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
