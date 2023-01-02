package com.ww.view.billext;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class BillViewTableModel extends DefaultTableModel {
    static Vector<String> columns = new Vector<>();

    static {
        columns.addElement("编号");
        columns.addElement("账单号");
        columns.addElement("菜品编号");
        columns.addElement("数量");
        columns.addElement("价格");
        columns.addElement("餐桌号");
        columns.addElement("日期");
        columns.addElement("结账方式");
    }
    private BillViewTableModel() {
        super(null, columns);
    }
    private static BillViewTableModel billViewTableModel = new BillViewTableModel();

    public static BillViewTableModel assembleModel(Vector<Vector<Object>> data) {
        billViewTableModel.setDataVector(data, columns);
        return billViewTableModel;
    }
    public static void updateModel(Vector<Vector<Object>> data) {
        billViewTableModel.setDataVector(data, columns);
    }

    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
