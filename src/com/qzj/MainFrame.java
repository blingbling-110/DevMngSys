package com.qzj;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

/**
 * 	��ҵ�豸����ϵͳ������
 * @author qinzijun
 *
 */
public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 	������˵���
	 */
	private MenuBar mainMenuBar = null;
	
	/**
	 * 	�����幤����
	 */
	private ToolBar mainToolBar = null;
	
	/**
	 * 	�������������
	 */
	private JPanel mainContentPane = null;
	
	/**
	 * 	�������
	 */
	private DesktopPane desktopPane = null;
	
	/**
	 * 	״̬���
	 */
	private JPanel statePane = null;
	
	/**
	 * 	״̬��ǩ
	 */
	private JLabel stateLabel = null;
	
	/**
	 * 	�ָ���
	 */
	private JSeparator separator = null;
	
	/**
	 * 	�û���ǩ
	 */
	private JLabel userLabel = null;
	
	/**
	 * 	�����幹�췽��
	 * @param isAdmin �Ƿ�ӵ�й���Ȩ��
	 */
	public MainFrame(boolean isAdmin) {
		setTitle("��ҵ�豸����ϵͳ������������Ա");
		setIconImage(new ImageIcon(getClass().getResource(
				"/res/root.jpg")).getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);//	���ô������
		setJMenuBar(getMainMenuBar(isAdmin));
		setContentPane(getMainContentPane(isAdmin));
	}

	/**
	 * 	��ȡ������˵���
	 * @param isAdmin �Ƿ�ӵ�й���Ȩ��
	 * @return mainMenuBar
	 */
	public MenuBar getMainMenuBar(boolean isAdmin) {
		if(mainMenuBar == null) {
			mainMenuBar = new MenuBar(getDesktopPane(), 
					getStateLabel(), isAdmin);
		}
		return mainMenuBar;
	}
	
	/**
	 * 	��ȡ�����幤����
	 * @param isAdmin �Ƿ�ӵ�й���Ȩ��
	 * @return mainToolBar
	 */
	public ToolBar getMainToolBar(boolean isAdmin) {
		if(mainToolBar == null) {
			mainToolBar = new ToolBar(getMainMenuBar(isAdmin), isAdmin);
			//	���ù��ͼ��
			mainToolBar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		return mainToolBar;
	}

	/**
	 * 	��ȡ�������������
	 * @param isAdmin �Ƿ�ӵ�й���Ȩ��
	 * @return mainContentPane
	 */
	public JPanel getMainContentPane(boolean isAdmin) {
		if(mainContentPane == null) {
			mainContentPane = new JPanel(new BorderLayout());
			mainContentPane.add(getMainToolBar(isAdmin), BorderLayout.NORTH);
			mainContentPane.add(getDesktopPane(), BorderLayout.CENTER);
			mainContentPane.add(getStatePane(), BorderLayout.SOUTH);
		}
		return mainContentPane;
	}

	/**
	 * 	��ȡ�������
	 * @return desktopPane
	 */
	public DesktopPane getDesktopPane() {
		if(desktopPane == null) {
			desktopPane = new DesktopPane();
		}
		return desktopPane;
	}

	/**
	 * 	��ȡ״̬���
	 * @return statePane
	 */
	public JPanel getStatePane() {
		if(statePane == null) {
			statePane = new JPanel();
			/*
			 * 	GridBagLayout�������鲼�ֹ�����
			 * 	�������ʱ��Ҫָ��GridBagConstraintsԼ�����󣬲�����
			 * 
			 * 	gridx/gridy������ĺ������ꣻ
			 * 	gridwidth�������ռ������Ҳ������Ŀ�ȣ�
			 * 	gridheight�������ռ������Ҳ������ĸ߶ȣ�
			 * 	fill�������������ڶ����ܳ������ʱ��
			 * 		ͨ�� fill��ֵ���趨��䷽ʽ�����ĸ�ֵ��
			 * 	ipadx�������ĺ����ࣻ
			 * 	ipady�������������ࣻ
			 * 	insets������������������ʱ��
			 * 		ͨ�� insets��ָ�����ܣ����������ң�������϶��
			 * 	anchor��ͬ���ǵ���������������ʱ��ͨ�� anchor�����������λ�ã�
			 * 		anchor������ֵ�����Ժ���Ե�ֵ�ֱ��� ���ɸ����ĵ����У������в鿴��
			 * 	weightx���е�Ȩ�أ�ͨ�����������������η����е�ʣ��ռ䣻
			 * 	weighty���е�Ȩ�أ�ͨ�����������������η����е�ʣ��ռ䡣
			 */
			statePane.setLayout(new GridBagLayout());
			//����״̬���ı߿�
			statePane.setBorder(BorderFactory.createBevelBorder(
					BevelBorder.RAISED));
			GridBagConstraints stateLabelGbc = new GridBagConstraints();
			stateLabelGbc.gridx = 0;
			stateLabelGbc.gridy = 0;
			stateLabelGbc.fill = GridBagConstraints.HORIZONTAL;
			stateLabelGbc.weightx = 1.0;
			stateLabelGbc.insets = new Insets(0, 8, 0, 8);
			GridBagConstraints separatorGbc = new GridBagConstraints();
			separatorGbc.gridx = 1;
			separatorGbc.gridy = 0;
			separatorGbc.fill = GridBagConstraints.VERTICAL;
			GridBagConstraints userLabelGbc = new GridBagConstraints();
			userLabelGbc.gridx = 2;
			userLabelGbc.gridy = 0;
			userLabelGbc.insets = new Insets(0, 8, 0, 8);
			statePane.add(getStateLabel(), stateLabelGbc);
			statePane.add(getSeparator(), separatorGbc);
			statePane.add(getUserLabel(), userLabelGbc);
		}
		return statePane;
	}

	/**
	 * 	��ȡ״̬��ǩ
	 * @return stateLabel
	 */
	public JLabel getStateLabel() {
		if(stateLabel == null) {
			stateLabel = new JLabel("��ǰû��ѡ������");
		}
		return stateLabel;
	}

	/**
	 * 	��ȡ�ָ���
	 * @return separator
	 */
	public JSeparator getSeparator() {
		if(separator == null) {
			separator = new JSeparator(JSeparator.VERTICAL);
		}
		return separator;
	}

	/**
	 * 	��ȡ�û���ǩ
	 * @return userLabel
	 */
	public JLabel getUserLabel() {
		if(userLabel == null) {
			userLabel = new JLabel("��ǰ��¼�û�����������Ա");
		}
		return userLabel;
	}

	/**
	 * 	���ڱ�д���������ľ�̬�ڲ���
	 * @author blingbling_inwin
	 *
	 */
	static class Test {
		public static void main(String[] args) {
			try {
				//	�����񱾵ػ�
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				new MainFrame(true).setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
