package com.ww.view;

import com.ww.domain.Bill;
import com.ww.handler.PayHandler;
import com.ww.service.BillService;
import com.ww.service.MenuService;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PayView extends JDialog {
    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);
    JLabel payLabel2 = new JLabel("总金额:");
    JLabel money;
    JLabel payLable = new JLabel("结账方式:");
    private String[] ways = new String[]{"支付宝", "微信", "现金"};
    private List<Bill> bills = new ArrayList<>();
    JComboBox payBox = new JComboBox(ways);
    JButton payBtn = new JButton("确定");
    java.util.List<JLabel> leftLabel = new ArrayList<>();
    List<JLabel> rightLabel = new ArrayList<>();
    BillService billService;
    PayHandler payHandler;
    MenuService menuService;
    private double paySum = 0;
    private int diningTableId = 0;
    public PayView(MainView mainView, int selectedTableId) {
        super(mainView, "结账-农家乐饭店管理系统", true);
        URL resource = LoginView.class.getClassLoader().getResource("1.png");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);
        billService = new BillService();
        menuService = new MenuService();
        payHandler = new PayHandler(this, mainView);
        diningTableId = selectedTableId;
        bills = billService.getBillByDiningTableId(selectedTableId);
        for (int i = 0; i < bills.size(); i++) {
            String name = menuService.getMenuById(bills.get(i).getMenuId()).getName();
            Integer nums = bills.get(i).getNums();
            Double price = menuService.getMenuById(bills.get(i).getMenuId()).getPrice();
            paySum += bills.get(i).getMoney();
            JLabel llabel = new JLabel(name + ": ");
            JLabel rlabel = new JLabel(nums + " * " + price + " = " + bills.get(i).getMoney());
            leftLabel.add(llabel);
            rightLabel.add(rlabel);
        }
        money = new JLabel(String.valueOf(paySum));
        Font centerFont = new Font("楷体", Font.PLAIN, 20);
        for (int i = 0; i < leftLabel.size(); i++) {
            leftLabel.get(i).setFont(centerFont);
            centerPanel.add(leftLabel.get(i));
            rightLabel.get(i).setFont(centerFont);
            centerPanel.add(rightLabel.get(i));
        }
        payLable.setPreferredSize(new Dimension(100, 30));
        payLable.setFont(new Font("楷体", Font.PLAIN, 20));
        centerPanel.add(payLable);
        payLabel2.setFont(new Font("楷体", Font.PLAIN, 20));
        centerPanel.add(payLabel2);
        money.setFont(new Font("楷体", Font.PLAIN, 20));
        centerPanel.add(money);
        payBox.setPreferredSize(new Dimension(80, 30));
        centerPanel.add(payBox);
        payBtn.addActionListener(payHandler);
        centerPanel.add(payBtn);
        Container contentPane = getContentPane();
        layoutCenter();
        contentPane.add(centerPanel, BorderLayout.CENTER);
        setSize(350, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void layoutCenter() {
        Spring childWidth = Spring.sum(Spring.sum(Spring.width(leftLabel.get(0)), Spring.width(rightLabel.get(0))), Spring.constant(20));
        int offsetX = childWidth.getValue() / 2;
        //第一个
        springLayout.putConstraint(SpringLayout.WEST, leftLabel.get(0), -offsetX, SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH, leftLabel.get(0), 20, SpringLayout.NORTH, centerPanel);
        springLayout.putConstraint(SpringLayout.WEST, rightLabel.get(0), 20, SpringLayout.EAST, leftLabel.get(0));
        springLayout.putConstraint(SpringLayout.NORTH, rightLabel.get(0), 0, SpringLayout.NORTH, leftLabel.get(0));
        for (int i = 1; i < leftLabel.size(); i++) {
            springLayout.putConstraint(SpringLayout.NORTH, leftLabel.get(i), 20, SpringLayout.SOUTH, leftLabel.get(i - 1));
            springLayout.putConstraint(SpringLayout.EAST, leftLabel.get(i), 0, SpringLayout.EAST, leftLabel.get(i - 1));
            springLayout.putConstraint(SpringLayout.WEST, rightLabel.get(i), 20, SpringLayout.EAST, leftLabel.get(i));
            springLayout.putConstraint(SpringLayout.NORTH, rightLabel.get(i), 0, SpringLayout.NORTH, leftLabel.get(i));
        }
        springLayout.putConstraint(SpringLayout.NORTH, payLabel2, 20, SpringLayout.SOUTH, leftLabel.get(leftLabel.size() - 1));
        springLayout.putConstraint(SpringLayout.EAST, payLabel2, 0, SpringLayout.EAST, leftLabel.get(leftLabel.size() - 1));
        springLayout.putConstraint(SpringLayout.WEST, money, 20, SpringLayout.EAST, payLabel2);
        springLayout.putConstraint(SpringLayout.NORTH, money, 0, SpringLayout.NORTH, payLabel2);
        springLayout.putConstraint(SpringLayout.NORTH, payLable, 20, SpringLayout.SOUTH, payLabel2);
        springLayout.putConstraint(SpringLayout.EAST, payLable, 0, SpringLayout.EAST, payLabel2);
        springLayout.putConstraint(SpringLayout.WEST, payBox, 20, SpringLayout.EAST, payLable);
        springLayout.putConstraint(SpringLayout.NORTH, payBox, 0, SpringLayout.NORTH, payLable);
        springLayout.putConstraint(SpringLayout.WEST, payBtn, 80, SpringLayout.WEST, payLable);
        springLayout.putConstraint(SpringLayout.NORTH, payBtn, 20, SpringLayout.SOUTH, payLable);
    }

    public boolean payMoney() {
        return billService.payBill(diningTableId,ways[payBox.getSelectedIndex()]);
    }
}
