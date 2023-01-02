package com.ww.view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.Vector;

import com.ww.domain.DiningTable;
import com.ww.handler.MainViewHandler;
import com.ww.utils.DimensionUtil;
import com.ww.service.DiningTableService;
import com.ww.view.tableext.MainViewTable;
import com.ww.view.tableext.MainViewTableModel;

public class MainView extends JFrame {

    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton payBtn = new JButton("结账");
    JButton orderBtn = new JButton("点餐");
    JButton reverseBtn = new JButton("预定");
    JButton menuBtn = new JButton("菜单管理");
    JButton billBtn = new JButton("账单查看");
    JButton userBtn = new JButton("员工管理");
    JTextField searchTxt = new JTextField(15);
    JButton searchBtn = new JButton("查询");
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");
    MainViewTable mainViewTable = new MainViewTable();
    DiningTableService diningTableService = new DiningTableService();
    private int pageNow = 1;
    private int pageSize = 10;
    private int countSum = 0;
    MainViewHandler mainViewHandler;

    public MainView(boolean result) {
        super("主界面-农家乐饭店管理系统");
        URL resource = LoginView.class.getClassLoader().getResource("1.png");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);
        mainViewHandler = new MainViewHandler(this);
        Rectangle bounds = DimensionUtil.getBounds();
        pageSize = Math.floorDiv(bounds.height, 58);
        Container contentPane = getContentPane();
        layOutNorth(contentPane, result);
        layOutSouth(contentPane);
        layOutCenter(contentPane);
        setBounds(bounds);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        //关闭退出程序
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //大小不可改变
        setResizable(true);
        setVisible(true);
    }

    private void layOutCenter(Container contentPane) {
        List<DiningTable> tables = diningTableService.list();
        countSum = tables.size();
        Vector<Vector<Object>> data = new Vector<>();
        int count = 0;
        for (DiningTable table : tables) {
            count++;
            if (count >= pageSize) {
                continue;
            }
            Vector<Object> rowVector = new Vector<>();
            rowVector.add(table.getId());
            rowVector.add(table.getState());
            rowVector.add(table.getOrderName());
            rowVector.add(table.getOrderTel());
            rowVector.add(table.getNums());
            data.addElement(rowVector);
        }
        MainViewTableModel mainViewTableModel = MainViewTableModel.assembleModel(data);
        mainViewTable.setModel(mainViewTableModel);
        mainViewTable.renderRule();
        showPreNext();
        JScrollPane jScrollPane = new JScrollPane(mainViewTable);
        contentPane.add(jScrollPane, BorderLayout.CENTER);
    }

    private void layOutSouth(Container contentPane) {
        Font BtnFont = new Font("楷体", Font.PLAIN, 40);
        preBtn.setFont(BtnFont);
        nextBtn.setFont(BtnFont);
        preBtn.addActionListener(mainViewHandler);
        nextBtn.addActionListener(mainViewHandler);
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        contentPane.add(southPanel, BorderLayout.SOUTH);
    }

    private void showPreNext() {
        if (pageNow == 1) {
            preBtn.setVisible(false);
        } else {
            preBtn.setVisible(true);
        }
        int pageCount = 0;
        if (countSum % pageSize == 0) {
            pageCount = countSum / pageSize;
        } else {
            pageCount = countSum / pageSize + 1;
        }
        if (pageNow == pageCount) {
            nextBtn.setVisible(false);
        } else {
            nextBtn.setVisible(true);
        }
    }

    private void layOutNorth(Container contentPane, boolean result) {
        Font BtnFont = new Font("楷体", Font.PLAIN, 25);
        reverseBtn.setFont(BtnFont);
        payBtn.setFont(BtnFont);
        orderBtn.setFont(BtnFont);
        menuBtn.setFont(BtnFont);
        billBtn.setFont(BtnFont);
        searchBtn.setFont(BtnFont);
        userBtn.setFont(BtnFont);
        searchTxt.setPreferredSize(new Dimension(400, 40));
        searchTxt.setFont(new Font("楷体", Font.PLAIN, 20));
        reverseBtn.addActionListener(mainViewHandler);
        orderBtn.addActionListener(mainViewHandler);
        payBtn.addActionListener(mainViewHandler);
        menuBtn.addActionListener(mainViewHandler);
        billBtn.addActionListener(mainViewHandler);
        searchBtn.addActionListener(mainViewHandler);
        userBtn.addActionListener(mainViewHandler);
        northPanel.add(reverseBtn);
        northPanel.add(orderBtn);
        northPanel.add(payBtn);
        northPanel.add(menuBtn);
        northPanel.add(billBtn);
        if (result) {
            northPanel.add(userBtn);
        }
        northPanel.add(searchTxt);
        northPanel.add(searchBtn);
        northPanel.setPreferredSize(new Dimension(0, 50));
        contentPane.add(northPanel, BorderLayout.NORTH);
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageNow() {
        return pageNow;
    }


    //待补充逻辑
    public void reloadSearchTanle() {
        List<DiningTable> tables;
        if (searchTxt.getText() == null || "".equals(searchTxt.getText())) {
            tables = diningTableService.list();
        } else {
            tables = diningTableService.getDiningTableByorderTel(searchTxt.getText());
        }
        countSum = tables.size();
        int count = 0;
        Vector<Vector<Object>> data = new Vector<>();
        for (DiningTable table : tables) {
            count++;
            if (count >= pageSize) {
                continue;
            }
            Vector<Object> rowVector = new Vector<>();
            rowVector.add(table.getId());
            rowVector.add(table.getState());
            rowVector.add(table.getOrderName());
            rowVector.add(table.getOrderTel());
            rowVector.add(table.getNums());
            data.addElement(rowVector);
        }
        MainViewTableModel.updateModel(data);
        mainViewTable.renderRule();
        showPreNext();
    }

    public void reloadPreNextTanle() {
        Rectangle bounds = DimensionUtil.getBounds();
        pageSize = Math.floorDiv(bounds.height, 58);
        List<DiningTable> tables = diningTableService.list();
        countSum = tables.size();
        int count = 0;
        int countTable = 0;
        Vector<Vector<Object>> data = new Vector<>();
        for (DiningTable table : tables) {
            if ((count / pageSize) < pageNow - 1) {
                count++;
                continue;
            }
            countTable++;
            if (countTable >= pageSize) {
                continue;
            }
            Vector<Object> rowVector = new Vector<>();
            rowVector.add(table.getId());
            rowVector.add(table.getState());
            rowVector.add(table.getOrderName());
            rowVector.add(table.getOrderTel());
            rowVector.add(table.getNums());
            data.addElement(rowVector);
        }
        MainViewTableModel.updateModel(data);
        mainViewTable.renderRule();
        showPreNext();
    }

    public int[] getSelectedTableIds() {
        int[] selectedRows = mainViewTable.getSelectedRows();
        int[] ids = new int[selectedRows.length];
        for (int i = 0; i < selectedRows.length; i++) {
            int rowIndex = selectedRows[i];
            Object tableId = mainViewTable.getValueAt(rowIndex, 0);
            ids[i] = Integer.valueOf(tableId.toString());
        }
        return ids;
    }
}
