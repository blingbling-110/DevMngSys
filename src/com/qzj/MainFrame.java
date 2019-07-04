package com.qzj;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

/**
 * 	企业设备管理系统主窗体
 * @author qinzijun
 *
 */
public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 	主窗体菜单栏
	 */
	private MenuBar mainMenuBar = null;
	
	/**
	 * 	主窗体工具栏
	 */
	private ToolBar mainToolBar = null;
	
	/**
	 * 	主窗体内容面板
	 */
	private JPanel mainContentPane = null;
	
	/**
	 * 	桌面面板
	 */
	private DesktopPane desktopPane = null;
	
	/**
	 * 	状态面板
	 */
	private JPanel statePane = null;
	
	/**
	 * 	状态标签
	 */
	private JLabel stateLabel = null;
	
	/**
	 * 	分隔符
	 */
	private JSeparator separator = null;
	
	/**
	 * 	用户标签
	 */
	private JLabel userLabel = null;
	
	public MainFrame() {
		setTitle("企业设备管理系统");
		setIconImage(new ImageIcon(getClass().getResource(
				"/res/root.jpg")).getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);//	设置窗体居中
		setJMenuBar(getMainMenuBar());
		setContentPane(getMainContentPane());
	}

	/**
	 * 	获取主窗体菜单栏
	 * @return mainMenuBar
	 */
	public MenuBar getMainMenuBar() {
		if(mainMenuBar == null) {
			mainMenuBar = new MenuBar();
		}
		return mainMenuBar;
	}
	
	/**
	 * 	获取主窗体工具栏
	 * @return mainToolBar
	 */
	public ToolBar getMainToolBar() {
		if(mainToolBar == null) {
			mainToolBar = new ToolBar(getMainMenuBar());
			//	设置光标图像
			mainToolBar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		return mainToolBar;
	}

	/**
	 * 	获取主窗体内容面板
	 * @return mainContentPane
	 */
	public JPanel getMainContentPane() {
		if(mainContentPane == null) {
			mainContentPane = new JPanel(new BorderLayout());
			mainContentPane.add(getMainToolBar(), BorderLayout.NORTH);
			mainContentPane.add(getDesktopPane(), BorderLayout.CENTER);
			mainContentPane.add(getStatePane(), BorderLayout.SOUTH);
		}
		return mainContentPane;
	}

	/**
	 * 	获取桌面面板
	 * @return desktopPane
	 */
	public DesktopPane getDesktopPane() {
		if(desktopPane == null) {
			desktopPane = new DesktopPane();
		}
		return desktopPane;
	}

	/**
	 * 	获取状态面板
	 * @return statePane
	 */
	public JPanel getStatePane() {
		if(statePane == null) {
			statePane = new JPanel();
			/*
			 * 	GridBagLayout：网格组布局管理器
			 * 	布局组件时需要指定GridBagConstraints约束对象，参数：
			 * 
			 * 	gridx/gridy：组件的横纵坐标；
			 * 	gridwidth：组件所占列数，也是组件的宽度；
			 * 	gridheight：组件所占行数，也是组件的高度；
			 * 	fill：当组件在其格内而不能撑满其格时，
			 * 		通过 fill的值来设定填充方式，有四个值；
			 * 	ipadx：组件间的横向间距；
			 * 	ipady：组件间的纵向间距；
			 * 	insets：当组件不能填满其格时，
			 * 		通过 insets来指定四周（即上下左右）所留空隙；
			 * 	anchor：同样是当组件不能填满其格时，通过 anchor来设置组件的位置，
			 * 		anchor有两种值，绝对和相对的值分别有 若干个，文档中有，可自行查看；
			 * 	weightx：行的权重，通过这个属性来决定如何分配行的剩余空间；
			 * 	weighty：列的权重，通过这个属性来决定如何分配列的剩余空间。
			 */
			statePane.setLayout(new GridBagLayout());
			//设置状态面板的边框
			statePane.setBorder(BorderFactory.createBevelBorder(
					BevelBorder.RAISED));
			GridBagConstraints stateLabelGbc = new GridBagConstraints();
			stateLabelGbc.gridx = 0;
			stateLabelGbc.gridy = 0;
			stateLabelGbc.fill = GridBagConstraints.HORIZONTAL;
			stateLabelGbc.weightx = 1.0;
			stateLabelGbc.insets = new Insets(0, 8, 0, 8);
			GridBagConstraints separatorGbc = new GridBagConstraints();
			separatorGbc.gridx = 1;
			separatorGbc.gridy = 0;
			separatorGbc.fill = GridBagConstraints.VERTICAL;
			GridBagConstraints userLabelGbc = new GridBagConstraints();
			userLabelGbc.gridx = 2;
			userLabelGbc.gridy = 0;
			userLabelGbc.insets = new Insets(0, 8, 0, 8);
			statePane.add(getStateLabel(), stateLabelGbc);
			statePane.add(getSeparator(), separatorGbc);
			statePane.add(getUserLabel(), userLabelGbc);
		}
		return statePane;
	}

	/**
	 * 	获取状态标签
	 * @return stateLabel
	 */
	public JLabel getStateLabel() {
		if(stateLabel == null) {
			stateLabel = new JLabel("当前没有选定窗体");
		}
		return stateLabel;
	}

	/**
	 * 	获取分隔符
	 * @return separator
	 */
	public JSeparator getSeparator() {
		if(separator == null) {
			separator = new JSeparator(JSeparator.VERTICAL);
		}
		return separator;
	}

	/**
	 * 	获取用户标签
	 * @return userLabel
	 */
	public JLabel getUserLabel() {
		if(userLabel == null) {
			userLabel = new JLabel("当前登录用户：");
		}
		return userLabel;
	}

	/**
	 * 	用于编写测试用例的静态内部类
	 * @author blingbling_inwin
	 *
	 */
	static class Test {
		public static void main(String[] args) {
			try {
				//	窗体风格本地化
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				new MainFrame().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
