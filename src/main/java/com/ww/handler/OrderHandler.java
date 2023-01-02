package com.ww.handler;

import com.ww.service.EmployeeService;
import com.ww.view.LoginView;
import com.ww.view.OrderView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderHandler implements ActionListener {
    private OrderView orderView;
    public OrderHandler(OrderView orderView) {
        this.orderView = orderView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("确定".equals(text)){
            int value = JOptionPane.showConfirmDialog(orderView, "是否点餐?","确定",JOptionPane.YES_NO_OPTION);
            if(value == JOptionPane.YES_OPTION){
                boolean result = orderView.orderMenu();
                if(result){
                    JOptionPane.showMessageDialog(orderView,"点餐成功");
                    orderView.dispose();
                }else{
                    JOptionPane.showMessageDialog(orderView,"点餐失败");
                }
            }
        }
    }
}
