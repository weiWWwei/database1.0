package com.ww.view;

import com.ww.domain.DiningTable;
import com.ww.domain.Menu;
import com.ww.handler.AddMenuHandler;
import com.ww.handler.MenuHanlder;
import com.ww.handler.ReverseTableHandler;
import com.ww.service.DiningTableService;
import com.ww.service.MenuService;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class AddMenuView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel nameLable = new JLabel("菜品名:",JLabel.RIGHT);
    JTextField nameIdTxt = new JTextField();
    JLabel typeLable = new JLabel("类型:",JLabel.RIGHT);
    JTextField typeTxt = new JTextField();
    JLabel priceLable = new JLabel("价格:",JLabel.RIGHT);
    JTextField priceTxt = new JTextField();
    JButton addBtn = new JButton("确定");
    MenuService menuService;
    AddMenuHandler addMenuHandler;
    public AddMenuView(MenuView menuView){
        super(menuView,"增加菜品-农家乐饭店管理系统",true);
        URL resource = LoginView.class.getClassLoader().getResource("1.png");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);
        menuService = new MenuService();
        addMenuHandler = new AddMenuHandler(this,menuView);
        nameLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(nameLable);
        nameIdTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(nameIdTxt);
        typeLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(typeLable);
        typeTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(typeTxt);
        priceLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(priceLable);
        priceTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(priceTxt);
        addBtn.addActionListener(addMenuHandler);
        jPanel.add(addBtn);
        Container contentPane = getContentPane();
        contentPane.add(jPanel);
        setSize(350,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public boolean addMenu() {
        String nameText = nameIdTxt.getText();
        String typeText = typeTxt.getText();
        String priceText = priceTxt.getText();
        return menuService.addMenu(nameText,typeText,Double.parseDouble(priceText));
    }
}
