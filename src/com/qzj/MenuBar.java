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
import com.qzj.innerFrame.BrwIFrame;
import com.qzj.innerFrame.DevIFrame;
import com.qzj.innerFrame.DevSumIFrame;
import com.qzj.innerFrame.PwdIFrame;
import com.qzj.innerFrame.RestoreIFrame;
import com.qzj.innerFrame.RtnIFrame;
import com.qzj.innerFrame.UserIFrame;
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
	 * 	�����˵�
	 */
	private JMenu sumMenu = null;
	
	/**
	 * 	�豸�����˵���
	 */
	private JMenuItem devSumItem = null;
	
	/**
	 * 	��Ա�����˵���
	 */
	private JMenuItem userSumItem = null;
	
	/**
	 * 	����˵�
	 */
	private JMenu manageMenu = null;
	
	/**
	 * 	���ù���˵���
	 */
	private JMenuItem brwItem = null;
	
	/**
	 * 	�黹����˵���
	 */
	private JMenuItem rtnItem = null;
	
	/**
	 * 	�����˵�
	 */
	private JMenu searchMenu = null;
	
	/**
	 * 	�����豸�˵���
	 */
	private JMenuItem devItem = null;
	
	/**
	 * 	������Ա�˵���
	 */
	private JMenuItem userItem = null;
	
	/**
	 * 	�����˵�
	 */
	private JMenu helpMenu = null;
	
	/**
	 * 	�޸�����˵���
	 */
	private JMenuItem pwdItem = null;
	
	/**
	 * 	���ڲ˵���
	 */
	private JMenuItem aboutItem = null;
	
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
	
	/**
	 * 	������˵������췽��
	 * @param desktopPane �������������
	 * @param stateLabel ������״̬��ǩ
	 */
	public MenuBar(DesktopPane desktopPane, JLabel stateLabel) {
		this.desktopPane = desktopPane;
		this.stateLabel = stateLabel;
		innerFrames = new HashMap<>();
		add(getFileMenu());
		add(getSumMenu());
		if(MainFrame.isAdmin)
			add(getManageMenu());
		add(getSearchMenu());
		add(getHelpMenu());
	}
	
	/**
	 * 	��ȡ�ļ��˵�
	 * @return �ļ��˵�
	 */
	public JMenu getFileMenu() {
		if(fileMenu == null) {
			fileMenu = new JMenu("�ļ�(F)");
			fileMenu.setMnemonic(KeyEvent.VK_F);//	���ÿ�ݼ�
			if(MainFrame.isAdmin) {
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
					showInnerFrame(backupItem, BackupIFrame.class);
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
						showInnerFrame(restoreItem, RestoreIFrame.class);
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
	 * 	��ȡ�����˵�
	 * @return sumMenu
	 */
	public JMenu getSumMenu() {
		if(sumMenu == null) {
			sumMenu = new JMenu("����(S)");
			sumMenu.setMnemonic(KeyEvent.VK_S);
			sumMenu.add(getDevSumItem());
			if(MainFrame.isAdmin)
				sumMenu.add(getUserSumItem());
		}
		return sumMenu;
	}

	/**
	 * 	��ȡ�豸�����˵���
	 * @return devSumItem
	 */
	public JMenuItem getDevSumItem() {
		if(devSumItem == null) {
			devSumItem = new JMenuItem("�豸����(D)", new ImageIcon(
					getClass().getResource("/res/icon/devSum.png")));
			devSumItem.setMnemonic(KeyEvent.VK_D);
			devSumItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					showInnerFrame(devSumItem, DevSumIFrame.class);
				}
			});
		}
		return devSumItem;
	}

	/**
	 * 	��ȡ��Ա�����˵���
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
					showInnerFrame(userSumItem, UserSumIFrame.class);
				}
			});
		}
		return userSumItem;
	}

	/**
	 * 	��ȡ����˵�
	 * @return manageMenu
	 */
	public JMenu getManageMenu() {
		if(manageMenu == null) {
			manageMenu = new JMenu("����(M)");
			manageMenu.setMnemonic(KeyEvent.VK_M);
			manageMenu.add(getBrwItem());
			manageMenu.add(getRtnItem());
		}
		return manageMenu;
	}

	/**
	 * 	��ȡ���ù���˵���
	 * @return brwItem
	 */
	public JMenuItem getBrwItem() {
		if(brwItem == null) {
			brwItem = new JMenuItem("���ù���(B)", new ImageIcon(
					getClass().getResource("/res/icon/brw.png")));
			brwItem.setMnemonic(KeyEvent.VK_B);
			brwItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					showInnerFrame(brwItem, BrwIFrame.class);
				}
			});
		}
		return brwItem;
	}

	/**
	 * 	��ȡ�黹����˵���
	 * @return rtnItem
	 */
	public JMenuItem getRtnItem() {
		if(rtnItem == null) {
			rtnItem = new JMenuItem("�黹����(R)", new ImageIcon(
					getClass().getResource("/res/icon/rtn.png")));
			rtnItem.setMnemonic(KeyEvent.VK_R);
			rtnItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					showInnerFrame(rtnItem, RtnIFrame.class);
				}
			});
		}
		return rtnItem;
	}

	/**
	 * 	��ȡ�����˵�
	 * @return searchMenu
	 */
	public JMenu getSearchMenu() {
		if(searchMenu == null) {
			searchMenu = new JMenu("����(E)");
			searchMenu.setMnemonic(KeyEvent.VK_E);
			searchMenu.add(getDevItem());
			if(MainFrame.isAdmin)
				searchMenu.add(getUserItem());
		}
		return searchMenu;
	}

	/**
	 * 	��ȡ�����豸�˵�
	 * @return devMenu
	 */
	public JMenuItem getDevItem() {
		if(devItem == null) {
			devItem = new JMenuItem("�����豸(D)", new ImageIcon(
					getClass().getResource("/res/icon/search.png")));
			devItem.setMnemonic(KeyEvent.VK_D);
			devItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					showInnerFrame(devItem, DevIFrame.class);
				}
			});
		}
		return devItem;
	}

	/**
	 * 	��ȡ������Ա�˵�
	 * @return userMenu
	 */
	public JMenuItem getUserItem() {
		if(userItem == null) {
			userItem = new JMenuItem("������Ա(U)", new ImageIcon(
					getClass().getResource("/res/icon/finger.png")));
			userItem.setMnemonic(KeyEvent.VK_U);
			userItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					showInnerFrame(userItem, UserIFrame.class);
				}
			});
		}
		return userItem;
	}

	/**
	 * 	��ȡ�����˵�
	 * @return helpMenu
	 */
	public JMenu getHelpMenu() {
		if(helpMenu == null) {
			helpMenu = new JMenu("����(H)");
			helpMenu.setMnemonic(KeyEvent.VK_H);
			helpMenu.add(getPwdItem());
			helpMenu.add(getAboutItem());
		}
		return helpMenu;
	}

	/**
	 * 	��ȡ�޸�����˵���
	 * @return pwdItem
	 */
	public JMenuItem getPwdItem() {
		if(pwdItem == null) {
			pwdItem = new JMenuItem("�޸�����(C)", new ImageIcon(
					getClass().getResource("/res/icon/key.png")));
			pwdItem.setMnemonic(KeyEvent.VK_C);
			pwdItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					showInnerFrame(pwdItem, PwdIFrame.class);
				}
			});
		}
		return pwdItem;
	}

	/**
	 * 	��ȡ���ڲ˵���
	 * @return aboutItem
	 */
	public JMenuItem getAboutItem() {
		if(aboutItem == null) {
			aboutItem = new JMenuItem("����(A)", new ImageIcon(
					getClass().getResource("/res/icon/root.png")));
			aboutItem.setMnemonic(KeyEvent.VK_A);
			aboutItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, 
							"��ҵ�豸����ϵͳ\n\n"
							+ "��ϵͳ����ѧϰJava����Լ�MySQL���ݿ����"
							+ "����д��ˮƽ���ޣ����ָ�̡�\n\n"
							+ "���ߣ����ӿ�\n"
							+ "�汾��Alpha\n"
							+ "���ڣ�2019��7��14��\n"
							+ "Դ���ַ��"
							+ "https://github.com/blingbling-110/DevMngSys\n"
							+ "��ϵ��ʽ��523497359@qq.com", 
							"���ڱ�ϵͳ", JOptionPane.INFORMATION_MESSAGE, 
							new ImageIcon(getClass().getResource(
									"/res/root.jpg")));
				}
			});
		}
		return aboutItem;
	}

	/**
	 * 	��ʾ�ڲ����壬���÷���ʵ�����ڲ��������
	 * @param jMenuItem �ڲ��������Դ�˵���
	 * @param innerFrameC �ڲ�������
	 */
	protected void showInnerFrame(JMenuItem jMenuItem, 
			Class<? extends JInternalFrame> innerFrameC) {
		JInternalFrame innerFrame = innerFrames.get(jMenuItem);
		if(innerFrame == null || innerFrame.isClosed()) {
			//	���÷�������ڲ����幹�췽����newInstance()����
			try {
				innerFrame = innerFrameC.getConstructor().newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			innerFrames.put(jMenuItem, innerFrame);
			desktopPane.add(innerFrame);
			innerFrame.setFrameIcon(jMenuItem.getIcon());
			//	�����ʼλ��
			innerFrame.setLocation(
					(int)(Math.random() * (desktopPane.getWidth() 
							- innerFrame.getWidth())), 
					(int)(Math.random() * (desktopPane.getHeight() 
							- innerFrame.getHeight())));
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
