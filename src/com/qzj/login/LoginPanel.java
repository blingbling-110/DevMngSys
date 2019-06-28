package com.qzj.login;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LoginPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int width, height;
	private Image image;
	
	public LoginPanel() {
		URL url = getClass().getResource("/res/login.jpg");
		image = new ImageIcon(url).getImage();
	}
	
	protected void paintComponent(Graphics g) {//	重写父类的组件绘制方法
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);//	在面板左上角开始绘制背景图片
	}
}
