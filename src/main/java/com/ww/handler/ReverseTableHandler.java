package com.ww.handler;

import com.ww.view.MainView;
import com.ww.view.ReserveView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReverseTableHandler implements ActionListener {
    private ReserveView reserveView;
    private MainView mainView;
    public ReverseTableHandler(ReserveView reserveView,MainView mainView){
        this.mainView = mainView;
        this.reserveView = reserveView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("确定".equals(text)){
            int value = JOptionPane.showConfirmDialog(reserveView, "是否预定?","确定",JOptionPane.YES_NO_OPTION);
            if(value == JOptionPane.YES_OPTION){
                boolean result = reserveView.orderTable();
                if(result){
                    mainView.reloadSearchTanle();
                    JOptionPane.showMessageDialog(reserveView,"预定成功");
                    reserveView.dispose();
                }else{
                    JOptionPane.showMessageDialog(reserveView,"预定失败");
                }
            }
        }
    }
}
