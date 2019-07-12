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
	 * 	退出菜单项
	 */
	private JMenuItem exitItem = null;
	
	/**
	 * 	管理菜单
	 */
	private JMenu manageMenu = null;
	
	/**
	 * 	设备总览菜单项
	 */
	private JMenuItem devSumItem = null;
	
	/**
	 * 	人员总览菜单项
	 */
	private JMenuItem userSumItem = null;
	
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
	
	public MenuBar(DesktopPane desktopPane, 
			JLabel stateLabel, boolean isAdmin) {
		this.desktopPane = desktopPane;
		this.stateLabel = stateLabel;
		innerFrames = new HashMap<>();
		add(getFileMenu(isAdmin));
		add(getMangeMenu(isAdmin));
	}
	
	/**
	 * 	获取文件菜单
	 * @return 文件菜单
	 */
	public JMenu getFileMenu(boolean isAdmin) {
		if(fileMenu == null) {
			fileMenu = new JMenu("文件(F)");
			fileMenu.setMnemonic(KeyEvent.VK_F);//	设置快捷键
			fileMenu.add(getExitItem());
		}
		return fileMenu;
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
	 * 	获取设备菜单
	 * @return manageMenu
	 */
	public JMenu getMangeMenu(boolean isAdmin) {
		if(manageMenu == null) {
			manageMenu = new JMenu("总览(S)");
			manageMenu.setMnemonic(KeyEvent.VK_S);
			manageMenu.add(getDevSumItem(isAdmin));
			if(isAdmin)
				manageMenu.add(getUserSumItem());
		}
		return manageMenu;
	}

	/**
	 * 	获取设备总览菜单
	 * @return devSumItem
	 */
	public JMenuItem getDevSumItem(boolean isAdmin) {
		if(devSumItem == null) {
			devSumItem = new JMenuItem("设备总览(D)", new ImageIcon(
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
			userSumItem = new JMenuItem("人员总览(U)", new ImageIcon(
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
	 * 	显示内部窗体，利用反射实例化内部窗体对象
	 * @param jMenuItem 内部窗体的来源菜单项
	 * @param jInternalFrame 内部窗体
	 */
	protected void showInnerFrame(JMenuItem jMenuItem, 
			Class<? extends JInternalFrame> innerFrameC, boolean isAdmin) {
		JInternalFrame innerFrame = innerFrames.get(jMenuItem);
		try {
			if(innerFrame == null || innerFrame.isClosed()) {
				//	利用反射调用内部窗体构造方法的newInstance()方法
				innerFrame = innerFrameC.getConstructor(
						boolean.class).newInstance(isAdmin);
				innerFrames.put(jMenuItem, innerFrame);
				desktopPane.add(innerFrame);
				innerFrame.setFrameIcon(jMenuItem.getIcon());
				//	随机初始位置
				innerFrame.setLocation(
						(int)(Math.random() * (desktopPane.getWidth() - 1032)), 
						(int)(Math.random() * (desktopPane.getHeight() - 432)));
				innerFrame.setVisible(true);
			}
			innerFrame.setSelected(true);//	选中内部窗体
		} catch (Exception e) {
			e.printStackTrace();
		}
		stateLabel.setText(innerFrame.getTitle());//	状态栏显示
		//	添加内部窗体事件监听器
		innerFrame.addInternalFrameListener(new InternalFrameAdapter() {
			//	当内部窗体处于激活状态时调用
			public void internalFrameActivated(InternalFrameEvent e) {
				JInternalFrame jInternalFrame = e.getInternalFrame();
				stateLabel.setText(jInternalFrame.getTitle());
			}
			//	当内部窗体处于非激活状态时调用
			public void internalFrameDeactivated(InternalFrameEvent e) {
				stateLabel.setText("当前没有选定窗体");
			}
		});
	}
}
