package com.ww.view;

import com.ww.domain.Menu;
import com.ww.handler.MenuHanlder;
import com.ww.service.MenuService;
import com.ww.view.menuext.MenuViewTable;
import com.ww.view.menuext.MenuViewTableModel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.Vector;

public class MenuView extends JFrame {
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton deleteBtn = new JButton("删除");
    JButton addBtn = new JButton("增加");
    JTextField searchTxt = new JTextField(15);
    JButton searchBtn = new JButton("查询");
    MenuViewTable menuViewTable = new MenuViewTable();
    MenuService menuService = new MenuService();
    MenuHanlder menuHanlder;

    public MenuView() {
        super("菜单界面-农家乐饭店管理系统");
        URL resource = LoginView.class.getClassLoader().getResource("1.png");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);
        menuHanlder = new MenuHanlder(this);
        Container contentPane = getContentPane();
        layOutNorth(contentPane);
        layOutCenter(contentPane);
        setSize(800, 1000);
        setLocationRelativeTo(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //大小可改变
        setResizable(true);
        setVisible(true);
    }

    private void layOutCenter(Container contentPane) {
        List<Menu> menus = menuService.list();
        Vector<Vector<Object>> data = new Vector<>();
        for (Menu menu : menus) {
            Vector<Object> rowVector = new Vector<>();
            rowVector.add(menu.getId());
            rowVector.add(menu.getName());
            rowVector.add(menu.getType());
            rowVector.add(menu.getPrice());
            data.addElement(rowVector);
        }
        MenuViewTableModel menuViewTableModel = MenuViewTableModel.assembleModel(data);
        menuViewTable.setModel(menuViewTableModel);
        menuViewTable.renderRule();
        JScrollPane jScrollPane = new JScrollPane(menuViewTable);
        contentPane.add(jScrollPane, BorderLayout.CENTER);
    }

    private void layOutNorth(Container contentPane) {
        Font BtnFont = new Font("楷体", Font.PLAIN, 25);
        addBtn.setFont(BtnFont);
        deleteBtn.setFont(BtnFont);
        searchBtn.setFont(BtnFont);
        searchTxt.setPreferredSize(new Dimension(400, 40));
        searchTxt.setFont(new Font("楷体", Font.PLAIN, 20));
        addBtn.addActionListener(menuHanlder);
        deleteBtn.addActionListener(menuHanlder);
        searchBtn.addActionListener(menuHanlder);
        northPanel.add(addBtn);
        northPanel.add(deleteBtn);
        northPanel.add(searchTxt);
        northPanel.add(searchBtn);
        northPanel.setPreferredSize(new Dimension(0, 50));
        contentPane.add(northPanel, BorderLayout.NORTH);
    }

    public void reloadSearchTanle() {
        List<Menu> menus;
        if (searchTxt.getText() == null || "".equals(searchTxt.getText())) {
            menus = menuService.list();
        } else {
            menus = menuService.getList(searchTxt.getText());
        }
        Vector<Vector<Object>> data = new Vector<>();
        for (Menu menu : menus) {
            Vector<Object> rowVector = new Vector<>();
            rowVector.add(menu.getId());
            rowVector.add(menu.getName());
            rowVector.add(menu.getType());
            rowVector.add(menu.getPrice());
            data.addElement(rowVector);
        }
        MenuViewTableModel.updateModel(data);
        menuViewTable.renderRule();
    }

    public int[] getSelectedTableIds() {
        int[] selectedRows = menuViewTable.getSelectedRows();
        int[] ids = new int[selectedRows.length];
        for (int i = 0; i < selectedRows.length; i++) {
            int rowIndex = selectedRows[i];
            Object tableId = menuViewTable.getValueAt(rowIndex, 0);
            ids[i] = Integer.valueOf(tableId.toString());
        }
        return ids;
    }
}
