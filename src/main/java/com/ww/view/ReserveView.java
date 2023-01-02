package com.ww.view;

import com.ww.domain.DiningTable;
import com.ww.handler.ReverseTableHandler;
import com.ww.service.DiningTableService;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ReserveView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel tableIdLable = new JLabel("餐桌号:",JLabel.RIGHT);
    JTextField tableIdTxt = new JTextField();
    JLabel nameLable = new JLabel("姓名:",JLabel.RIGHT);
    JTextField nameIdTxt = new JTextField();
    JLabel phoneLable = new JLabel("电话:",JLabel.RIGHT);
    JTextField phoneTxt = new JTextField();
    JButton reverseBtn = new JButton("确定");
    DiningTableService diningTableService ;
    ReverseTableHandler reverseTableHandler;
    public ReserveView(MainView mainView,int selectedTableId){
        super(mainView,"预定餐桌-农家乐饭店管理系统",true);
        URL resource = LoginView.class.getClassLoader().getResource("1.png");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);
        diningTableService = new DiningTableService();
        DiningTable diningTable = diningTableService.getDiningTableById(selectedTableId);
        reverseTableHandler = new ReverseTableHandler(this,mainView);
        tableIdLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(tableIdLable);
        tableIdTxt.setPreferredSize(new Dimension(200,30));
        tableIdTxt.setText(diningTable.getId()+"");
        jPanel.add(tableIdTxt);
        nameLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(nameLable);
        nameIdTxt.setPreferredSize(new Dimension(200,30));
        nameIdTxt.setText(diningTable.getOrderName());
        jPanel.add(nameIdTxt);
        phoneLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(phoneLable);
        phoneTxt.setPreferredSize(new Dimension(200,30));
        phoneTxt.setText(diningTable.getOrderTel());
        jPanel.add(phoneTxt);
        reverseBtn.addActionListener(reverseTableHandler);
        jPanel.add(reverseBtn);
        Container contentPane = getContentPane();
        contentPane.add(jPanel);
        setSize(350,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public boolean orderTable(){
        String tableIdText = tableIdTxt.getText();
        String nameText = nameIdTxt.getText();
        String phoneText = phoneTxt.getText();
        return diningTableService.orderDiningTable(Integer.parseInt(tableIdText),nameText,phoneText);
    }
}
