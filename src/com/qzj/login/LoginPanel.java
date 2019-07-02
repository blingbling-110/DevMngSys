package com.qzj.login;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 	��¼���
 * @author qinzijun
 *
 */
public class LoginPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 	��¼����еı���ͼƬ
	 */
	private Image image;
	
	public LoginPanel() {
		URL url = getClass().getResource("/res/login.jpg");
		image = new ImageIcon(url).getImage();
	}
	
	/**
	 * 	��д�����������Ʒ���
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);//	��������Ͻǿ�ʼ���Ʊ���ͼƬ
	}
}
