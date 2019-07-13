package com.qzj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;

/**
 * 	主窗体工具栏
 * @author qinzijun
 *
 */
public class ToolBar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 	主窗体工具栏构造方法
	 * @param menuBar 主窗体菜单栏
	 * @param isAdmin 是否拥有管理权限
	 */
	public ToolBar(MenuBar menuBar, boolean isAdmin) {
		//	设置工具栏的边框
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		add(addToolButton(menuBar.getDevSumItem(isAdmin)));
		if(isAdmin) {
			add(addToolButton(menuBar.getUserSumItem()));
			add(addToolButton(menuBar.getBrwItem()));
			add(addToolButton(menuBar.getRtnItem()));
		}
		add(addToolButton(menuBar.getDevItem(isAdmin)));
		if(isAdmin)
			add(addToolButton(menuBar.getUserItem()));
		add(addToolButton(menuBar.getExitItem()));
	}
	
	/**
	 * 	增加工具栏按钮
	 * @param item 工具栏按钮对应的菜单项
	 * @return 工具栏按钮
	 */
	private JButton addToolButton(final JMenuItem item) {
		JButton button = new JButton(item.getIcon());
		button.setToolTipText(item.getText().substring(0, 
				item.getText().length() - 3));//	设置工具栏按钮的悬停文本
		button.setFocusable(false);//	使工具栏按钮失去焦点
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				item.doClick();//	菜单项的点击事件
			}
		});
		return button;
	}
}
