package com.ww.service;


import com.ww.dao.MenuDAO;
import com.ww.domain.Menu;

import java.util.List;

/**
 * @author 魏巍
 * @version 1.0
 * 完成对menu表的各种操作(通过调用MenuDAO)
 */
public class MenuService {

    //定义MenuDAO 属性
    private MenuDAO menuDAO = new MenuDAO();

    //返回所有的菜品, 返回给界面使用
    public List<Menu> list() {
        return menuDAO.queryMulti("select * from menu order by type,price", Menu.class);
    }

    //需要方法，根据id, 返回Menu对象
    public Menu getMenuById(int id) {
        return menuDAO.querySingle("select * from menu where id = ?", Menu.class, id);
    }
    public Menu getMenuByName(String name) {
        return menuDAO.querySingle("select * from menu where name = ?", Menu.class, name);
    }

    public List<Menu> getList(String value) {
        List<Menu> menus = menuDAO.queryMulti("select * from menu where name = ? or type = ? ", Menu.class, value, value);
        if(menus.size()==0){
            menus = menuDAO.queryMulti("select * from menu where id = ? ", Menu.class, Integer.parseInt(value));
        }
        return menus;
    }

    public boolean addMenu(String name, String type, double price) {
        int update = menuDAO.update("insert into menu values(null,?,?,?)", name, type, price);
        return update > 0;
    }

    public boolean deleteMenus(int[] selectedTableIds) {
        int update = 0;
        for (int i = 0; i < selectedTableIds.length; i++) {
            update += menuDAO.update("delete from menu where id = ?", selectedTableIds[i]);
        }
        return update == selectedTableIds.length;
    }
}
