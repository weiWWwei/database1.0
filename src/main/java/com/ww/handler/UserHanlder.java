package com.ww.handler;

import com.ww.domain.Employee;
import com.ww.service.EmployeeService;
import com.ww.service.MenuService;
import com.ww.view.AddMenuView;
import com.ww.view.AddUserView;
import com.ww.view.MenuView;
import com.ww.view.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserHanlder implements ActionListener {
    private UserView userView;
    private EmployeeService employeeService;
    public UserHanlder(UserView userView){
        this.userView=userView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("删除".equals(text)){
            int[] selectedTableIds = userView.getSelectedTableIds();
            if(selectedTableIds.length == 0){
                JOptionPane.showMessageDialog(userView,"请选择要删除的行");
                return;
            }
            int option = JOptionPane.showConfirmDialog(userView, "你确定要删除选择的" + selectedTableIds.length + "行吗?", "确认删除", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                employeeService = new EmployeeService();
                boolean flag = employeeService.deleteUsers(selectedTableIds);
                if(flag){
                    userView.reloadSearchTanle();
                    JOptionPane.showMessageDialog(userView,"删除成功！");
                }
            }
        }else if("增加".equals(text)){
            new AddUserView(userView);
        }else if("查询".equals(text)){
            userView.reloadSearchTanle();
        }
    }
}
