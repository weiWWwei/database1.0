package com.ww.handler;

import com.ww.domain.DiningTable;
import com.ww.service.BillService;
import com.ww.service.DiningTableService;
import com.ww.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViewHandler implements ActionListener {
    private MainView mainView;
    private DiningTableService diningTableService;
    private BillService billService = new BillService();

    public MainViewHandler(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("预定".equals(text)) {
            int[] selectedTableIds = mainView.getSelectedTableIds();
            if (selectedTableIds.length == 0) {
                JOptionPane.showMessageDialog(mainView, "请选择要预定的餐桌");
                return;
            }
            if (selectedTableIds.length > 1) {
                JOptionPane.showMessageDialog(mainView, "一次只能预定一个");
                return;
            }
            diningTableService = new DiningTableService();
            DiningTable diningTable = diningTableService.getDiningTableById(selectedTableIds[0]);
            if (!"空".equals(diningTable.getState())) {
                JOptionPane.showMessageDialog(mainView, "该餐桌不可预定");
                return;
            }
            new ReserveView(mainView, selectedTableIds[0]);
        } else if ("点餐".equals(text)) {
            int[] selectedTableIds = mainView.getSelectedTableIds();
            if (selectedTableIds.length == 0) {
                JOptionPane.showMessageDialog(mainView, "请选择要点餐的餐桌");
                return;
            }
            if (selectedTableIds.length > 1) {
                JOptionPane.showMessageDialog(mainView, "一次只能点餐一个");
                return;
            }
            new OrderView(selectedTableIds[0]);
        } else if ("结账".equals(text)) {
            int[] selectedTableIds = mainView.getSelectedTableIds();
            if (selectedTableIds.length == 0) {
                JOptionPane.showMessageDialog(mainView, "请选择要结账的餐桌");
                return;
            }
            if (selectedTableIds.length > 1) {
                JOptionPane.showMessageDialog(mainView, "一次只能结账一个");
                return;
            }
            if(!billService.hasPayBillByDiningTableId(selectedTableIds[0])){
                JOptionPane.showMessageDialog(mainView, "该餐桌没有未结账的账单");
                return;
            }
            new PayView(mainView, selectedTableIds[0]);
            mainView.reloadSearchTanle();
        } else if ("菜单管理".equals(text)) {
            new MenuView();
        } else if ("账单查看".equals(text)) {
            new BillView();
        } else if ("查询".equals(text)) {
            mainView.setPageNow(1);
            mainView.reloadSearchTanle();
        } else if ("上一页".equals(text)) {
            mainView.setPageNow(mainView.getPageNow() - 1);
            mainView.reloadPreNextTanle();
        } else if ("下一页".equals(text)) {
            mainView.setPageNow(mainView.getPageNow() + 1);
            mainView.reloadPreNextTanle();
        }
    }
}
