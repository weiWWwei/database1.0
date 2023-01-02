package com.ww.view;

import com.ww.domain.Employee;
import com.ww.domain.Menu;
import com.ww.handler.MenuHanlder;
import com.ww.handler.UserHanlder;
import com.ww.service.EmployeeService;
import com.ww.view.menuext.MenuViewTableModel;
import com.ww.view.userext.UserViewTable;
import com.ww.view.userext.UserViewTableModel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.Vector;

public class UserView extends JFrame{
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton deleteBtn = new JButton("删除");
    JButton addBtn = new JButton("增加");
    JTextField searchTxt = new JTextField(15);
    JButton searchBtn = new JButton("查询");
    UserViewTable userViewTable = new UserViewTable();
    EmployeeService userService = new EmployeeService();
    UserHanlder userHanlder;

    public UserView() {
        super("用户界面-农家乐饭店管理系统");
        URL resource = LoginView.class.getClassLoader().getResource("1.png");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);
        userHanlder = new UserHanlder(this);
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
        List<Employee> users = userService.list();
        Vector<Vector<Object>> data = new Vector<>();
        for (Employee user : users) {
            Vector<Object> rowVector = new Vector<>();
            rowVector.add(user.getId());
            rowVector.add(user.getEmpId());
            rowVector.add(user.getPwd());
            rowVector.add(user.getName());
            rowVector.add(user.getJob());
            data.addElement(rowVector);
        }
       UserViewTableModel userViewTableModel = UserViewTableModel.assembleModel(data);
        userViewTable.setModel(userViewTableModel);
        userViewTable.renderRule();
        JScrollPane jScrollPane = new JScrollPane(userViewTable);
        contentPane.add(jScrollPane, BorderLayout.CENTER);
    }

    private void layOutNorth(Container contentPane) {
        Font BtnFont = new Font("楷体", Font.PLAIN, 25);
        addBtn.setFont(BtnFont);
        deleteBtn.setFont(BtnFont);
        searchBtn.setFont(BtnFont);
        searchTxt.setPreferredSize(new Dimension(400, 40));
        searchTxt.setFont(new Font("楷体", Font.PLAIN, 20));
        addBtn.addActionListener(userHanlder);
        deleteBtn.addActionListener(userHanlder);
        searchBtn.addActionListener(userHanlder);
        northPanel.add(addBtn);
        northPanel.add(deleteBtn);
        northPanel.add(searchTxt);
        northPanel.add(searchBtn);
        northPanel.setPreferredSize(new Dimension(0, 50));
        contentPane.add(northPanel, BorderLayout.NORTH);
    }

    public void reloadSearchTanle() {
        List<Employee> users;
        if (searchTxt.getText() == null || "".equals(searchTxt.getText())) {
            users = userService.list();
        } else {
            users = userService.getList(searchTxt.getText());
        }
        Vector<Vector<Object>> data = new Vector<>();
        for (Employee user : users) {
            Vector<Object> rowVector = new Vector<>();
            rowVector.add(user.getId());
            rowVector.add(user.getEmpId());
            rowVector.add(user.getPwd());
            rowVector.add(user.getName());
            rowVector.add(user.getJob());
            data.addElement(rowVector);
        }
        UserViewTableModel.updateModel(data);
        userViewTable.renderRule();
    }

    public int[] getSelectedTableIds() {
        int[] selectedRows = userViewTable.getSelectedRows();
        int[] ids = new int[selectedRows.length];
        for (int i = 0; i < selectedRows.length; i++) {
            int rowIndex = selectedRows[i];
            Object tableId = userViewTable.getValueAt(rowIndex, 0);
            ids[i] = Integer.valueOf(tableId.toString());
        }
        return ids;
    }
}
