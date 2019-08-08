package com.qzj;

import com.qzj.login.LoginDialog;
import com.qzj.sqlOpr.SqlOpr;
import com.qzj.sqlOpr.model.TbBrw;
import com.qzj.sqlOpr.model.TbDevInfo;
import com.qzj.sqlOpr.model.TbUserInfo;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * ��ҵ�豸����ϵͳ������
 *
 * @author qinzijun
 */
public class MainFrame extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * ������˵���
     */
    private MenuBar mainMenuBar = null;

    /**
     * �����幤����
     */
    private ToolBar mainToolBar = null;

    /**
     * �������������
     */
    private JPanel mainContentPane = null;

    /**
     * �������
     */
    private DesktopPane desktopPane = null;

    /**
     * ״̬���
     */
    private JPanel statePane = null;

    /**
     * ״̬��ǩ
     */
    private JLabel stateLabel = null;

    /**
     * �ָ���
     */
    private JSeparator separator = null;

    /**
     * �û���ǩ
     */
    private JLabel userLabel = null;

    /**
     * �Ƿ�ӵ�й���Ȩ��
     */
    public static boolean isAdmin = false;

    /**
     * ��¼�û���
     */
    public static String userId = null;

    /**
     * ��ѯ���������߳�
     */
    private Thread pollingForReq = null;

    /**
     * �����幹�췽��
     */
    public MainFrame() {
        setTitle("��ҵ�豸����ϵͳ");
        setIconImage(new ImageIcon(getClass().getResource(
                "/res/root.jpg")).getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);//	���ô������
        setJMenuBar(getMainMenuBar());
        setContentPane(getMainContentPane());
        getPollingForReq().start();
    }

    /**
     * ��ȡ������˵���
     *
     * @return mainMenuBar
     */
    public MenuBar getMainMenuBar() {
        if (mainMenuBar == null) {
            mainMenuBar = new MenuBar(getDesktopPane(), getStateLabel());
        }
        return mainMenuBar;
    }

    /**
     * ��ȡ�����幤����
     *
     * @return mainToolBar
     */
    public ToolBar getMainToolBar() {
        if (mainToolBar == null) {
            mainToolBar = new ToolBar(getMainMenuBar());
            //	���ù��ͼ��
            mainToolBar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        return mainToolBar;
    }

    /**
     * ��ȡ�������������
     *
     * @return mainContentPane
     */
    public JPanel getMainContentPane() {
        if (mainContentPane == null) {
            mainContentPane = new JPanel(new BorderLayout());
            mainContentPane.add(getMainToolBar(), BorderLayout.NORTH);
            mainContentPane.add(getDesktopPane(), BorderLayout.CENTER);
            mainContentPane.add(getStatePane(), BorderLayout.SOUTH);
        }
        return mainContentPane;
    }

    /**
     * ��ȡ�������
     *
     * @return desktopPane
     */
    public DesktopPane getDesktopPane() {
        if (desktopPane == null) {
            desktopPane = new DesktopPane();
        }
        return desktopPane;
    }

    /**
     * ��ȡ״̬���
     *
     * @return statePane
     */
    public JPanel getStatePane() {
        if (statePane == null) {
            statePane = new JPanel();
            /*
             * 	GridBagLayout�������鲼�ֹ�����
             * 	�������ʱ��Ҫָ��GridBagConstraintsԼ�����󣬲�����
             *
             * 	gridx/gridy������ĺ������ꣻ
             * 	gridwidth�������ռ������Ҳ������Ŀ�ȣ�
             * 	gridheight�������ռ������Ҳ������ĸ߶ȣ�
             * 	fill�������������ڶ����ܳ������ʱ��
             * 		ͨ�� fill��ֵ���趨��䷽ʽ�����ĸ�ֵ��
             * 	ipadx�������ĺ����ࣻ
             * 	ipady�������������ࣻ
             * 	insets������������������ʱ��
             * 		ͨ�� insets��ָ�����ܣ����������ң�������϶��
             * 	anchor��ͬ���ǵ���������������ʱ��ͨ�� anchor�����������λ�ã�
             * 		anchor������ֵ�����Ժ���Ե�ֵ�ֱ��� ���ɸ����ĵ����У������в鿴��
             * 	weightx���е�Ȩ�أ�ͨ�����������������η����е�ʣ��ռ䣻
             * 	weighty���е�Ȩ�أ�ͨ�����������������η����е�ʣ��ռ䡣
             */
            statePane.setLayout(new GridBagLayout());
            //����״̬���ı߿�
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
     * ��ȡ״̬��ǩ
     *
     * @return stateLabel
     */
    public JLabel getStateLabel() {
        if (stateLabel == null) {
            stateLabel = new JLabel("��ǰû��ѡ������");
        }
        return stateLabel;
    }

    /**
     * ��ȡ�ָ���
     *
     * @return separator
     */
    public JSeparator getSeparator() {
        if (separator == null) {
            separator = new JSeparator(JSeparator.VERTICAL);
        }
        return separator;
    }

    /**
     * ��ȡ�û���ǩ
     *
     * @return userLabel
     */
    public JLabel getUserLabel() {
        if (userLabel == null) {
            userLabel = new JLabel("��ǰ��¼�û�����������Ա");
        }
        return userLabel;
    }

    public Thread getPollingForReq() {
        if (pollingForReq == null) {
            pollingForReq = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (MainFrame.userId == null) {
                            JOptionPane.showMessageDialog(MainFrame.this,
                                    "��ֹ��������ݷ���", "��¼ʧ��",
                                    JOptionPane.ERROR_MESSAGE);
                            System.exit(1);
                        }
                        String jobNum = null;
                        try {
                            jobNum = SqlOpr.getIdFromUserId(
                                    MainFrame.userId);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(
                                    MainFrame.this, "��ѯ����ʧ��",
                                    "����ʧ��", JOptionPane.ERROR_MESSAGE);
                            System.exit(1);
                        }
                        List<List<String>> pollingList = SqlOpr.pollForReq(
                                jobNum);
                        if (pollingList.size() != 0) {
                            for (List<String> a : pollingList) {
                                String devId = a.get(0);
                                Item devItem = new Item(devId, null, null);
                                TbDevInfo devInfo = SqlOpr.getDevInfo(devItem);
                                String devName = devInfo.getName();
                                Integer reqerId = Integer.parseInt(a.get(1));
                                Item reqerItem = new Item(null, null, reqerId);
                                TbUserInfo reqerInfo = SqlOpr.getUserInfo(reqerItem);
                                String reqerName = reqerInfo.getName();
                                if (JOptionPane.showConfirmDialog(
                                        MainFrame.this,
                                        "����Ϊ " + reqerId + " ��ͬ��\""
                                                + reqerName + "\"������ñ��Ϊ "
                                                + devId + " ���豸\"" + devName
                                                + "\"����ȷ���豸��ת�ơ�",
                                        "�豸ת��",
                                        JOptionPane.OK_CANCEL_OPTION,
                                        JOptionPane.WARNING_MESSAGE) ==
                                        JOptionPane.OK_OPTION) {
                                    Date date = new Date();
                                    SimpleDateFormat sdf = new SimpleDateFormat(
                                            "yyyyMMdd");
                                    String brwId = "brwID_" + sdf.format(date);
                                    String dateStr = String.format("%tc", date);
                                    //	��ȡ���н��õ�
                                    List<List<String>> allBrw = SqlOpr.getAllBrw();
                                    int idCount = 0;//	ͳ�Ƶ������н��õ�����
                                    //	��ȡ���н��õ����
                                    for(int i = 0; i < allBrw.size(); i++) {
                                        String id = allBrw.get(i).get(0);
                                        if(id.indexOf(brwId) == 0)
                                            idCount++;
                                    }
                                    //	ȷ�����õ����
                                    idCount++;
                                    brwId = brwId + String.format("%03d", idCount);
                                    TbBrw brw = new TbBrw();//	��װ�����ӽ��õ��Ķ���
                                    brw.setId(brwId.trim());
                                    brw.setDevId(devId);
                                    brw.setBrwerId(reqerId);
                                    brw.setDate(dateStr);
                                    brw.setRemark("�豸ת��");
                                    boolean res = SqlOpr.insertTbBrw(brw);
                                    boolean isClear = SqlOpr.sendReq(devId, "");
                                    if(res && isClear) {
                                        JOptionPane.showMessageDialog(
                                                MainFrame.this,
                                                "�豸�ѳɹ�ת��",
                                                "�����ɹ�",
                                                JOptionPane.INFORMATION_MESSAGE);
                                    }else
                                        JOptionPane.showMessageDialog(
                                                MainFrame.this,
                                                "�豸ת��ʧ��",
                                                "����ʧ��",
                                                JOptionPane.ERROR_MESSAGE);
                                } else {
                                    boolean res = SqlOpr.sendReq(devId, "");
                                    if (res)
                                        JOptionPane.showMessageDialog(
                                                MainFrame.this,
                                                "�Ѿܾ���������",
                                                "�����ɹ�",
                                                JOptionPane.INFORMATION_MESSAGE);
                                    else
                                        JOptionPane.showMessageDialog(
                                                MainFrame.this,
                                                "�ܾ���������ʧ��",
                                                "����ʧ��",
                                                JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        return pollingForReq;
    }

    /**
     * ���ڱ�д���������ľ�̬�ڲ���
     *
     * @author blingbling_inwin
     */
    static class Test {
        public static void main(String[] args) {
            try {
                //	�����񱾵ػ�
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                MainFrame.isAdmin = true;
                MainFrame.userId = "root";
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ����������
     *
     * @param args
     */
    public static void main(String[] args) {
        LoginDialog loginDialog = new LoginDialog();
        loginDialog.setVisible(true);
    }
}
