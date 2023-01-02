package com.ww.view.menuext;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class MenuViewTableModel extends DefaultTableModel {
    static Vector<String> columns = new Vector<>();

    static {
        columns.addElement("菜品号");
        columns.addElement("菜品名");
        columns.addElement("类型");
        columns.addElement("价格");
    }
    private MenuViewTableModel() {
        super(null, columns);
    }
    private static MenuViewTableModel menuViewTableModel= new MenuViewTableModel();

    public static MenuViewTableModel assembleModel(Vector<Vector<Object>> data) {
        menuViewTableModel.setDataVector(data, columns);
        return menuViewTableModel;
    }
    public static void updateModel(Vector<Vector<Object>> data) {
        menuViewTableModel.setDataVector(data, columns);
    }

    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
