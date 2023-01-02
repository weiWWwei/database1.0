package com.ww.view;

import com.ww.domain.*;
import com.ww.handler.BillHandler;
import com.ww.service.BillService;
import com.ww.view.billext.BillViewTable;
import com.ww.view.billext.BillViewTableModel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.Vector;

public class BillView extends JFrame{
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JTextField searchTxt = new JTextField(15);
    JButton searchBtn = new JButton("查询");
    BillViewTable billViewTable = new BillViewTable();
    BillService billService = new BillService();
    BillHandler billHanlder;
    public BillView() {
        super("账单界面-农家乐饭店管理系统");
        URL resource = LoginView.class.getClassLoader().getResource("1.png");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);
        billHanlder = new BillHandler(this);
        Container contentPane = getContentPane();
        layOutNorth(contentPane);
        layOutCenter(contentPane);
        setSize(1800, 1000);
        setLocationRelativeTo(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //大小可改变
        setResizable(true);
        setVisible(true);
    }
    private void layOutCenter(Container contentPane) {
        List<Bill> bills = billService.list();
        Vector<Vector<Object>> data = new Vector<>();
        for (Bill bill : bills) {
            Vector<Object> rowVector = new Vector<>();
            rowVector.add(bill.getId());
            rowVector.add(bill.getBillId());
            rowVector.add(bill.getMenuId());
            rowVector.add(bill.getNums());
            rowVector.add(bill.getMoney());
            rowVector.add(bill.getDiningTableId());
            rowVector.add(bill.getBillDate());
            rowVector.add(bill.getState());
            data.addElement(rowVector);
        }
        BillViewTableModel billViewTableModel = BillViewTableModel.assembleModel(data);
        billViewTable.setModel(billViewTableModel);
        billViewTable.renderRule();
        JScrollPane jScrollPane = new JScrollPane(billViewTable);
        contentPane.add(jScrollPane, BorderLayout.CENTER);
    }

    private void layOutNorth(Container contentPane) {
        Font BtnFont = new Font("楷体", Font.PLAIN, 25);
        searchBtn.setFont(BtnFont);
        searchTxt.setPreferredSize(new Dimension(400, 40));
        searchTxt.setFont(new Font("楷体", Font.PLAIN, 20));
        searchBtn.addActionListener(billHanlder);
        northPanel.add(searchTxt);
        northPanel.add(searchBtn);
        northPanel.setPreferredSize(new Dimension(0, 50));
        contentPane.add(northPanel, BorderLayout.NORTH);
    }

    public void reloadSearchTanle() {
        List<Bill> bills;
        if (searchTxt.getText() == null || "".equals(searchTxt.getText())) {
            bills = billService.list();
        } else {
            bills = billService.getList(searchTxt.getText());
        }
        Vector<Vector<Object>> data = new Vector<>();
        for (Bill bill : bills) {
            Vector<Object> rowVector = new Vector<>();
            rowVector.add(bill.getId());
            rowVector.add(bill.getBillId());
            rowVector.add(bill.getMenuId());
            rowVector.add(bill.getNums());
            rowVector.add(bill.getMoney());
            rowVector.add(bill.getDiningTableId());
            rowVector.add(bill.getBillDate());
            rowVector.add(bill.getState());
            data.addElement(rowVector);
        }
        BillViewTableModel.updateModel(data);
        billViewTable.renderRule();
    }
}
