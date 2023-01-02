package com.ww.handler;

import com.ww.view.AddMenuView;
import com.ww.view.MainView;
import com.ww.view.MenuView;
import com.ww.view.ReserveView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMenuHandler implements ActionListener {
    private AddMenuView addMenuView;
    private MenuView menuView;
    public AddMenuHandler(AddMenuView addMenuView,MenuView menuView){
        this.menuView = menuView;
        this.addMenuView = addMenuView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("确定".equals(text)){
            int value = JOptionPane.showConfirmDialog(addMenuView, "是否添加?");
            if(value == JOptionPane.YES_OPTION){
                boolean result = addMenuView.addMenu();
                if(result){
                    JOptionPane.showMessageDialog(addMenuView,"添加成功！");
                    menuView.reloadSearchTanle();
                    addMenuView.dispose();
                }else{
                    JOptionPane.showMessageDialog(addMenuView,"添加失败");
                }
            }
        }
    }
}
