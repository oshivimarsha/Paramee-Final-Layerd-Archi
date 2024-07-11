package lk.ijse.parameeIceCream.dao.custom;

import lk.ijse.parameeIceCream.dao.CrudDAO;
import lk.ijse.parameeIceCream.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO extends CrudDAO<Employee> {
    Employee searchByNic(String nic) throws SQLException, ClassNotFoundException;
    List<String> getTels() throws SQLException, ClassNotFoundException;
    Employee searchByTel(String tel) throws SQLException, ClassNotFoundException;

    Employee searchById(String id) throws SQLException, ClassNotFoundException;
    int getEmployeeCount() throws SQLException, ClassNotFoundException;
}
