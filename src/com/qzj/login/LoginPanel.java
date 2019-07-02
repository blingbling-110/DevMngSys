package com.qzj.login;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 	登录面板
 * @author qinzijun
 *
 */
public class LoginPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 	登录面板中的背景图片
	 */
	private Image image;
	
	public LoginPanel() {
		URL url = getClass().getResource("/res/login.jpg");
		image = new ImageIcon(url).getImage();
	}
	
	/**
	 * 	重写父类的组件绘制方法
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);//	在面板左上角开始绘制背景图片
	}
}
