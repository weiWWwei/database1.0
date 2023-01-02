package com.ww.view;

import com.ww.domain.Menu;
import com.ww.handler.OrderHandler;
import com.ww.service.BillService;
import com.ww.service.MenuService;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class OrderView extends JFrame {
    private MenuService menuService = new MenuService();
    private BillService billService = new BillService();
    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);
    List<JLabel> labels = new ArrayList<>();
    List<JTextField> texts = new ArrayList<>();
    JButton loginBtn = new JButton("确定");
    OrderHandler orderHandler;
    private int diningTableId;

    public OrderView(int selectedTableId) {
        super("点餐界面-农家乐饭店管理系统");
        orderHandler = new OrderHandler(this);
        diningTableId = selectedTableId;
        List<Menu> menus = menuService.list();
        for (Menu menu : menus) {
            JLabel label = new JLabel(menu.getName() + "(" + menu.getPrice() + ")" + ":");
            labels.add(label);
            JTextField text = new JTextField("0");
            texts.add(text);
        }
        URL resource = LoginView.class.getClassLoader().getResource("1.png");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);
        Container contentPane = getContentPane();
        Font centerFont = new Font("楷体", Font.PLAIN, 20);
        for (int i = 0; i < labels.size(); i++) {
            labels.get(i).setFont(centerFont);
            texts.get(i).setPreferredSize(new Dimension(100, 30));
            centerPanel.add(labels.get(i));
            centerPanel.add(texts.get(i));
        }
        loginBtn.addActionListener(orderHandler);
        loginBtn.setFont(centerFont);
        centerPanel.add(loginBtn);
        //弹簧布局
        layoutCenter();
        contentPane.add(centerPanel, BorderLayout.CENTER);
        setSize(500, 800);
        setLocationRelativeTo(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    private void layoutCenter() {
        Spring childWidth = Spring.sum(Spring.sum(Spring.width(labels.get(0)), Spring.width(texts.get(0))), Spring.constant(20));
        int offsetX = childWidth.getValue() / 2;
        //第一个
        springLayout.putConstraint(SpringLayout.WEST, labels.get(0), -offsetX, SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH, labels.get(0), 20, SpringLayout.NORTH, centerPanel);
        springLayout.putConstraint(SpringLayout.WEST, texts.get(0), 20, SpringLayout.EAST, labels.get(0));
        springLayout.putConstraint(SpringLayout.NORTH, texts.get(0), 0, SpringLayout.NORTH, labels.get(0));
        for (int i = 1; i < labels.size(); i++) {
            springLayout.putConstraint(SpringLayout.NORTH, labels.get(i), 20, SpringLayout.SOUTH, labels.get(i - 1));
            springLayout.putConstraint(SpringLayout.EAST, labels.get(i), 0, SpringLayout.EAST, labels.get(i - 1));
            springLayout.putConstraint(SpringLayout.WEST, texts.get(i), 20, SpringLayout.EAST, labels.get(i));
            springLayout.putConstraint(SpringLayout.NORTH, texts.get(i), 0, SpringLayout.NORTH, labels.get(i));
        }
        springLayout.putConstraint(SpringLayout.WEST, loginBtn, 80, SpringLayout.WEST, labels.get(labels.size() - 1));
        springLayout.putConstraint(SpringLayout.NORTH, loginBtn, 20, SpringLayout.SOUTH, texts.get(labels.size() - 1));
    }

    public boolean orderMenu() {
        List<Menu> menus = menuService.list();
        boolean flag = false;
        for (int i = 0; i < texts.size(); i++) {
            if ( "".equals(texts.get(i).getText()) || texts.get(i).getText() == null) continue;
            int num = Integer.parseInt(texts.get(i).getText());
            if (num == 0) continue;
            Menu menu = menuService.getMenuByName(menus.get(i).getName());
            if (!billService.orderMenu(menu.getId(), num, diningTableId)) {
                flag = true;
            }
        }
        return !flag;
    }
}
