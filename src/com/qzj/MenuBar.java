package com.qzj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
	
	public MenuBar() {
		add(getFileMenu());
	}
	
	/**
	 * 	��ȡ�ļ��˵�
	 * @return �ļ��˵�
	 */
	public JMenu getFileMenu() {
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
}
