package com.qzj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.qzj.innerFrame.BackupIFrame;
import com.qzj.innerFrame.DevSumIFrame;
import com.qzj.innerFrame.RestoreIFrame;
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
	 * 	���ݿⱸ�ݲ˵���
	 */
	private JMenuItem backupItem = null;
	
	/**
	 * 	���ݿ�ָ��˵���
	 */
	private JMenuItem restoreItem = null;
	
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
			if(isAdmin) {
				fileMenu.add(getBackupItem());
				fileMenu.add(getRestoreItem());
			}
			fileMenu.add(getExitItem());
		}
		return fileMenu;
	}

	/**
	 * 	��ȡ���ݿⱸ�ݲ˵���
	 * @return backupItem
	 */
	public JMenuItem getBackupItem() {
		if(backupItem == null) {
			backupItem = new JMenuItem("���ݿⱸ��(B)", new ImageIcon(
					getClass().getResource("/res/icon/backup.png")));
			backupItem.setMnemonic(KeyEvent.VK_B);
			backupItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					showInnerFrame(backupItem, BackupIFrame.class, true);
				}
			});
		}
		return backupItem;
	}

	/**
	 * 	��ȡ���ݿ�ָ��˵���
	 * @return restoreItem
	 */
	public JMenuItem getRestoreItem() {
		if(restoreItem == null) {
			restoreItem = new JMenuItem("���ݿ�ָ�(R)", new ImageIcon(
					getClass().getResource("/res/icon/restore.png")));
			restoreItem.setMnemonic(KeyEvent.VK_R);
			restoreItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(JOptionPane.showConfirmDialog(null, 
							"��ע�⣺�ڽ������ݿ�ָ�����֮ǰ�뱸�ݵ�ǰ���ݿ⣬����"
							+ "��ɲ���������ݶ�ʧ��\nȷ������Ҫ�������ݿ�ָ�������", 
							"ע������", 
							JOptionPane.OK_CANCEL_OPTION, 
							JOptionPane.WARNING_MESSAGE) == 
							JOptionPane.OK_OPTION)
						showInnerFrame(restoreItem, RestoreIFrame.class, true);
				}
			});
		}
		return restoreItem;
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
		if(innerFrame == null || innerFrame.isClosed()) {
			//	���÷�������ڲ����幹�췽����newInstance()����
			try {
				innerFrame = innerFrameC.getConstructor(
						boolean.class).newInstance(isAdmin);
			} catch (NoSuchMethodException e) {
				try {
					innerFrame = innerFrameC.getConstructor().newInstance();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			innerFrames.put(jMenuItem, innerFrame);
			desktopPane.add(innerFrame);
			innerFrame.setFrameIcon(jMenuItem.getIcon());
			//	�����ʼλ��
			innerFrame.setLocation(
					(int)(Math.random() * (desktopPane.getWidth() - 1032)), 
					(int)(Math.random() * (desktopPane.getHeight() - 432)));
			innerFrame.setVisible(true);
		}
		try {
			innerFrame.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}//	ѡ���ڲ�����
		//	״̬����ʾ
		stateLabel.setText("��ǰѡ���Ĵ��壺" + innerFrame.getTitle());
		//	����ڲ������¼�������
		innerFrame.addInternalFrameListener(new InternalFrameAdapter() {
			//	���ڲ����崦�ڼ���״̬ʱ����
			public void internalFrameActivated(InternalFrameEvent e) {
				JInternalFrame jInternalFrame = e.getInternalFrame();
				stateLabel.setText("��ǰѡ���Ĵ��壺" + jInternalFrame.getTitle());
			}
			//	���ڲ����崦�ڷǼ���״̬ʱ����
			public void internalFrameDeactivated(InternalFrameEvent e) {
				stateLabel.setText("��ǰû��ѡ������");
			}
		});
	}
}
