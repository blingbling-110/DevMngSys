package com.qzj;

import java.awt.event.KeyEvent;
import java.util.Map;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
	 * 	内部窗体的Map集合
	 */
	private Map<JMenuItem, JInternalFrame> innerFrames = null;
	
	/**
	 * 	文件菜单
	 */
	private JMenu fileMenu = null;
	
	/**
	 * TODO
	 * @param desktopPanel
	 * @param label
	 */
	public MenuBar(JDesktopPane desktopPanel, JLabel label) {
		//TODO
	}
	
	/**
	 * 	获取文件菜单
	 * @return 文件菜单
	 */
	public JMenu getFileMenu() {
		if(fileMenu == null) {
			fileMenu = new JMenu("文件(F)");
			fileMenu.setMnemonic(KeyEvent.VK_F);//	设置快捷键
		}
		return fileMenu;
	}
}
