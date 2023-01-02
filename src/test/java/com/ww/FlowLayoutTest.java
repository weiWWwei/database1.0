package com.ww;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class FlowLayoutTest extends JFrame{
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,80,30));
    JButton jb1 = new JButton("测试按钮1");
    JButton jb2 = new JButton("测试按钮2");
    JButton jb3 = new JButton("测试按钮3");
    JButton jb4 = new JButton("测试按钮4");
    JButton jb5 = new JButton("测试按钮5");
    JButton jb6 = new JButton("测试按钮6");
    JButton jb7 = new JButton("测试按钮7");
    JButton jb8 = new JButton("测试按钮8");
    public FlowLayoutTest(){
        super("流布局测试");
        //设置窗体图标
        URL resource = jframeTest.class.getClassLoader().getResource("1.png");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);
        Container contentPane = getContentPane();
        jPanel.add(jb1);
        jPanel.add(jb2);
        jPanel.add(jb3);
        jPanel.add(jb4);
        jPanel.add(jb5);
        jPanel.add(jb6);
        jPanel.add(jb7);
        jPanel.add(jb8);
        contentPane.add(jPanel);
        //窗口设置
        setSize(600,400);
        setLocationRelativeTo(null);
        //关闭退出程序
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //大小不可改变
        setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args) {
        new FlowLayoutTest();
    }
}
