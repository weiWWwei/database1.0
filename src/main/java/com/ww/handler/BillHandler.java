package com.ww.handler;

import com.ww.service.BillService;
import com.ww.view.BillView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BillHandler implements ActionListener {
    private BillView billView;
    private BillService billService;
    public BillHandler(BillView billView){
        this.billView=billView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
         if("查询".equals(text)){
             billView.reloadSearchTanle();
        }
    }
}
