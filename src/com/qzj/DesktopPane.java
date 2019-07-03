package com.qzj;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JDesktopPane;

/**
 * 	�������
 * @author qinzijun
 *
 */
public class DesktopPane extends JDesktopPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* ���� Javadoc��
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 * 	��д��������ķ���
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.LIGHT_GRAY);//	������������ɫ
		//	�õ�ǰ��������ɫ������
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
