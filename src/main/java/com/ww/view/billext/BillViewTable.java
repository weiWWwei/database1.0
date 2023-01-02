package com.ww.view.billext;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class BillViewTable extends JTable {
    public BillViewTable() {
        JTableHeader tableHeader = getTableHeader();
        tableHeader.setFont(new Font(null, Font.BOLD, 25));
        tableHeader.setForeground(Color.RED);
        setFont(new Font(null, Font.PLAIN, 25));
        setForeground(Color.black);
        setGridColor(Color.BLACK);
        setRowHeight(50);
    }

    public void setDataModel(BillViewTableModel billViewTableModel) {
        this.setModel(billViewTableModel);
    }

    public void renderRule() {
        Vector<String> columns = BillViewTableModel.getColumns();
        BillViewCellRender render = new BillViewCellRender();
        for (int i = 0; i < columns.size(); i++) {
            TableColumn column = getColumn(columns.get(i));
            column.setCellRenderer(render);
            if (i != 1 && i != 6) {
                column.setPreferredWidth(130);
                column.setMaxWidth(200);

            }
            column.setResizable(false);
        }
    }
}
