package lk.ijse.parameeIceCream.dao.custom.impl;

import lk.ijse.parameeIceCream.dao.DAOFactory;
import lk.ijse.parameeIceCream.dao.SQLUtill;
import lk.ijse.parameeIceCream.dao.custom.EmployeeDAO;
import lk.ijse.parameeIceCream.db.DbConnection;
import lk.ijse.parameeIceCream.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM employee");

        ArrayList<Employee> empList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String nic = resultSet.getString(3);
            String address = resultSet.getString(4);
            String email = resultSet.getString(5);
            String tel = resultSet.getString(6);
            Date dob = Date.valueOf(resultSet.getString(7));
            Date registerDate = Date.valueOf(resultSet.getString(8));
            String position = resultSet.getString(9);
            double salary = Double.parseDouble((resultSet.getString(10)));
            String departmentId = resultSet.getString(11);
            String path = resultSet.getString(12);

            Employee employee = new Employee(id, name, nic, address, email, tel, dob, registerDate, position, salary, departmentId, path);
            empList.add(employee);
        }
        return empList;
    }

    @Override
    public boolean save(Employee employee) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", employee.getId(),employee.getName(),employee.getNic(),employee.getAddress(),employee.getEmail(),employee.getTel(),employee.getDob(),employee.getRegisterDate(),employee.getPosition(),employee.getSalary(),employee.getDepartmentId(),employee.getPath());
        return result;
    }

    @Override
    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("UPDATE employee SET employeeId = ?, name = ?, nic = ?, address = ?, email = ?, DOB = ?, registerDate = ?, position = ?, salary = ?, departmentId = ?, path = ? WHERE tel = ?", employee.getId(), employee.getName(), employee.getNic(), employee.getAddress(), employee.getEmail(), employee.getDob(), employee.getRegisterDate(), employee.getPosition(), employee.getSalary(), employee.getDepartmentId(), employee.getPath(), employee.getTel());
        return result;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT employeeId FROM employee WHERE employeeId=?",id);
        return rst.next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT employeeId FROM employee ORDER BY CAST(SUBSTRING(employeeId, 2) AS UNSIGNED) DESC LIMIT 1");

        if (rst.next()) {
            String id = rst.getString(1);
            String[] split = id.split("E");
            int idNum = Integer.parseInt(split[1]);

            if(idNum >= 1){
                return "E" + 0 + 0 + ++idNum;
            }else if(idNum >= 9){
                return "E" + 0 + ++idNum;
            } else if(idNum >= 99){
                return "E" + ++idNum;
            }
        }
        return "E001";
    }

    @Override
    public boolean delete(String nic) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("DELETE FROM employee WHERE nic = ?", nic);
        return result;
    }

    @Override
    public Employee search(String nic) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM employee WHERE nic = ?", nic);

        if (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            nic = resultSet.getString(3);
            String address = resultSet.getString(4);
            String email = resultSet.getString(5);
            String tel = resultSet.getString(6);
            Date dob = Date.valueOf(resultSet.getString(7));
            Date registerDate = Date.valueOf(resultSet.getString(8));
            String position = resultSet.getString(9);
            double salary = Double.parseDouble(resultSet.getString(10));
            String departmentId = resultSet.getString(11);
            String path = resultSet.getString(12);

            Employee employee = new Employee(id, name, nic, address, email, tel, dob, registerDate, position, salary, departmentId, path);

            return employee;
        }
        return null;
    }

    @Override
    public Employee searchByNic(String nic) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM employee WHERE nic = ?", nic);

        if (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            nic = resultSet.getString(3);
            String address = resultSet.getString(4);
            String email = resultSet.getString(5);
            String tel = resultSet.getString(6);
            Date dob = Date.valueOf(resultSet.getString(7));
            Date registerDate = Date.valueOf(resultSet.getString(8));
            String position = resultSet.getString(9);
            double salary = Double.parseDouble(resultSet.getString(10));
            String departmentId = resultSet.getString(11);
            String path = resultSet.getString(12);

            Employee employee = new Employee(id, name, nic, address, email, tel, dob, registerDate, position, salary, departmentId, path);

            return employee;
        }

        return null;
    }

    @Override
    public List<String> getTels() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute( "SELECT tel FROM employee");

        List<String> telList = new ArrayList<>();

        while (resultSet.next()) {
            String tel = resultSet.getString(1);
            telList.add(tel);
        }
        return telList;
    }

    @Override
    public Employee searchByTel(String tel) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM employee WHERE tel = ?", tel);

        if (resultSet.next()) {

            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String nic = resultSet.getString(3);
            String address = resultSet.getString(4);
            String email = resultSet.getString(5);
            String eTel = resultSet.getString(6);
            Date dob = Date.valueOf(resultSet.getString(7));
            Date registerDate = Date.valueOf(resultSet.getString(8));
            String position = resultSet.getString(9);
            double salary = Double.parseDouble(resultSet.getString(10));
            String departmentId = resultSet.getString(11);
            String path = resultSet.getString(12);

            Employee employee = new Employee(id, name, nic, address, email, eTel, dob, registerDate, position, salary, departmentId, path);

            return employee;
        }

        return null;
    }

    @Override
    public Employee searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM employee WHERE employeeId = ?", id);

        if (resultSet.next()) {

            String eId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String nic = resultSet.getString(3);
            String address = resultSet.getString(4);
            String email = resultSet.getString(5);
            String eTel = resultSet.getString(6);
            Date dob = Date.valueOf(resultSet.getString(7));
            Date registerDate = Date.valueOf(resultSet.getString(8));
            String position = resultSet.getString(9);
            double salary = Double.parseDouble(resultSet.getString(10));
            String departmentId = resultSet.getString(11);
            String path = resultSet.getString(12);

            Employee employee = new Employee(eId, name, nic, address, email, eTel, dob, registerDate, position, salary, departmentId, path);

            return employee;
        }

        return null;
    }

    @Override
    public int getEmployeeCount() throws SQLException, ClassNotFoundException {
        /*ResultSet resultSet = SQLUtill.execute("SELECT COUNT(*) AS employeeCount FROM employee");

        if(resultSet.next()) {
            return resultSet.getInt("employeeCount");
        }
        return 0;*/
        String sql = "SELECT COUNT(*) AS employeeCount FROM employee";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("employeeCount");
        }
        return 0;
    }
}
