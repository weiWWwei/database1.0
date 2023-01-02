package com.ww;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class jframeTest extends JFrame {
    JButton jButton;//按钮
    public jframeTest(){
        super("这是frame的标题");
        jButton = new JButton("这是一个按钮");//按钮设置
        Container contentPane =  getContentPane();
        contentPane.add(jButton);

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
        new jframeTest();
    }
}
