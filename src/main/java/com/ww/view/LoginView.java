package com.ww.view;

import com.ww.handler.LoginHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

public class LoginView extends JFrame {

    JLabel nameLabel = new JLabel("农家乐饭店管理系统", JLabel.CENTER);
    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);
    JLabel userNameLabel = new JLabel("用户名:");
    JTextField userTxt = new JTextField();
    JLabel pwdLabel = new JLabel("密码:");
    JPasswordField pwdField = new JPasswordField();
    JButton loginBtn = new JButton("登录");
    JButton resetBtn = new JButton("重置");
    TrayIcon trayIcon;
    SystemTray systemTray;
    LoginHandler loginHandler;

    public LoginView() {
        super("农家乐饭店管理系统");
        loginHandler = new LoginHandler(this);
        URL resource = LoginView.class.getClassLoader().getResource("1.png");
        Image image = new ImageIcon(resource).getImage();
        nameLabel.setPreferredSize(new Dimension(0, 80));
        setIconImage(image);
        if (SystemTray.isSupported()) {
            systemTray = SystemTray.getSystemTray();
            trayIcon = new TrayIcon(new ImageIcon(resource).getImage());
            trayIcon.setImageAutoSize(true);
            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowIconified(WindowEvent e) {
                    LoginView.this.dispose();
                }
            });
            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int clickCount = e.getClickCount();
                    if (clickCount == 1) {
                        LoginView.this.setExtendedState(JFrame.NORMAL);
                    }
                    LoginView.this.setVisible(true);
                }
            });
        }
        //设置LoginBtn为默认按钮
        getRootPane().setDefaultButton(loginBtn);
        Container contentPane = getContentPane();
        nameLabel.setFont(new Font("华文行楷", Font.PLAIN, 40));
        Font centerFont = new Font("楷体", Font.PLAIN, 20);
        userNameLabel.setFont(centerFont);
        userTxt.setPreferredSize(new Dimension(200, 30));
        pwdLabel.setFont(centerFont);
        pwdField.setPreferredSize(new Dimension(200, 30));
        loginBtn.setFont(centerFont);
        resetBtn.setFont(centerFont);
        centerPanel.add(userNameLabel);
        centerPanel.add(userTxt);
        centerPanel.add(pwdLabel);
        centerPanel.add(pwdField);
        loginBtn.addKeyListener(loginHandler);
        loginBtn.addActionListener(loginHandler);
        centerPanel.add(loginBtn);
        resetBtn.addActionListener(loginHandler);
        centerPanel.add(resetBtn);
        //弹簧布局
        layoutCenter();
        contentPane.add(nameLabel, BorderLayout.NORTH);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        //窗口设置
        setSize(600, 400);
        setLocationRelativeTo(null);
        //关闭退出程序
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //大小不可改变
        setResizable(false);
        setVisible(true);
    }

    private void layoutCenter() {
        Spring childWidth = Spring.sum(Spring.sum(Spring.width(userNameLabel), Spring.width(userTxt)), Spring.constant(20));
        int offsetX = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, userNameLabel, -offsetX, SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH, userNameLabel, 20, SpringLayout.NORTH, centerPanel);
        //userTxt
        springLayout.putConstraint(SpringLayout.WEST, userTxt, 20, SpringLayout.EAST, userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, userTxt, 0, SpringLayout.NORTH, userNameLabel);
        //pwdLabel
        springLayout.putConstraint(SpringLayout.NORTH, pwdLabel, 20, SpringLayout.SOUTH, userNameLabel);
        springLayout.putConstraint(SpringLayout.EAST, pwdLabel, 0, SpringLayout.EAST, userNameLabel);
        //pwdField
        springLayout.putConstraint(SpringLayout.WEST, pwdField, 20, SpringLayout.EAST, pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH, pwdField, 0, SpringLayout.NORTH, pwdLabel);
        //loginBtn
        springLayout.putConstraint(SpringLayout.WEST, loginBtn, 80, SpringLayout.WEST, pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH, loginBtn, 20, SpringLayout.SOUTH, pwdField);
        //resetBtn
        springLayout.putConstraint(SpringLayout.WEST, resetBtn, 30, SpringLayout.EAST, loginBtn);
        springLayout.putConstraint(SpringLayout.NORTH, resetBtn, 0, SpringLayout.NORTH, loginBtn);
    }

    public JTextField getUserTxt() {
        return userTxt;
    }

    public JPasswordField getPwdField() {
        return pwdField;
    }

}
