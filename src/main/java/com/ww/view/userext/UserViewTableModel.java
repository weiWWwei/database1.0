package com.ww.view.userext;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class UserViewTableModel extends DefaultTableModel {
    static Vector<String> columns = new Vector<>();

    static {
        columns.addElement("用户编号");
        columns.addElement("用户名");
        columns.addElement("密码（md5）");
        columns.addElement("名字");
        columns.addElement("职位");
    }
    private UserViewTableModel() {
        super(null, columns);
    }
    private static UserViewTableModel menuViewTableModel= new UserViewTableModel();

    public static UserViewTableModel assembleModel(Vector<Vector<Object>> data) {
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
