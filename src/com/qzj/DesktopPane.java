package com.qzj;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JDesktopPane;

/**
 * 	桌面面板
 * @author qinzijun
 *
 */
public class DesktopPane extends JDesktopPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* （非 Javadoc）
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 * 	重写绘制组件的方法
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.LIGHT_GRAY);//	设置上下文颜色
		//	用当前上下文颜色填充矩形
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
