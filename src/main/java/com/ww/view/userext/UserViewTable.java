package com.ww.view.userext;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class UserViewTable extends JTable {
    public UserViewTable() {
        JTableHeader tableHeader = getTableHeader();
        tableHeader.setFont(new Font(null, Font.BOLD, 25));
        tableHeader.setForeground(Color.RED);
        setFont(new Font(null, Font.PLAIN, 25));
        setForeground(Color.black);
        setGridColor(Color.BLACK);
        setRowHeight(50);
    }
    public void setDataModel(UserViewTableModel menuViewTableModel) {
        this.setModel(menuViewTableModel);
    }
    public void renderRule() {
        Vector<String> columns = UserViewTableModel.getColumns();
        UserViewCellRender render = new UserViewCellRender();
        for (int i = 0; i < columns.size(); i++) {
            TableColumn column = getColumn(columns.get(i));
            column.setCellRenderer(render);
            if (i == 0) {
                column.setPreferredWidth(150);
                column.setMaxWidth(200);

            }
            column.setResizable(false);
        }
    }
}
