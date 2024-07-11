package lk.ijse.parameeIceCream.dao.custom;

import lk.ijse.parameeIceCream.dao.CrudDAO;
import lk.ijse.parameeIceCream.entity.Salary;

import java.sql.SQLException;
import java.util.List;

public interface SalaryDAO extends CrudDAO<Salary> {
    List<Double> getSalaryDetail(String id) throws SQLException, ClassNotFoundException;
}
