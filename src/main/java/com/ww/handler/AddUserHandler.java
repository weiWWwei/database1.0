package com.ww.handler;

import com.ww.view.AddMenuView;
import com.ww.view.AddUserView;
import com.ww.view.MenuView;
import com.ww.view.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserHandler  implements ActionListener {
    private AddUserView addUserView;
    private UserView userView;
    public AddUserHandler(AddUserView addUserView,UserView userView){
        this.userView = userView;
        this.addUserView = addUserView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("确定".equals(text)){
            int value = JOptionPane.showConfirmDialog(addUserView, "是否添加?");
            if(value == JOptionPane.YES_OPTION){
                boolean result = addUserView.addUser();
                if(result){
                    JOptionPane.showMessageDialog(addUserView,"添加成功！");
                    userView.reloadSearchTanle();
                    addUserView.dispose();
                }else{
                    JOptionPane.showMessageDialog(addUserView,"添加失败");
                }
            }
        }
    }


}
