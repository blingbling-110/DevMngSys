package com.qzj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;

/**
 * 	�����幤����
 * @author qinzijun
 *
 */
public class ToolBar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 	�����幤�������췽��
	 * @param menuBar ������˵���
	 * @param isAdmin �Ƿ�ӵ�й���Ȩ��
	 */
	public ToolBar(MenuBar menuBar, boolean isAdmin) {
		//	���ù������ı߿�
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
	 * 	���ӹ�������ť
	 * @param item ��������ť��Ӧ�Ĳ˵���
	 * @return ��������ť
	 */
	private JButton addToolButton(final JMenuItem item) {
		JButton button = new JButton(item.getIcon());
		button.setToolTipText(item.getText().substring(0, 
				item.getText().length() - 3));//	���ù�������ť����ͣ�ı�
		button.setFocusable(false);//	ʹ��������ťʧȥ����
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				item.doClick();//	�˵���ĵ���¼�
			}
		});
		return button;
	}
}
