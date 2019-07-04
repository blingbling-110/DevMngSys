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
	
	/**
	 * 	�豸�˵�
	 */
	private JMenu deviceMenu = null;
	
	/**
	 * 	�豸�����˵���
	 */
	private JMenuItem devSumItem = null;
	
	/**
	 * 	�豸�����ڲ�����
	 */
	private DevSumIFrame devSumIFrame = null;
	
	/**
	 * 	�������
	 */
	private DesktopPane desktopPane = null;
	
	/**
	 * 	״̬��ǩ
	 */
	private JLabel stateLabel = null;
	
	public MenuBar(DesktopPane desktopPane, JLabel stateLabel) {
		this.desktopPane = desktopPane;
		this.stateLabel = stateLabel;
		add(getFileMenu());
		add(getDeviceMenu());
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

	/**
	 * 	��ȡ�豸�˵�
	 * @return deviceMenu
	 */
	public JMenu getDeviceMenu() {
		if(deviceMenu == null) {
			deviceMenu = new JMenu("�豸(D)");
			deviceMenu.setMnemonic(KeyEvent.VK_D);
			deviceMenu.add(getDevSumItem());
		}
		return deviceMenu;
	}

	/**
	 * 	��ȡ�豸�����˵�
	 * @return devSumItem
	 */
	public JMenuItem getDevSumItem() {
		if(devSumItem == null) {
			devSumItem = new JMenuItem("�豸����(S)", new ImageIcon(
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
	 * 	��ȡ�豸�����ڲ�����
	 * @return devSumIFrame
	 */
	public DevSumIFrame getDevSumIFrame() {
		if(devSumIFrame == null) {
			devSumIFrame = new DevSumIFrame();
		}
		return devSumIFrame;
	}

	/**
	 * 	��ʾ�ڲ�����
	 * @param jMenuItem �ڲ��������Դ�˵���
	 * @param jInternalFrame �ڲ�����
	 */
	protected void showInnerFrame(JMenuItem jMenuItem, 
			JInternalFrame jInternalFrame) {
		if(jInternalFrame.isClosed()) {
			desktopPane.add(jInternalFrame);
			jInternalFrame.setVisible(true);
		}
		try {
			jInternalFrame.setSelected(true);//	ѡ���ڲ�����
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		stateLabel.setText(jInternalFrame.getTitle());//	״̬����ʾ
		//	����ڲ������¼�������
		jInternalFrame.addInternalFrameListener(new InternalFrameAdapter() {
			//	���ڲ����崦�ڼ���״̬ʱ����
			public void internalFrameActivated(InternalFrameEvent e) {
				JInternalFrame jInternalFrame = e.getInternalFrame();
				stateLabel.setText(jInternalFrame.getTitle());
			}
			//	���ڲ����崦�ڷǼ���״̬ʱ����
			public void internalFrameDeactivated(InternalFrameEvent e) {
				stateLabel.setText("��ǰû��ѡ������");
			}
		});
	}
}
