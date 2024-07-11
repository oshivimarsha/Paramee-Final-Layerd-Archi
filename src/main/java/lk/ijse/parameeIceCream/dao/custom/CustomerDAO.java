package lk.ijse.parameeIceCream.dao.custom;

import lk.ijse.parameeIceCream.dao.CrudDAO;
import lk.ijse.parameeIceCream.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {
    List<String> getTels() throws SQLException, ClassNotFoundException;
    int getCustomerCount() throws SQLException, ClassNotFoundException;
}
