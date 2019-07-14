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
 * 	主窗体菜单栏
 * @author qinzijun
 *
 */
public class MenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 	文件菜单
	 */
	private JMenu fileMenu = null;
	
	/**
	 * 	数据库备份菜单项
	 */
	private JMenuItem backupItem = null;
	
	/**
	 * 	数据库恢复菜单项
	 */
	private JMenuItem restoreItem = null;
	
	/**
	 * 	退出菜单项
	 */
	private JMenuItem exitItem = null;
	
	/**
	 * 	总览菜单
	 */
	private JMenu sumMenu = null;
	
	/**
	 * 	设备总览菜单项
	 */
	private JMenuItem devSumItem = null;
	
	/**
	 * 	人员总览菜单项
	 */
	private JMenuItem userSumItem = null;
	
	/**
	 * 	管理菜单
	 */
	private JMenu manageMenu = null;
	
	/**
	 * 	借用管理菜单项
	 */
	private JMenuItem brwItem = null;
	
	/**
	 * 	归还管理菜单项
	 */
	private JMenuItem rtnItem = null;
	
	/**
	 * 	搜索菜单
	 */
	private JMenu searchMenu = null;
	
	/**
	 * 	搜索设备菜单项
	 */
	private JMenuItem devItem = null;
	
	/**
	 * 	搜索人员菜单项
	 */
	private JMenuItem userItem = null;
	
	/**
	 * 	帮助菜单
	 */
	private JMenu helpMenu = null;
	
	/**
	 * 	修改密码菜单项
	 */
	private JMenuItem pwdItem = null;
	
	/**
	 * 	关于菜单项
	 */
	private JMenuItem aboutItem = null;
	
	/**
	 * 	内部窗体的Map集合
	 */
	private Map<JMenuItem, JInternalFrame> innerFrames = null;
	
	/**
	 * 	桌面面板
	 */
	private DesktopPane desktopPane = null;
	
	/**
	 * 	状态标签
	 */
	private JLabel stateLabel = null;
	
	/**
	 * 	主窗体菜单栏构造方法
	 * @param desktopPane 主窗体桌面面板
	 * @param stateLabel 主窗体状态标签
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
	 * 	获取文件菜单
	 * @return 文件菜单
	 */
	public JMenu getFileMenu() {
		if(fileMenu == null) {
			fileMenu = new JMenu("文件(F)");
			fileMenu.setMnemonic(KeyEvent.VK_F);//	设置快捷键
			if(MainFrame.isAdmin) {
				fileMenu.add(getBackupItem());
				fileMenu.add(getRestoreItem());
			}
			fileMenu.add(getExitItem());
		}
		return fileMenu;
	}

	/**
	 * 	获取数据库备份菜单项
	 * @return backupItem
	 */
	public JMenuItem getBackupItem() {
		if(backupItem == null) {
			backupItem = new JMenuItem("数据库备份(B)", new ImageIcon(
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
	 * 	获取数据库恢复菜单项
	 * @return restoreItem
	 */
	public JMenuItem getRestoreItem() {
		if(restoreItem == null) {
			restoreItem = new JMenuItem("数据库恢复(R)", new ImageIcon(
					getClass().getResource("/res/icon/restore.png")));
			restoreItem.setMnemonic(KeyEvent.VK_R);
			restoreItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(JOptionPane.showConfirmDialog(null, 
							"请注意：在进行数据库恢复操作之前请备份当前数据库，否则将"
							+ "造成不可逆的数据丢失。\n确定现在要进行数据库恢复操作？", 
							"注意事项", 
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
	 * 	获取退出菜单项
	 * @return exitItem
	 */
	public JMenuItem getExitItem() {
		if(exitItem == null) {
			exitItem = new JMenuItem("退出(X)", new ImageIcon(
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
	 * 	获取总览菜单
	 * @return sumMenu
	 */
	public JMenu getSumMenu() {
		if(sumMenu == null) {
			sumMenu = new JMenu("总览(S)");
			sumMenu.setMnemonic(KeyEvent.VK_S);
			sumMenu.add(getDevSumItem());
			if(MainFrame.isAdmin)
				sumMenu.add(getUserSumItem());
		}
		return sumMenu;
	}

	/**
	 * 	获取设备总览菜单项
	 * @return devSumItem
	 */
	public JMenuItem getDevSumItem() {
		if(devSumItem == null) {
			devSumItem = new JMenuItem("设备总览(D)", new ImageIcon(
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
	 * 	获取人员总览菜单项
	 * @return userSumItem
	 */
	public JMenuItem getUserSumItem() {
		if(userSumItem == null) {
			userSumItem = new JMenuItem("人员总览(U)", new ImageIcon(
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
	 * 	获取管理菜单
	 * @return manageMenu
	 */
	public JMenu getManageMenu() {
		if(manageMenu == null) {
			manageMenu = new JMenu("管理(M)");
			manageMenu.setMnemonic(KeyEvent.VK_M);
			manageMenu.add(getBrwItem());
			manageMenu.add(getRtnItem());
		}
		return manageMenu;
	}

	/**
	 * 	获取借用管理菜单项
	 * @return brwItem
	 */
	public JMenuItem getBrwItem() {
		if(brwItem == null) {
			brwItem = new JMenuItem("借用管理(B)", new ImageIcon(
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
	 * 	获取归还管理菜单项
	 * @return rtnItem
	 */
	public JMenuItem getRtnItem() {
		if(rtnItem == null) {
			rtnItem = new JMenuItem("归还管理(R)", new ImageIcon(
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
	 * 	获取搜索菜单
	 * @return searchMenu
	 */
	public JMenu getSearchMenu() {
		if(searchMenu == null) {
			searchMenu = new JMenu("搜索(E)");
			searchMenu.setMnemonic(KeyEvent.VK_E);
			searchMenu.add(getDevItem());
			if(MainFrame.isAdmin)
				searchMenu.add(getUserItem());
		}
		return searchMenu;
	}

	/**
	 * 	获取搜索设备菜单
	 * @return devMenu
	 */
	public JMenuItem getDevItem() {
		if(devItem == null) {
			devItem = new JMenuItem("搜索设备(D)", new ImageIcon(
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
	 * 	获取搜索人员菜单
	 * @return userMenu
	 */
	public JMenuItem getUserItem() {
		if(userItem == null) {
			userItem = new JMenuItem("搜索人员(U)", new ImageIcon(
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
	 * 	获取帮助菜单
	 * @return helpMenu
	 */
	public JMenu getHelpMenu() {
		if(helpMenu == null) {
			helpMenu = new JMenu("帮助(H)");
			helpMenu.setMnemonic(KeyEvent.VK_H);
			helpMenu.add(getPwdItem());
			helpMenu.add(getAboutItem());
		}
		return helpMenu;
	}

	/**
	 * 	获取修改密码菜单项
	 * @return pwdItem
	 */
	public JMenuItem getPwdItem() {
		if(pwdItem == null) {
			pwdItem = new JMenuItem("修改密码(C)", new ImageIcon(
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
	 * 	获取关于菜单项
	 * @return aboutItem
	 */
	public JMenuItem getAboutItem() {
		if(aboutItem == null) {
			aboutItem = new JMenuItem("关于(A)", new ImageIcon(
					getClass().getResource("/res/icon/root.png")));
			aboutItem.setMnemonic(KeyEvent.VK_A);
			aboutItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, 
							"企业设备管理系统\n\n"
							+ "本系统出于学习Java编程以及MySQL数据库操作"
							+ "而编写，水平有限，多多指教。\n\n"
							+ "作者：覃子俊\n"
							+ "版本：Alpha\n"
							+ "日期：2019年7月14日\n"
							+ "源码地址："
							+ "https://github.com/blingbling-110/DevMngSys\n"
							+ "联系方式：523497359@qq.com", 
							"关于本系统", JOptionPane.INFORMATION_MESSAGE, 
							new ImageIcon(getClass().getResource(
									"/res/root.jpg")));
				}
			});
		}
		return aboutItem;
	}

	/**
	 * 	显示内部窗体，利用反射实例化内部窗体对象
	 * @param jMenuItem 内部窗体的来源菜单项
	 * @param innerFrameC 内部窗体类
	 */
	protected void showInnerFrame(JMenuItem jMenuItem, 
			Class<? extends JInternalFrame> innerFrameC) {
		JInternalFrame innerFrame = innerFrames.get(jMenuItem);
		if(innerFrame == null || innerFrame.isClosed()) {
			//	利用反射调用内部窗体构造方法的newInstance()方法
			try {
				innerFrame = innerFrameC.getConstructor().newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			innerFrames.put(jMenuItem, innerFrame);
			desktopPane.add(innerFrame);
			innerFrame.setFrameIcon(jMenuItem.getIcon());
			//	随机初始位置
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
		}//	选中内部窗体
		//	状态栏显示
		stateLabel.setText("当前选定的窗体：" + innerFrame.getTitle());
		//	添加内部窗体事件监听器
		innerFrame.addInternalFrameListener(new InternalFrameAdapter() {
			//	当内部窗体处于激活状态时调用
			public void internalFrameActivated(InternalFrameEvent e) {
				JInternalFrame jInternalFrame = e.getInternalFrame();
				stateLabel.setText("当前选定的窗体：" + jInternalFrame.getTitle());
			}
			//	当内部窗体处于非激活状态时调用
			public void internalFrameDeactivated(InternalFrameEvent e) {
				stateLabel.setText("当前没有选定窗体");
			}
		});
	}
}
