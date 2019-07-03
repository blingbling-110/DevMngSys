package com.qzj;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	
	public MainFrame() {
		setTitle("��ҵ�豸����ϵͳ");
		setIconImage(new ImageIcon(getClass().getResource(
				"/res/root.jpg")).getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);//	ʹ�������
		setJMenuBar(getMainMenuBar());
		setContentPane(getMainContentPane());
	}

	/**
	 * 	��ȡ������˵���
	 * @return mainMenuBar
	 */
	public MenuBar getMainMenuBar() {
		if(mainMenuBar == null) {
			mainMenuBar = new MenuBar();
		}
		return mainMenuBar;
	}
	
	/**
	 * 	��ȡ�����幤����
	 * @return mainToolBar
	 */
	public ToolBar getMainToolBar() {
		if(mainToolBar == null) {
			mainToolBar = new ToolBar(getMainMenuBar());
			//	���ù��ͼ��
			mainToolBar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		return mainToolBar;
	}

	/**
	 * 	��ȡ�������������
	 * @return mainContentPane
	 */
	public JPanel getMainContentPane() {
		if(mainContentPane == null) {
			mainContentPane = new JPanel(new BorderLayout());
			mainContentPane.add(getMainToolBar(), BorderLayout.NORTH);
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
			GridBagConstraints gbc0 = new GridBagConstraints();
			gbc0.gridx = 0;
			gbc0.gridy = 0;
			gbc0.fill = GridBagConstraints.HORIZONTAL;
			gbc0.weightx = 1.0;
			statePane.add(getStateLabel(), gbc0);
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
	 * 	���ڱ�д���������ľ�̬�ڲ���
	 * @author blingbling_inwin
	 *
	 */
	static class Test {
		public static void main(String[] args) {
			try {
				//	�����񱾵ػ�
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				new MainFrame().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
