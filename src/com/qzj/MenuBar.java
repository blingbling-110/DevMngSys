package com.qzj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import com.qzj.innerFrame.DevSumIFrame;

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
	 * 	设备菜单
	 */
	private JMenu deviceMenu = null;
	
	/**
	 * 	设备总览菜单项
	 */
	private JMenuItem devSumItem = null;
	
	/**
	 * 	设备总览内部窗体
	 */
	private DevSumIFrame devSumIFrame = null;
	
	/**
	 * 	桌面面板
	 */
	private DesktopPane desktopPane = null;
	
	/**
	 * 	状态标签
	 */
	private JLabel stateLabel = null;
	
	public MenuBar(DesktopPane desktopPane, JLabel stateLabel) {
		this.desktopPane = desktopPane;
		this.stateLabel = stateLabel;
		add(getFileMenu());
		add(getDeviceMenu());
	}
	
	/**
	 * 	获取文件菜单
	 * @return 文件菜单
	 */
	public JMenu getFileMenu() {
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
	 * @return deviceMenu
	 */
	public JMenu getDeviceMenu() {
		if(deviceMenu == null) {
			deviceMenu = new JMenu("设备(D)");
			deviceMenu.setMnemonic(KeyEvent.VK_D);
			deviceMenu.add(getDevSumItem());
		}
		return deviceMenu;
	}

	/**
	 * 	获取设备总览菜单
	 * @return devSumItem
	 */
	public JMenuItem getDevSumItem() {
		if(devSumItem == null) {
			devSumItem = new JMenuItem("设备总览(S)", new ImageIcon(
					getClass().getResource("/res/icon/devSum.png")));
			devSumItem.setMnemonic(KeyEvent.VK_S);
			devSumItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					showInnerFrame(devSumItem, getDevSumIFrame());
				}
			});
		}
		return devSumItem;
	}

	/**
	 * 	获取设备总览内部窗体
	 * @return devSumIFrame
	 */
	public DevSumIFrame getDevSumIFrame() {
		if(devSumIFrame == null) {
			devSumIFrame = new DevSumIFrame();
		}
		return devSumIFrame;
	}

	/**
	 * 	显示内部窗体
	 * @param jMenuItem 内部窗体的来源菜单项
	 * @param jInternalFrame 内部窗体
	 */
	protected void showInnerFrame(JMenuItem jMenuItem, 
			JInternalFrame jInternalFrame) {
		if(jInternalFrame.isClosed()) {
			desktopPane.add(jInternalFrame);
			jInternalFrame.setVisible(true);
		}
		try {
			jInternalFrame.setSelected(true);//	选中内部窗体
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		stateLabel.setText(jInternalFrame.getTitle());//	状态栏显示
		//	添加内部窗体事件监听器
		jInternalFrame.addInternalFrameListener(new InternalFrameAdapter() {
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
