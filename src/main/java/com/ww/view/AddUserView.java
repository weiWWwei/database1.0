package com.ww.view;

import com.ww.handler.AddUserHandler;
import com.ww.service.EmployeeService;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class AddUserView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel userNameLabel = new JLabel("用户名:",JLabel.RIGHT);
    JTextField userNameTxt = new JTextField();
    JLabel pwdLable = new JLabel("密码:",JLabel.RIGHT);
    JTextField pwdTxt = new JTextField();
    JLabel nameLabel = new JLabel("名字:",JLabel.RIGHT);
    JTextField nameTxt = new JTextField();
    JLabel jobLabel = new JLabel("职位:",JLabel.RIGHT);
    JTextField jobTxt = new JTextField();
    JButton addBtn = new JButton("确定");
    EmployeeService employeeService;
    AddUserHandler addUserHandler;
    public AddUserView(UserView userView){
        super(userView,"增加用户-农家乐饭店管理系统",true);
        URL resource = LoginView.class.getClassLoader().getResource("1.png");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);
        employeeService = new EmployeeService();
        addUserHandler = new AddUserHandler(this,userView);
        userNameLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(userNameLabel);
        userNameTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(userNameTxt);
        pwdLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(pwdLable);
        pwdTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(pwdTxt);
        nameLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(nameLabel);
        nameTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(nameTxt);
        jobLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(jobLabel);
        jobTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(jobTxt);
        addBtn.addActionListener(addUserHandler);
        jPanel.add(addBtn);
        Container contentPane = getContentPane();
        contentPane.add(jPanel);
        setSize(350,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public boolean addUser() {
        String userNameText = userNameTxt.getText();
        String pwdText = pwdTxt.getText();
        String nameText = nameTxt.getText();
        String job = jobTxt.getText();
        return employeeService.addUser(userNameText,pwdText,nameText,job);
    }
}
