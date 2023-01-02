package com.ww;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BorderLayoutTest extends JFrame {
    JButton northBtn = new JButton("北边的按钮");
    JLabel southLabel = new JLabel("南边的label");
    JRadioButton westRadioBtn = new JRadioButton("男");
    JTextArea eastRrea = new JTextArea("输入内容",10,20);
    JButton centerBtn = new JButton("中间的按钮");
    public BorderLayoutTest(){
        super("测试边界布局");
        Container contentPane =  getContentPane();
        contentPane.add(northBtn,BorderLayout.NORTH);
        southLabel.setPreferredSize(new Dimension(0,80));
        contentPane.add(southLabel,BorderLayout.SOUTH);
        contentPane.add(westRadioBtn,BorderLayout.WEST);
        contentPane.add(eastRrea,BorderLayout.EAST);
        contentPane.add(centerBtn,BorderLayout.CENTER);
        //设置窗体图标
        URL resource = jframeTest.class.getClassLoader().getResource("1.png");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);
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
        new BorderLayoutTest();
    }
}
