package lk.ijse.parameeIceCream.dao.custom;

import lk.ijse.parameeIceCream.dao.CrudDAO;
import lk.ijse.parameeIceCream.entity.Order;

import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order> {
    String getCurrentId() throws SQLException, ClassNotFoundException;
    String getLastOId() throws SQLException, ClassNotFoundException;
    double getNetTot(String oId) throws SQLException, ClassNotFoundException;
    int getOrderCount() throws SQLException, ClassNotFoundException;
}
