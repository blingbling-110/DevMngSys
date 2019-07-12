package com.qzj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import com.qzj.innerFrame.DevSumIFrame;
import com.qzj.innerFrame.UserSumIFrame;

/**
 * 	������˵���
 * @author qinzijun
 *
 */
public class MenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 	�ļ��˵�
	 */
	private JMenu fileMenu = null;
	
	/**
	 * 	�˳��˵���
	 */
	private JMenuItem exitItem = null;
	
	/**
	 * 	����˵�
	 */
	private JMenu manageMenu = null;
	
	/**
	 * 	�豸�����˵���
	 */
	private JMenuItem devSumItem = null;
	
	/**
	 * 	��Ա�����˵���
	 */
	private JMenuItem userSumItem = null;
	
	/**
	 * 	�ڲ������Map����
	 */
	private Map<JMenuItem, JInternalFrame> innerFrames = null;
	
	/**
	 * 	�������
	 */
	private DesktopPane desktopPane = null;
	
	/**
	 * 	״̬��ǩ
	 */
	private JLabel stateLabel = null;
	
	public MenuBar(DesktopPane desktopPane, 
			JLabel stateLabel, boolean isAdmin) {
		this.desktopPane = desktopPane;
		this.stateLabel = stateLabel;
		innerFrames = new HashMap<>();
		add(getFileMenu(isAdmin));
		add(getMangeMenu(isAdmin));
	}
	
	/**
	 * 	��ȡ�ļ��˵�
	 * @return �ļ��˵�
	 */
	public JMenu getFileMenu(boolean isAdmin) {
		if(fileMenu == null) {
			fileMenu = new JMenu("�ļ�(F)");
			fileMenu.setMnemonic(KeyEvent.VK_F);//	���ÿ�ݼ�
			fileMenu.add(getExitItem());
		}
		return fileMenu;
	}

	/**
	 * 	��ȡ�˳��˵���
	 * @return exitItem
	 */
	public JMenuItem getExitItem() {
		if(exitItem == null) {
			exitItem = new JMenuItem("�˳�(X)", new ImageIcon(
					getClass().getResource("/res/icon/exit.png")));
			exitItem.setMnemonic(KeyEvent.VK_X);
			exitItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return exitItem;
	}

	/**
	 * 	��ȡ�豸�˵�
	 * @return manageMenu
	 */
	public JMenu getMangeMenu(boolean isAdmin) {
		if(manageMenu == null) {
			manageMenu = new JMenu("����(S)");
			manageMenu.setMnemonic(KeyEvent.VK_S);
			manageMenu.add(getDevSumItem(isAdmin));
			if(isAdmin)
				manageMenu.add(getUserSumItem());
		}
		return manageMenu;
	}

	/**
	 * 	��ȡ�豸�����˵�
	 * @return devSumItem
	 */
	public JMenuItem getDevSumItem(boolean isAdmin) {
		if(devSumItem == null) {
			devSumItem = new JMenuItem("�豸����(D)", new ImageIcon(
					getClass().getResource("/res/icon/devSum.png")));
			devSumItem.setMnemonic(KeyEvent.VK_D);
			devSumItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					showInnerFrame(devSumItem, DevSumIFrame.class, isAdmin);
				}
			});
		}
		return devSumItem;
	}

	/**
	 * @return userSumItem
	 */
	public JMenuItem getUserSumItem() {
		if(userSumItem == null) {
			userSumItem = new JMenuItem("��Ա����(U)", new ImageIcon(
					getClass().getResource("/res/icon/userSum.png")));
			userSumItem.setMnemonic(KeyEvent.VK_U);
			userSumItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					showInnerFrame(userSumItem, UserSumIFrame.class, true);
				}
			});
		}
		return userSumItem;
	}

	/**
	 * 	��ʾ�ڲ����壬���÷���ʵ�����ڲ��������
	 * @param jMenuItem �ڲ��������Դ�˵���
	 * @param jInternalFrame �ڲ�����
	 */
	protected void showInnerFrame(JMenuItem jMenuItem, 
			Class<? extends JInternalFrame> innerFrameC, boolean isAdmin) {
		JInternalFrame innerFrame = innerFrames.get(jMenuItem);
		try {
			if(innerFrame == null || innerFrame.isClosed()) {
				//	���÷�������ڲ����幹�췽����newInstance()����
				innerFrame = innerFrameC.getConstructor(
						boolean.class).newInstance(isAdmin);
				innerFrames.put(jMenuItem, innerFrame);
				desktopPane.add(innerFrame);
				innerFrame.setFrameIcon(jMenuItem.getIcon());
				//	�����ʼλ��
				innerFrame.setLocation(
						(int)(Math.random() * (desktopPane.getWidth() - 1032)), 
						(int)(Math.random() * (desktopPane.getHeight() - 432)));
				innerFrame.setVisible(true);
			}
			innerFrame.setSelected(true);//	ѡ���ڲ�����
		} catch (Exception e) {
			e.printStackTrace();
		}
		stateLabel.setText(innerFrame.getTitle());//	״̬����ʾ
		//	����ڲ������¼�������
		innerFrame.addInternalFrameListener(new InternalFrameAdapter() {
			//	���ڲ����崦�ڼ���״̬ʱ����
			public void internalFrameActivated(InternalFrameEvent e) {
				JInternalFrame jInternalFrame = e.getInternalFrame();
				stateLabel.setText(jInternalFrame.getTitle());
			}
			//	���ڲ����崦�ڷǼ���״̬ʱ����
			public void internalFrameDeactivated(InternalFrameEvent e) {
				stateLabel.setText("��ǰû��ѡ������");
			}
		});
	}
}
