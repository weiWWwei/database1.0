package com.ww.handler;

import com.ww.service.MenuService;
import com.ww.view.AddMenuView;
import com.ww.view.MainView;
import com.ww.view.MenuView;
import com.ww.view.ReserveView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuHanlder implements ActionListener {
    private MenuView menuView;
    private MenuService menuService;
    public MenuHanlder(MenuView menuView){
        this.menuView=menuView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("删除".equals(text)){
            int[] selectedTableIds = menuView.getSelectedTableIds();
            if(selectedTableIds.length == 0){
                JOptionPane.showMessageDialog(menuView,"请选择要删除的行");
                return;
            }
            int option = JOptionPane.showConfirmDialog(menuView, "你确定要删除选择的" + selectedTableIds.length + "行吗?", "确认删除", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                menuService = new MenuService();
                boolean flag = menuService.deleteMenus(selectedTableIds);
                if(flag){
                    menuView.reloadSearchTanle();
                    JOptionPane.showMessageDialog(menuView,"删除成功！");
                }
            }
        }else if("增加".equals(text)){
            new AddMenuView(menuView);
        }else if("查询".equals(text)){
            menuView.reloadSearchTanle();
        }
    }
}
