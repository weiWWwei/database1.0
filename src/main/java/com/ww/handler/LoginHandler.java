package com.ww.handler;

import com.ww.domain.Employee;
import com.ww.service.EmployeeService;
import com.ww.view.LoginView;
import com.ww.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginHandler extends KeyAdapter implements ActionListener {
    private LoginView loginView;
    private EmployeeService employeeService = new EmployeeService();
    public LoginHandler(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("重置".equals(text)){
            loginView.getUserTxt().setText("");
            loginView.getPwdField().setText("");
        }else if("登录".equals(text)){
            login();
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(KeyEvent.VK_ENTER == e.getKeyCode()){
            login();
        }
    }
    private void login() {
        String empId = loginView.getUserTxt().getText();
        char[] chars = loginView.getPwdField().getPassword();
        if(empId == null || "".equals(empId.trim())||
        chars == null){
            JOptionPane.showMessageDialog(loginView,"用户名密码必填");
            return;
        }
        String pwd = new String(chars);
        Employee employee = employeeService.getEmployeeByIdAndPwd(empId, pwd);
        if(employee!=null){
            //跳转主界面
            new MainView();
            loginView.dispose();
        }else {
            JOptionPane.showMessageDialog(loginView,"用户名密码错误");
        }
    }
}
