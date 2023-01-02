package com.ww.handler;

import com.ww.service.BillService;
import com.ww.view.MainView;
import com.ww.view.PayView;
import com.ww.view.ReserveView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayHandler implements ActionListener {
    private PayView payView;
    private MainView mainView;
    public PayHandler(PayView payView,MainView mainView){
        this.mainView = mainView;
        this.payView = payView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("确定".equals(text)){
            int value = JOptionPane.showConfirmDialog(payView, "是否结账?","确定",JOptionPane.YES_NO_OPTION);
            if(value == JOptionPane.YES_OPTION){
                boolean result = payView.payMoney();
                if(result){
                    mainView.reloadSearchTanle();
                    JOptionPane.showMessageDialog(payView,"结账成功");
                    payView.dispose();
                }else{
                    JOptionPane.showMessageDialog(payView,"结账失败");
                }
            }
        }
    }
}
