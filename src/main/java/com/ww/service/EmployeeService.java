package com.ww.service;


import com.ww.dao.EmployeeDAO;
import com.ww.domain.Employee;
import com.ww.domain.Menu;

import java.util.List;


/**
 * @author 魏巍
 * @version 1.0
 * 该类完成对employee表的各种操作(通过调用EmployeeDAO对象完成)
 */
public class EmployeeService {

    //定义一个 EmployeeDAO 属性
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    //方法，根据empId 和 pwd 返回一个Employee对象
    //如果查询不到，就返回null
    public Employee getEmployeeByIdAndPwd(String empId, String pwd) {

        return employeeDAO.querySingle("select * from employee where empId=? and pwd=md5(?)", Employee.class, empId, pwd);

    }

    public List<Employee> list() {
        return employeeDAO.queryMulti("select * from employee order by job", Employee.class);
    }

    public List<Employee> getList(String value) {
        List<Employee> employees = employeeDAO.queryMulti("select * from employee where name = ? or empId = ? or job =?", Employee.class, value, value,value);
        if (employees.size() == 0) {
            employees = employeeDAO.queryMulti("select * from employee where id = ? ", Employee.class, Integer.parseInt(value));
        }
        return employees;
    }

    public boolean deleteUsers(int[] selectedTableIds) {
        int update = 0;
        for (int i = 0; i < selectedTableIds.length; i++) {
            update += employeeDAO.update("delete from employee where id = ?", selectedTableIds[i]);
        }
        return update == selectedTableIds.length;
    }

    public boolean addUser(String userNameText, String pwdText, String nameText, String job) {
        int update = employeeDAO.update("insert into employee values(null,?,md5(?),?,?)", userNameText, pwdText, nameText, job);
        return update > 0;
    }
}
